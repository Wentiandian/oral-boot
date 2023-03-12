package itw.oralboot.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import itw.oralboot.common.utils.R;
import itw.oralboot.modules.sys.entity.*;
import itw.oralboot.modules.sys.form.EleRecordsFileListFrom;
import itw.oralboot.modules.sys.service.*;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 电子病历信息管理
 */
@RestController
@RequestMapping("/sys/dzbl")
public class EleRecordsController extends AbstractController{

    @Autowired
    private EleRecordsService eleRecordsService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private SysFileService sysFileService;

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private DrugPreService drugPreService;

    @Autowired
    private SysGhService sysGhService;

    @Autowired
    private SysBookingService sysBookingService;

    /**
     * 查询所有电子病历信息
     * @param params
     * @return
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        Integer current = Integer.parseInt(params.get("page").toString());
        Integer pageSize = Integer.parseInt(params.get("limit").toString());
        String eleRecordsId = (String) params.get("eleRecordsId");
        String patientName = (String) params.get("patientName");
        String deptId = (String) params.get("deptId");
        String isReferral = (String) params.get("isReferral");
        String starDate = (String) params.get("starDate");
        String endDate = (String) params.get("endDate");

        Page page = (Page) eleRecordsService.findPage(current,pageSize,eleRecordsId,patientName,deptId,isReferral,starDate,endDate);

        return R.ok().put("list",page);
    }

    /**
     * 根据病历ID查询病历信息和文件信息
     * @param eleRecordsId
     * @return
     * @throws ParseException
     */
    @GetMapping("/info/{eleRecordsId}")
    public R getInfoById(@PathVariable("eleRecordsId") Long eleRecordsId) throws ParseException {
        //根据病历ID查询病历信息
        EleRecordsEntity eleRecordsEntity  =  eleRecordsService.getById(eleRecordsId);

        //根据病历ID查询文件信息
        LambdaQueryWrapper<SysFileEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysFileEntity::getRelationId,eleRecordsId)
                .eq(SysFileEntity::getStatus,1);
        List<SysFileEntity> fileEntityList = sysFileService.list(queryWrapper);

        //获取药物单ID
        Long prescriptionId = eleRecordsEntity.getPrescriptionId();

        //根据药物单ID查询药物单信息
        PrescriptionEntity prescriptionEntity = prescriptionService.getById(prescriptionId);

