package itw.oralboot.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
@TableName("prescription")
public class PrescriptionEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 药物单ID
     */
    @TableId
    private Long prescriptionId;

    /**
     * 医院名称
     */
    private String hospital;

    /**
     * 病人ID
     */
    private Long patientId;

    /**
     * 科室ID
     */
    private Long deptId;

    /**
     * 开处方时间
     */
    private Date prescriptionTime;

    /**
     * 使用方法
     */
    private String useMethod;

    /**
     * 医生
     */
    private String docName;

    /**
     * 药剂师
     */
    private String pharmacist;

    /**
     * 计价员
     */
    private String estimator;

    /**
     * 状态
     */
    private String status;

    /**
     * 总价
     */
    private String sum;

    /**
     * 用来标记获取药物单ID   0：未获取到   1：已获取到
     */
    private Integer flag;

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
