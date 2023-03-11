package itw.oralboot.modules.sys.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import itw.oralboot.common.utils.R;
import itw.oralboot.modules.sys.entity.SysDrugEntity;
import itw.oralboot.modules.sys.service.SysDrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * 药物信息管理
 */
@RestController
@RequestMapping("/sys/ypfl")
public class SysDrugController extends AbstractController{

    @Autowired
    private SysDrugService sysDrugService;

    /**
     * 查询所有药品
     * @param params
     * @return
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        Integer current = Integer.parseInt(params.get("page").toString());
        Integer pageSize = Integer.parseInt(params.get("limit").toString());
        String drugId = (String) params.get("drugId");
        String drugName = (String) params.get("drugName");
        String drugDosageForm = (String) params.get("drugDosageForm");
        String starDate = (String) params.get("starDate");
        String endDate = (String) params.get("endDate");

        Page<SysDrugEntity> sysDeptPage = (Page<SysDrugEntity>) sysDrugService.findPage(current,pageSize,drugId,drugName,drugDosageForm,"",starDate,endDate);
        return R.ok().put("list",sysDeptPage);
    }

    /**
     * 根据药品ID查询药品信息
     * @param drugId
     * @return
     */
    @GetMapping("/info/{drugId}")
    public R info(@PathVariable("drugId") Long drugId){
        SysDrugEntity sysDrugEntity = sysDrugService.getById(drugId);
        return R.ok().put("info",sysDrugEntity);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody SysDrugEntity sysDrugEntity) throws ParseException {
        sysDrugEntity.setCreateUserId(getUserId());
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        sysDrugEntity.setCreateTime(formatter.parse(formatter.format(date)));
        sysDrugService.save(sysDrugEntity);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public R update(@RequestBody SysDrugEntity sysDrugEntity) throws ParseException {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        sysDrugEntity.setModifyTime(formatter.parse(formatter.format(date)));
        sysDrugEntity.setModifyUserId(getUserId());
        sysDrugService.updateById(sysDrugEntity);
        return R.ok();
    }

    /**
     * 对药品信息进行删除
     * @param deptIds
     * @return
     */
    @PostMapping("/delete")
    public R deleteGh(@RequestBody Long[] deptIds){
        sysDrugService.removeByIds(Arrays.asList(deptIds));
        return R.ok();
    }
}
