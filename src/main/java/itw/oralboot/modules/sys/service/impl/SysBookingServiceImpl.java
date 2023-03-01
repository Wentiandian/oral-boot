package itw.oralboot.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import itw.oralboot.modules.sys.dao.SysBookingDao;
import itw.oralboot.modules.sys.entity.SysBooking;
import itw.oralboot.modules.sys.service.SysBookingService;
import org.springframework.stereotype.Service;

@Service("SysBookingService")
public class SysBookingServiceImpl extends ServiceImpl<SysBookingDao, SysBooking> implements SysBookingService {

}
