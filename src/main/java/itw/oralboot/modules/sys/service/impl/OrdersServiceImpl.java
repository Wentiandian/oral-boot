package itw.oralboot.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import itw.oralboot.modules.sys.dao.OrdersDao;
import itw.oralboot.modules.sys.entity.OrdersEntity;
import itw.oralboot.modules.sys.service.OrdersService;
import org.springframework.stereotype.Service;

@Service("OrderService")
public class OrdersServiceImpl extends ServiceImpl<OrdersDao, OrdersEntity> implements OrdersService {
}
