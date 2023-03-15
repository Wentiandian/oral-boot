package itw.oralboot.common.ScheduleHandler;

import itw.oralboot.modules.sys.entity.SysBooking;
import itw.oralboot.modules.sys.entity.SysGhEntity;
import itw.oralboot.modules.sys.service.SysBookingService;
import itw.oralboot.modules.sys.service.SysGhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 挂号预约定时器，把挂号预约的过时未就诊的置为已过期
 */
@Component
public class GhBookingSchedule {

    @Autowired
    private SysGhService sysGhService;

    @Autowired
    private SysBookingService sysBookingService;

    /**
     * 每30分钟执行一次检查，挂号预约信息是否已过期
     * @throws ParseException
     */
    @Scheduled(cron = "0 30 * * * ?")
    public void handler() throws ParseException {
        //先从数据库拿去挂号与预约数据
        List<SysGhEntity> sysGhEntityList = sysGhService.list();
        List<SysBooking> sysBookingList = sysBookingService.list();
        //获取当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        Date nowTime = formatter.parse(formatter.format(date));
        //检查挂号时间是否超过24小时
        for (int i = 0; i < sysGhEntityList.size(); i++) {
            try{
                date = sdf.parse(sysGhEntityList.get(i).getGhTime().toString());
            }catch (Exception e){
                e.printStackTrace();
            }
            String ghTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
            Date ghTime = formatter.parse(ghTimeStr);
            long cha = nowTime.getTime() - ghTime.getTime();
            double result = cha * 1.0 / (1000 * 60 * 60);
            if(cha > 0 && result > 24){
                sysGhEntityList.get(i).setStatus(2);
                sysGhService.updateById(sysGhEntityList.get(i));//更新挂号信息状态为2:已过期
            }
        }
        //检查预约时间是否已过
        for (int i = 0; i < sysBookingList.size(); i++) {
            try{
                date = sdf.parse(sysBookingList.get(i).getBookingTime().toString());
            }catch (Exception e){
                e.printStackTrace();
            }
            String bookingTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
            Date bookingTime = formatter.parse(bookingTimeStr);
            if(bookingTime.before(nowTime)){//如果预约时间在当前时间之前
                sysBookingList.get(i).setStatus(2);
                sysBookingService.updateById(sysBookingList.get(i));//更新预约信息状态为2:已过期
            }
        }
    }
}
