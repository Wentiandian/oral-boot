package itw.oralboot.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import itw.oralboot.common.validator.group.AddGroup;
import itw.oralboot.common.validator.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@TableName("sys_gh")
public class SysGhEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 挂号ID
     */
    @TableId
    private Long ghId;

    /**
     * 患者ID
     */
    @TableId
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long patientId;

    /**
     * 状态  0：禁用   1：正常
     */
    private Integer status;

    /**
     * 挂号时间
     */
    private Date ghTime;

    /**
     * 患者名称
     */
    private String patientName;

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
