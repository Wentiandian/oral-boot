package itw.oralboot.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import itw.oralboot.common.utils.R;
import itw.oralboot.modules.sys.entity.DrugPreEntity;
import itw.oralboot.modules.sys.entity.EleRecordsEntity;
import itw.oralboot.modules.sys.entity.PatientEntity;
import itw.oralboot.modules.sys.entity.PrescriptionEntity;
import itw.oralboot.modules.sys.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sys/hzgl")
public class PatientController extends AbstractController{

    @Autowired
    private PatientService patientService;

    @Autowired
    private EleRecordsService eleRecordsService;

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private DrugPreService drugPreService;

    @Autowired
    private SysDrugService sysDrugService;

    /**
     * 获取所有患者信息
     * @param params
     * @return
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        Integer current = Integer.parseInt(params.get("page").toString());
        Integer pageSize = Integer.parseInt(params.get("limit").toString());
        String patientId = (String) params.get("patientId");
        String patientName = (String) params.get("patientName");
        String status = (String) params.get("status");
        String starDate = (String) params.get("starDate");
        String endDate = (String) params.get("endDate");

        Page<PatientEntity> page = (Page<PatientEntity>) patientService.findPage(current,pageSize,patientId,patientName,status,starDate,endDate);
        return R.ok().put("list",page);
    }

    /**
     * 根据患者ID查询患者信息
     * @param patientId
     * @return
     */
    @GetMapping("/info/{patientId}")
    public R info(@PathVariable("patientId") Long patientId){
        //查询患者信息
        PatientEntity patient = patientService.getById(patientId);
        //查询患者对应的病历信息，获取电子病历列表
        LambdaQueryWrapper<EleRecordsEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(patientId!=null,EleRecordsEntity::getPatientId,patientId);
        List<EleRecordsEntity> eleRecordsEntityList = eleRecordsService.list(queryWrapper);
        eleRecordsEntityList.stream().map((item)->{
            //根据药物单号查询药物单
            Long prescriptionId = item.getPrescriptionId();
            PrescriptionEntity prescriptionEntity = prescriptionService.getById(prescriptionId);
            item.setPrescriptionEntity(prescriptionEntity);
            //根据药物单号查询药品信息
            LambdaQueryWrapper<DrugPreEntity> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(DrugPreEntity::getPrescriptionId,prescriptionId);
            List<DrugPreEntity> drugPreEntityList = drugPreService.list(wrapper);
            drugPreEntityList.stream().map((j)->{
                Long drugId = j.getDrugId();
                String drugName = sysDrugService.getById(drugId).getDrugName();
                j.setDrugName(drugName);
                return j;
            }).collect(Collectors.toList());
            item.setDrugPreEntityList(drugPreEntityList);
            return item;
        }).collect(Collectors.toList());
        return R.ok().put("info",patient).put("list",eleRecordsEntityList);
    }

    /**
     * 根据患者ID更新患者信息
     * @param patient
     * @return
     */
    @PostMapping("/update")
    public R update(@RequestBody PatientEntity patient) throws ParseException {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        patient.setModifyTime(formatter.parse(formatter.format(date)));
        patient.setModifyUserId(getUserId());
        patientService.updateById(patient);
        return R.ok();
    }

    /**
     * 根据患者Id删除患者信息
     * @param patientIds
     * @return
     */
    @PostMapping("/delete")
    public R delete(@RequestBody Long[] patientIds){
        patientService.removeByIds(Arrays.asList(patientIds));
        return R.ok();
    }
}
