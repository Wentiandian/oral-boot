package itw.oralboot.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import itw.oralboot.common.validator.group.AddGroup;
import itw.oralboot.common.validator.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@TableName("patient")
public class PatientEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 患者ID
     */
    @TableId
    private Long patientId;

    /**
     * 患者名
     */
    @NotBlank(message="患者名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String patientName;

    /**
     * 性别
     */
    private String sex;

    /**
     * 邮箱
     */
    @NotBlank(message="邮箱不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @Email(message="邮箱格式不正确", groups = {AddGroup.class, UpdateGroup.class})
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 年龄
     */
    private String age;

    /**
     * 状态  0：禁用   1：正常
     */
    private Integer status;

    /**
     * 角色ID列表
     */
    @TableField(exist=false)
    private List<Long> roleIdList;

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
