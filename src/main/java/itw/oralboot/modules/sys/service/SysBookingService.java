package itw.oralboot.modules.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import itw.oralboot.modules.sys.entity.SysBooking;
import itw.oralboot.modules.sys.form.SysBookingForm;

import java.util.List;

public interface SysBookingService extends IService<SysBooking> {

    List<SysBookingForm> finPage(Integer current, Integer pageSize, String patientName, String deptName, String name, String starDate, String endDate, String status);
}
