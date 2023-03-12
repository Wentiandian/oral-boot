package itw.oralboot.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import itw.oralboot.modules.sys.dao.OrdersDao;
import itw.oralboot.modules.sys.entity.OrdersEntity;
import itw.oralboot.modules.sys.service.OrdersService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("OrderService")
public class OrdersServiceImpl extends ServiceImpl<OrdersDao, OrdersEntity> implements OrdersService {

    @Autowired
    private OrdersDao ordersDao;

    @Override
    public IPage<OrdersEntity> findPage(Integer current, Integer pageSize, String orderId, String prescriptionId, String patientId, String status, String starDate, String endDate) {
        IPage<OrdersEntity> page=new Page<>(current,pageSize);
        LambdaQueryWrapper<OrdersEntity> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.like(Strings.isNotEmpty(orderId),OrdersEntity::getOrderId,orderId)
                .like(Strings.isNotEmpty(prescriptionId), OrdersEntity::getPrescriptionId,prescriptionId)
                .like(Strings.isNotEmpty(patientId), OrdersEntity::getPatientId, patientId)
                .eq(Strings.isNotEmpty(status),OrdersEntity::getStatus,status)
                .between(Strings.isNotEmpty(starDate) && Strings.isNotEmpty(endDate),OrdersEntity::getOrderTime,starDate,endDate)
                .orderByDesc(OrdersEntity::getOrderTime);
        page = ordersDao.selectPage(page,queryWrapper);

        return page;
    }
}
