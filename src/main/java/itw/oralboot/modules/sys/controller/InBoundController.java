package itw.oralboot.modules.sys.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import itw.oralboot.common.utils.R;
import itw.oralboot.modules.sys.entity.SysDrugEntity;
import itw.oralboot.modules.sys.service.SysDrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 药品入库信息管理
 */
@RestController
@RequestMapping("/sys/ypcrk")
public class InBoundController extends AbstractController{

    @Autowired
    private SysDrugService sysDrugService;

    /**
     * 查询所有入库药品
     * @param params
     * @return
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        Integer current = Integer.parseInt(params.get("page").toString());
        Integer pageSize = Integer.parseInt(params.get("limit").toString());
        String drugId = (String) params.get("drugId");
        String drugName = (String) params.get("drugName");
        String starDate = (String) params.get("starDate");
        String endDate = (String) params.get("endDate");

        Page<SysDrugEntity> sysDeptPage = (Page<SysDrugEntity>) sysDrugService.findPage(current,pageSize,drugId,drugName,"","1",starDate,endDate);
        return R.ok().put("list",sysDeptPage);
    }

    /**
     * 对入库药品信息进行删除
     * @param drugIds
     * @return
     */
    @PostMapping("/delete")
    public R deleteGh(@RequestBody Long[] drugIds){
        List<SysDrugEntity> sysDrugEntityList = new ArrayList<>();
        for (int i = 0; i < drugIds.length; i++) {
            SysDrugEntity sysDrugEntity = new SysDrugEntity();
            sysDrugEntity.setDrugId(drugIds[i]);
            sysDrugEntity.setStatus(0);
            sysDrugEntityList.add(sysDrugEntity);
        }
        sysDrugService.updateBatchById(sysDrugEntityList);
        return R.ok();
    }

    /**
     * 新增药品入库/入库/出库
     */
    @PostMapping("/save/{functions}")
    public R save(@RequestBody SysDrugEntity sysDrugEntity,@PathVariable("functions") String functions) throws ParseException {
        Long drugId = sysDrugEntity.getDrugId();
        SysDrugEntity drug = sysDrugService.getById(drugId);
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        if(functions.equals("0")){
            sysDrugEntity.setCreateUserId(getUserId());
            sysDrugEntity.setCreateTime(formatter.parse(formatter.format(date)));
            sysDrugEntity.setStatus(1);
            int num = sysDrugEntity.getInventory();
            sysDrugEntity.setInventory(drug.getInventory() + num);
        }else if(functions.equals("1")){
            sysDrugEntity.setModifyUserId(getUserId());
            sysDrugEntity.setModifyTime(formatter.parse(formatter.format(date)));
            int num = sysDrugEntity.getInventory();
            sysDrugEntity.setInventory(drug.getInventory() + num);
        }else if(functions.equals("2")){
            sysDrugEntity.setModifyUserId(getUserId());
            sysDrugEntity.setModifyTime(formatter.parse(formatter.format(date)));
            int num = sysDrugEntity.getInventory();
            sysDrugEntity.setInventory(drug.getInventory() - num);
        }
        sysDrugService.updateById(sysDrugEntity);
        return R.ok();
    }
}
