package itw.oralboot.modules.sys.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import itw.oralboot.common.utils.R;
import itw.oralboot.modules.sys.entity.*;
import itw.oralboot.modules.sys.service.OrdersService;
import itw.oralboot.modules.sys.service.PatientService;
import itw.oralboot.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * 订单信息管理
 */
@RestController
@RequestMapping("/sys/jfgl")
public class OrdersController extends AbstractController{

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private SysUserService sysUserService;

    /**
     * 获取所有订单信息
     * @param params
     * @return
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        Integer current = Integer.parseInt(params.get("page").toString());
        Integer pageSize = Integer.parseInt(params.get("limit").toString());
        String orderId = (String) params.get("orderId");
        String prescriptionId = (String) params.get("prescriptionId");
        String patientId = (String) params.get("patientId");
        String status = (String) params.get("status");
        String starDate = (String) params.get("starDate");
        String endDate = (String) params.get("endDate");

        Page<OrdersEntity> page = (Page<OrdersEntity>) ordersService.findPage(current,pageSize,orderId,prescriptionId,patientId,status,starDate,endDate);

        return R.ok().put("list",page);
    }

    /**
     * 根据订单ID查询订单信息
     * @param orderId
     * @return
     */
    @GetMapping("/info/{orderId}")
    public R info(@PathVariable("orderId") Long orderId){
        OrdersEntity ordersEntity = ordersService.getById(orderId);
        //获取患者ID
        Long patientId = ordersEntity.getPatientId();
        //获取创建人ID
        Long userId = ordersEntity.getCreateUserId();
        //获取患者名称
        PatientEntity patient = patientService.getById(patientId);
        //获取创建人名
        SysUserEntity user = sysUserService.getById(userId);
        return R.ok().put("info",ordersEntity)
                .put("patient",patient)
                .put("user",user);
    }

    /**
     * 删除订单信息
     * @param orderIds
     * @return
     */
    @PostMapping("/delete")
    public R delete(@RequestBody Long[] orderIds){
        ordersService.removeByIds(Arrays.asList(orderIds));
        return R.ok();
    }
}
