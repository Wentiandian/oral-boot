package itw.oralboot.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import itw.oralboot.common.utils.R;
import itw.oralboot.modules.sys.entity.EleRecordsEntity;
import itw.oralboot.modules.sys.entity.PatientEntity;
import itw.oralboot.modules.sys.entity.SysFileEntity;
import itw.oralboot.modules.sys.form.EleRecordsFileListFrom;
import itw.oralboot.modules.sys.service.EleRecordsService;
import itw.oralboot.modules.sys.service.PatientService;
import itw.oralboot.modules.sys.service.SysFileService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
     * 根据病历ID查询病历消息和文件信息
     * @param eleRecordsId
     * @return
     * @throws ParseException
     */
    @GetMapping("/info/{eleRecordsId}")
    public R getInfoById(@PathVariable("eleRecordsId") Long eleRecordsId) throws ParseException {

        EleRecordsEntity eleRecordsEntity  =  eleRecordsService.getById(eleRecordsId);

        LambdaQueryWrapper<SysFileEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysFileEntity::getRelationId,eleRecordsId);
        List<SysFileEntity> fileEntityList = sysFileService.list(queryWrapper);

        return R.ok().put("info",eleRecordsEntity).put("fileList",fileEntityList);
    }

    /**
     * 对病历信息进行删除
     * @param eleRecordsIds
     * @return
     */
    @PostMapping("/delete")
    public R deleteGh(@RequestBody Long[] eleRecordsIds){
        eleRecordsService.removeByIds(Arrays.asList(eleRecordsIds));
        return R.ok();
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody EleRecordsFileListFrom eleRecordsFileListFrom) throws ParseException {
        String patientName = eleRecordsFileListFrom.getPatientName();
        LambdaQueryWrapper<PatientEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Strings.isNotEmpty(patientName),PatientEntity::getPatientName,patientName);
        PatientEntity patient = patientService.getOne(queryWrapper);
        Long patientId = patient.getPatientId();
        eleRecordsFileListFrom.setPatientId(patientId);
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        eleRecordsFileListFrom.setCreateTime(formatter.parse(formatter.format(date)));
        eleRecordsFileListFrom.setCreateUserId(getUserId());
        eleRecordsService.save(eleRecordsFileListFrom);

        Long relationId = eleRecordsFileListFrom.getEleRecordsId();
        List<SysFileEntity> fileList = eleRecordsFileListFrom.getFile();
        for (int i = 0; i < fileList.size(); i++) {
            fileList.get(i).setRelationId(relationId);
        }
        sysFileService.saveBatch(fileList);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public R update(@RequestBody EleRecordsFileListFrom eleRecordsFileListFrom) throws ParseException {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        eleRecordsFileListFrom.setModifyTime(formatter.parse(formatter.format(date)));
        eleRecordsFileListFrom.setModifyUserId(getUserId());
        eleRecordsService.updateById(eleRecordsFileListFrom);

        Long relationId = eleRecordsFileListFrom.getEleRecordsId();
        List<SysFileEntity> fileList = eleRecordsFileListFrom.getFile();
        if(fileList!=null){
            for (int i = 0; i < fileList.size(); i++) {
                fileList.get(i).setRelationId(relationId);
            }
            sysFileService.saveBatch(fileList);
            logger.info(fileList.toString());
        }
        return R.ok();
    }
}
