package itw.oralboot.modules.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import itw.oralboot.common.utils.R;
import itw.oralboot.modules.sys.entity.SysBooking;
import itw.oralboot.modules.sys.service.SysBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/app/Yy")
public class UserBookingController extends AbstractController{

    @Autowired
    private SysBookingService sysBookingService;

    /**
     * 移动端用户预约
     * @param sysBooking
     * @return
     * @throws ParseException
     */
    @RequestMapping("/save")
    public R saveYyInfo(@RequestBody SysBooking sysBooking) throws ParseException {
        AbstractController.setCurrentId(sysBooking.getPatientId());
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        sysBooking.setCreateUserId(AbstractController.getCurrentId());
        sysBooking.setCreateTime(formatter.parse(formatter.format(date)));
        sysBooking.setStatus(1);
        sysBookingService.save(sysBooking);
        return R.ok();
    }

    /**
     * 检查是否已预约过
     * @param patientId
     * @return
     */
    @GetMapping("/info/{patientId}")
    public R getYyInfo(@PathVariable Long patientId){
        LambdaQueryWrapper<SysBooking> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(patientId != null,SysBooking::getPatientId,patientId)
                .eq(SysBooking::getStatus,1);
        SysBooking sysBooking = sysBookingService.getOne(queryWrapper);
        if(sysBooking != null){
            return R.error(200,"已预约过，无需再次预约");
        }else {
            return R.ok();
        }
    }
}
