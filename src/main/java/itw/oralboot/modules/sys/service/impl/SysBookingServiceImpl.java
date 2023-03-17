package itw.oralboot.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import itw.oralboot.modules.sys.dao.SysBookingDao;
import itw.oralboot.modules.sys.entity.SysBooking;
import itw.oralboot.modules.sys.form.SysBookingForm;
import itw.oralboot.modules.sys.service.SysBookingService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SysBookingService")
public class SysBookingServiceImpl extends ServiceImpl<SysBookingDao, SysBooking> implements SysBookingService {

    @Autowired
    SysBookingDao sysBookingDao;

    /**
     * 查询所有预约信息涉及预约表、患者表、科室表、用户表
     * @param current
     * @param pageSize
     * @param patientName
     * @param deptName
     * @param name
     * @param starDate
     * @param endDate
     * @param status
     * @return
     */
    @Override
    public List<SysBookingForm> finPage(Integer current, Integer pageSize, String patientName, String deptName, String name, String starDate, String endDate, String status) {

        if(!Strings.isNotEmpty(starDate) && !Strings.isNotEmpty(endDate)){
            starDate = "1000-01-01";
            endDate = "9999-12-31";
        }

        List<SysBookingForm> sysBookingFormList;
        if(status == null || status.equals("")){
            sysBookingFormList = sysBookingDao.queryBookingList(current - 1, pageSize, "%" + patientName + "%", "%" + deptName + "%", "%" + name + "%", starDate, endDate);
        }else {
            sysBookingFormList = sysBookingDao.queryBookingStatusList(current - 1, pageSize, "%" + patientName + "%", "%" + deptName + "%", "%" + name + "%", starDate, endDate, status);
        }
        return sysBookingFormList;
    }
}
