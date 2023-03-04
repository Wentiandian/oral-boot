package itw.oralboot.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
@TableName("sys_dept")
public class SysDept implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 挂号ID
     */
    @TableId
    private Long deptId;

    /**
     * 科室名称
     */
    private String deptName;

    /**
     * 医生人数
     */
    @TableId
    private Integer docNum;

    /**
     * 科室主任
     */
    private String director;

    /**
     * 科室房间
     */
    private String deptRoom;

    /**
     * 剩余可预约人数
     */
    private Integer residualNum;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 科室描述
     */
    private String description;

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
