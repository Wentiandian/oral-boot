package itw.oralboot.modules.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import itw.oralboot.modules.sys.entity.OrdersEntity;

public interface OrdersService extends IService<OrdersEntity> {
    IPage<OrdersEntity> findPage(Integer current, Integer pageSize,String orderId, String prescriptionId, String patientId, String status, String starDate, String endDate);

}