        //根基药物单ID查询药品信息
        LambdaQueryWrapper<DrugPreEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DrugPreEntity::getPrescriptionId,prescriptionId);
        List<DrugPreEntity> drugPreEntityList = drugPreService.list(wrapper);

        return R.ok().put("info",eleRecordsEntity)
                .put("fileList",fileEntityList)
                .put("prescription",prescriptionEntity)
                .put("drugPreList",drugPreEntityList);
    }

    /**
     * 对药物单和药品关系信息、删除药物单信息 、病历信息进行删除
     * @param eleRecordsIds
     * @return
     */
    @PostMapping("/delete")
    public R deleteGh(@RequestBody Long[] eleRecordsIds){
        //获取病历对应药物单ID
        List<EleRecordsEntity> eleRecordsEntityList= eleRecordsService.listByIds(Arrays.asList(eleRecordsIds));
        //删除药物单和药品关系信息、删除药物单信息
        eleRecordsEntityList.stream().map((item)->{
            LambdaQueryWrapper<DrugPreEntity> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(DrugPreEntity::getPrescriptionId,item.getPrescriptionId());
            drugPreService.remove(queryWrapper);
            prescriptionService.removeById(item.getPrescriptionId());
            return null;
        }).collect(Collectors.toList());
        //删除病历信息
        eleRecordsService.removeByIds(Arrays.asList(eleRecordsIds));
        return R.ok();
    }

    /**
     * 新增保存药物单信息、病历信息、病历关联文件信息、药物单与药品关系信息
     */
    @PostMapping("/save")
    public R save(@RequestBody EleRecordsFileListFrom eleRecordsFileListFrom) throws ParseException {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        //先新增保存药物单信息
        PrescriptionEntity prescriptionEntity = eleRecordsFileListFrom.getPrescriptionEntity();
        prescriptionEntity.setCreateUserId(getUserId());
        prescriptionEntity.setCreateTime(formatter.parse(formatter.format(date)));
        prescriptionEntity.setPrescriptionTime(formatter.parse(formatter.format(date)));
        prescriptionEntity.setStatus("0");
        prescriptionEntity.setFlag(0);
        prescriptionService.save(prescriptionEntity);

        //获取药物单ID
        Long patientId = eleRecordsFileListFrom.getPrescriptionEntity().getPatientId();
        LambdaQueryWrapper<PrescriptionEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PrescriptionEntity::getPatientId,patientId)
                .eq(PrescriptionEntity::getFlag,0);
        PrescriptionEntity prescription = prescriptionService.getOne(wrapper);
        Long prescriptionId = prescription.getPrescriptionId();
        prescription.setFlag(1);
        prescriptionService.updateById(prescription);//把获取药物单标志改为以获取状态

        //新增保存患者病历信息
        eleRecordsFileListFrom.setPatientId(patientId);
        eleRecordsFileListFrom.setPrescriptionId(prescriptionId);
        eleRecordsFileListFrom.setCreateTime(formatter.parse(formatter.format(date)));
        eleRecordsFileListFrom.setCreateUserId(getUserId());
        eleRecordsService.save(eleRecordsFileListFrom);

        //病历图片文件新增保存
        Long relationId = eleRecordsFileListFrom.getEleRecordsId();
        List<SysFileEntity> fileList = eleRecordsFileListFrom.getFile();
        for (int i = 0; i < fileList.size(); i++) {
            fileList.get(i).setRelationId(relationId);
            fileList.get(i).setFileTime(formatter.parse(formatter.format(date)));
        }
        sysFileService.saveBatch(fileList);

        //新增保存药物单与药品关系信息
        List<DrugPreEntity> drugPreEntityList = eleRecordsFileListFrom.getDrugPreEntityList();
        drugPreEntityList = drugPreEntityList.stream().map((item)->{
            item.setPrescriptionId(prescriptionId);
            return item;
        }).collect(Collectors.toList());
        drugPreService.saveBatch(drugPreEntityList);

        //根据挂号或预约患者ID修改患者挂号或预约信息状态为已就诊状态
        if(eleRecordsFileListFrom.getIsGhBooking() == 0){
            SysGhEntity sysGhEntity = new SysGhEntity();
            sysGhEntity.setStatus(0);
            LambdaQueryWrapper<SysGhEntity> ghQueryWrapper = new LambdaQueryWrapper<>();
            ghQueryWrapper.eq(SysGhEntity::getPatientId,patientId);
            sysGhService.update(sysGhEntity,ghQueryWrapper);
        }else {
            SysBooking sysBooking = new SysBooking();
            sysBooking.setStatus(0);
            LambdaQueryWrapper<SysBooking> bookingQueryWrapper = new LambdaQueryWrapper<>();
            bookingQueryWrapper.eq(SysBooking::getPatientId,patientId);
            sysBookingService.update(sysBooking,bookingQueryWrapper);
        }

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public R update(@RequestBody EleRecordsFileListFrom eleRecordsFileListFrom) throws ParseException {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        //更新电子病历信息
        eleRecordsFileListFrom.setModifyTime(formatter.parse(formatter.format(date)));
        eleRecordsFileListFrom.setModifyUserId(getUserId());
        eleRecordsService.updateById(eleRecordsFileListFrom);

        //获取病历与文件的关系ID
        Long relationId = eleRecordsFileListFrom.getEleRecordsId();

        //根据病历与文件的关系ID更新与之关联文件信息
        List<SysFileEntity> fileList = eleRecordsFileListFrom.getFile();
        List<SysFileEntity> newFileList =new ArrayList<>();
        if(fileList!=null){
            for (int i = 0; i < fileList.size(); i++) {
                if(fileList.get(i).getFileName() != null){
                    newFileList.add(fileList.get(i));
                }
            }
            for (int i = 0; i < newFileList.size(); i++) {
                newFileList.get(i).setRelationId(relationId);
                newFileList.get(i).setFileTime(formatter.parse(formatter.format(date)));
            }
            sysFileService.saveBatch(newFileList);
        }

        //更新药物单信息
        PrescriptionEntity prescriptionEntity = eleRecordsFileListFrom.getPrescriptionEntity();
        prescriptionEntity.setModifyUserId(getUserId());
        prescriptionEntity.setModifyTime(formatter.parse(formatter.format(date)));
        prescriptionService.updateById(prescriptionEntity);

        //更新药物单与药品关系信息
        drugPreService.updateBatchById(eleRecordsFileListFrom.getDrugPreEntityList());
        return R.ok();
    }

}
