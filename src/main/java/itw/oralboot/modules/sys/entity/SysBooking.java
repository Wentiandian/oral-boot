package itw.oralboot.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
@TableName("sys_booking")
public class SysBooking implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 预约ID
     */
    @TableId
    private Long bookingId;

    /**
     * 病人ID
     */
    @TableId
    private Long patientId;

    /**
     * 医生ID
     */
    @TableId
    private Long docId;

    /**
     * 科室ID
     */
    @TableId
    private Long deptId;

    /**
     * 预约时间
     */
    private Date bookingTime;

    /**
     * 预约人数
     */
    private Integer bookingNum;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建者ID
     */
    private Long createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人ID
     */
    private Long modifyUserId;

    /**
     * 修改时间
     */
    private Date modifyTime;
}
