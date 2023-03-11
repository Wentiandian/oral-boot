package itw.oralboot.modules.sys.controller;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import itw.oralboot.common.utils.R;
import itw.oralboot.modules.sys.entity.DrugPreEntity;
import itw.oralboot.modules.sys.entity.OrdersEntity;
import itw.oralboot.modules.sys.entity.PrescriptionEntity;
import itw.oralboot.modules.sys.entity.SysDrugEntity;
import itw.oralboot.modules.sys.service.DrugPreService;
import itw.oralboot.modules.sys.service.OrdersService;
import itw.oralboot.modules.sys.service.PrescriptionService;
import itw.oralboot.modules.sys.service.SysDrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 药物单信息管理
 */
@RestController
@RequestMapping("/sys/ywd")
public class PrescriptionController extends AbstractController{

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private DrugPreService drugPreService;

    @Autowired
    private SysDrugService sysDrugService;

    @Autowired
    private OrdersService orderService;

    /**
     * 查询药物单所有信息
     * @param params
     * @return
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        Integer current = Integer.parseInt(params.get("page").toString());
        Integer pageSize = Integer.parseInt(params.get("limit").toString());
        String prescriptionId = (String) params.get("prescriptionId");
        String patientId = (String) params.get("patientId");
        String status = (String) params.get("status");
        String starDate = (String) params.get("starDate");
        String endDate = (String) params.get("endDate");
        Page<PrescriptionEntity> page = (Page<PrescriptionEntity>) prescriptionService.findPage(current,pageSize,prescriptionId,patientId,status,starDate,endDate);
        return R.ok().put("list",page);
    }


    /**
     * 根据药物单ID查询药物单信息与对应药品信息
     * @param prescriptionId
     * @return
     */
    @GetMapping("/info/{prescriptionId}")
    public R info(@PathVariable("prescriptionId") Long prescriptionId){
        PrescriptionEntity prescriptionEntity = prescriptionService.getById(prescriptionId);
        LambdaQueryWrapper<DrugPreEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DrugPreEntity::getPrescriptionId,prescriptionId);
        List<DrugPreEntity> drugPreEntityList = drugPreService.list(queryWrapper);
        List<SysDrugEntity> sysDrugEntityList = drugPreEntityList.stream().map((item)->{
            SysDrugEntity sysDrugEntity = new SysDrugEntity();
            Long drugId = item.getDrugId();
            Integer num = item.getDrugNum();
            sysDrugEntity = sysDrugService.getById(drugId);
            sysDrugEntity.setInventory(num);//把药品数量放进药品的库存中
            return sysDrugEntity;
        }).collect(Collectors.toList());

        return R.ok().put("info",prescriptionEntity).put("drugList",sysDrugEntityList);
    }

    /**
     * 对药物单表进行修改信息和更新状态，、生成（保存）订单
     */
    @PostMapping("/update")
    public R update(@RequestBody PrescriptionEntity prescriptionEntity) throws ParseException {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());

        logger.info(prescriptionEntity.toString());
        //生成（保存）订单
        OrdersEntity orderEntity = new OrdersEntity();
        orderEntity.setPrescriptionId(prescriptionEntity.getPrescriptionId());
        orderEntity.setPatientId(prescriptionEntity.getPatientId());
        orderEntity.setSum(prescriptionEntity.getSum());
        orderEntity.setOrderTime(formatter.parse(formatter.format(date)));
        orderEntity.setStatus("0");
        orderEntity.setCreateTime(formatter.parse(formatter.format(date)));
        orderEntity.setCreateUserId(getUserId());
        logger.info(orderEntity.toString());
        orderService.save(orderEntity);

        //更新药物单信息
        prescriptionEntity.setModifyTime(formatter.parse(formatter.format(date)));
        prescriptionEntity.setModifyUserId(getUserId());
        prescriptionEntity.setStatus("1");
        prescriptionService.updateById(prescriptionEntity);
        return R.ok();
    }
}
