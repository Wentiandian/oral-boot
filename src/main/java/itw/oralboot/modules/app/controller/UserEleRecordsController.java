package itw.oralboot.modules.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import itw.oralboot.common.utils.R;
import itw.oralboot.modules.sys.entity.DrugPreEntity;
import itw.oralboot.modules.sys.entity.EleRecordsEntity;
import itw.oralboot.modules.sys.entity.PrescriptionEntity;
import itw.oralboot.modules.sys.service.DrugPreService;
import itw.oralboot.modules.sys.service.EleRecordsService;
import itw.oralboot.modules.sys.service.PrescriptionService;
import itw.oralboot.modules.sys.service.SysDrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/app/Bl")
public class UserEleRecordsController extends AbstractController{

    @Autowired
    private EleRecordsService eleRecordsService;

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private DrugPreService drugPreService;

    @Autowired
    private SysDrugService sysDrugService;

    @GetMapping("/info/{patientId}")
    public R info(@PathVariable Long patientId){
        //获取电子病历列表
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

        return R.ok().put("list",eleRecordsEntityList);
    }
}
