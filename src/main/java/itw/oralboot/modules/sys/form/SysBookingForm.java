package itw.oralboot.modules.sys.form;

import com.baomidou.mybatisplus.annotation.TableId;
import itw.oralboot.modules.sys.entity.SysBooking;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
public class SysBookingForm extends SysBooking implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long bookingId = super.getBookingId();

    private Long patientId = super.getPatientId();

    private Long docId = super.getDocId();

    private Long deptId = super.getDeptId();

    private Date bookingTime = super.getBookingTime();

    private Integer bookingNum = super.getBookingNum();

    private Integer status = super.getStatus();

    /**
     * 患者名称
     */
    private String patientName;

    /**
     * 科室名称
     */
    private String deptName;

    /**
     * 医生名称
     */
    private String name;

    /**
     * 患者手机
     */
    private Long mobile;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 年龄
     */
    private Integer age;

}
