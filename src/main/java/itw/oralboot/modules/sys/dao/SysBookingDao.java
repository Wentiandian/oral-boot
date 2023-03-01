package itw.oralboot.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import itw.oralboot.modules.sys.entity.SysBooking;
import itw.oralboot.modules.sys.form.SysBookingForm;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 预约信息
 */
@Mapper
public interface SysBookingDao extends BaseMapper<SysBooking> {

    /**
     * 查询预约表和患者表、科室表、用户表信息列表(无状态参数)
     * @return
     */
    @Results({
            @Result(column = "booking_id", property = "bookingId"),
            @Result(column = "patient_id", property = "patientId"),
            @Result(column = "user_id", property = "docId"),
            @Result(column = "dept_id", property = "deptId"),
            @Result(column = "booking_time", property = "bookingTime"),
            @Result(column = "booking_num", property = "bookingNum"),
            @Result(column = "status", property = "status"),
            @Result(column = "patient_name", property = "patientName"),
            @Result(column = "dept_name", property = "deptName"),
            @Result(column = "name", property = "name"),
            @Result(column = "mobile", property = "mobile"),
            @Result(column = "sex", property = "sex"),
            @Result(column = "age", property = "age")
    })
    @Select("select * from sys_booking,patient,sys_dept,sys_user where sys_booking.patient_id = patient.patient_id and sys_booking.dept_id = sys_dept.dept_id and sys_booking.doc_id = sys_user.user_id and patient.patient_name like #{patientName} and sys_dept.dept_name like #{deptName} and sys_user.name like #{name} and date_format(booking_time, '%Y-%m-%d') between #{starDate} and #{endDate} limit #{current},#{pageSize};")
    List<SysBookingForm> queryBookingList (Integer current, Integer pageSize, String patientName, String deptName, String name, String starDate, String endDate);

    /**
     * 查询预约表和患者表、科室表、用户表信息列表(有状态参数)
     * @return
     */
    @Results({
            @Result(column = "booking_id", property = "bookingId"),
            @Result(column = "patient_id", property = "patientId"),
            @Result(column = "user_id", property = "docId"),
            @Result(column = "dept_id", property = "deptId"),
            @Result(column = "booking_time", property = "bookingTime"),
            @Result(column = "booking_num", property = "bookingNum"),
            @Result(column = "status", property = "status"),
            @Result(column = "patient_name", property = "patientName"),
            @Result(column = "dept_name", property = "deptName"),
            @Result(column = "name", property = "name"),
            @Result(column = "mobile", property = "mobile"),
            @Result(column = "sex", property = "sex"),
            @Result(column = "age", property = "age")
    })
    @Select("select * from sys_booking,patient,sys_dept,sys_user where sys_booking.patient_id = patient.patient_id and sys_booking.dept_id = sys_dept.dept_id and sys_booking.doc_id = sys_user.user_id and patient.patient_name like #{patientName} and sys_dept.dept_name like #{deptName} and sys_user.name like #{name} and sys_booking.status = #{status} and date_format(booking_time, '%Y-%m-%d') between #{starDate} and #{endDate} limit #{current},#{pageSize};")
    List<SysBookingForm> queryBookingStatusList (Integer current, Integer pageSize, String patientName, String deptName, String name, String starDate, String endDate, String status);

}
