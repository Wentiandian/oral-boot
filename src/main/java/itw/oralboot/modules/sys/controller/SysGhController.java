package itw.oralboot.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import itw.oralboot.common.utils.R;
import itw.oralboot.modules.sys.entity.PatientEntity;
import itw.oralboot.modules.sys.entity.SysGhEntity;
import itw.oralboot.modules.sys.form.GhPatientForm;
import itw.oralboot.modules.sys.service.PatientService;
import itw.oralboot.modules.sys.service.SysGhService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 挂号页面处理
 */
@RestController
@RequestMapping("/sys/gh")
public class SysGhController extends AbstractController{
    @Autowired
    private SysGhService sysGhService;

    @Autowired
    private PatientService patientService;

    /**
     * 对患者表和挂号表进行操作，查询所有患者挂号信息
     * @param params
     * @return
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        Integer current = Integer.parseInt(params.get("page").toString());
        Integer pageSize = Integer.parseInt(params.get("limit").toString());
        String patientName = (String) params.get("patientName");
        String starDate = (String) params.get("starDate");
        String endDate = (String) params.get("endDate");
        String status = (String) params.get("status");
        Page<SysGhEntity> patientEntityPage = (Page) sysGhService.findPage(current,pageSize,patientName,starDate,endDate,status);
        Page<GhPatientForm> ghPatientFormPage = new Page<>();
        //拷贝对象
        BeanUtils.copyProperties(patientEntityPage,ghPatientFormPage,"records");
        List<SysGhEntity> records = patientEntityPage.getRecords();

        List<GhPatientForm> ghPatientFormList =records.stream().map((item)->{
            GhPatientForm ghPatientForm = new GhPatientForm();
            BeanUtils.copyProperties(item,ghPatientForm);
            Long patientId = item.getPatientId();
            //根据患者ID查询患者信息
            LambdaQueryWrapper<PatientEntity> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(PatientEntity::getPatientId,patientId);
            PatientEntity patientEntity = patientService.getOne(queryWrapper);
            ghPatientForm.setPatientEntity(patientEntity);
            return ghPatientForm;
        }).collect(Collectors.toList());

        ghPatientFormPage.setRecords(ghPatientFormList);
        return R.ok().put("list",ghPatientFormPage);
    }

    /**
     * 根据挂号Id修改就诊状态
     * @param ghId
     * @return
     * @throws ParseException
     */
    @GetMapping("/status/{ghId}")
    public R updateGh(@PathVariable("ghId") Long ghId) throws ParseException {
        SysGhEntity sysGhEntity = new SysGhEntity();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        sysGhEntity.setModifyTime(formatter.parse(formatter.format(date)));
        sysGhEntity.setModifyUserId(getUserId());
        sysGhEntity.setGhId(ghId);
        sysGhEntity.setStatus(0);
        sysGhService.updateById(sysGhEntity);
        return R.ok();
    }

    /**
     * 对挂号信息进行删除
     * @param ghIds
     * @return
     */
    @PostMapping("/delete")
    public R deleteGh(@RequestBody Long[] ghIds){
        sysGhService.removeByIds(Arrays.asList(ghIds));
        return R.ok();
    }
}
