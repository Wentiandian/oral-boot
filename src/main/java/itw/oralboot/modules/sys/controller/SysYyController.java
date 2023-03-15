package itw.oralboot.modules.sys.controller;

import itw.oralboot.common.utils.R;
import itw.oralboot.modules.sys.entity.SysBooking;
import itw.oralboot.modules.sys.form.SysBookingForm;
import itw.oralboot.modules.sys.service.DocDeptService;
import itw.oralboot.modules.sys.service.SysBookingService;
import itw.oralboot.modules.sys.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 预约页面处理
 */
@RestController
@RequestMapping("/sys/yy")
public class SysYyController extends AbstractController{

    @Autowired
    private SysBookingService sysBookingService;


    /**
     * 查看所有预约信息
     * @param params
     * @return
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        Integer current = Integer.parseInt(params.get("page").toString());
        Integer pageSize = Integer.parseInt(params.get("limit").toString());
        String patientName = (String) params.get("patientName");
        String deptName = (String) params.get("deptName");
        String name = (String) params.get("name");
        String starDate = (String) params.get("starDate");
        String endDate = (String) params.get("endDate");
        String status = (String) params.get("status");

        List<SysBookingForm> sysBookingFormList = sysBookingService.finPage(current,pageSize,patientName,deptName,name,starDate,endDate,status);
        return R.ok().put("list",sysBookingFormList);
    }

    /**
     * 根据预约Id修改就诊状态
     * @param bookingId
     * @return
     * @throws ParseException
     */
    @GetMapping("/status/{bookingId}")
    public R updateGh(@PathVariable("bookingId") Long bookingId) throws ParseException {
        SysBooking sysBooking = new SysBooking();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        sysBooking.setModifyTime(formatter.parse(formatter.format(date)));
        sysBooking.setModifyUserId(getUserId());
        sysBooking.setBookingId(bookingId);
        sysBooking.setStatus(0);
        sysBookingService.updateById(sysBooking);
        return R.ok();
    }

    /**
     * 对预约信息进行删除
     * @param bookingIds
     * @return
     */
    @PostMapping("/delete")
    public R deleteGh(@RequestBody Long[] bookingIds){
        sysBookingService.removeByIds(Arrays.asList(bookingIds));
        return R.ok();
    }
}
