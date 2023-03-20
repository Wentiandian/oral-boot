package itw.oralboot.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@TableName("ele_records")
public class EleRecordsEntity implements Serializable {

    /**
     * 病历编号
     */
    @TableId
    private Long eleRecordsId;

    /**
     * 病人ID
     */
    @TableId
    private Long patientId;

    /**
     * 医生名称
     */
    private String docName;

    /**
     * 护士名称
     */
    private String nurseName;

    /**
     * 患者名称
     */
    private String patientName;

    /**
     * 治疗科室
     */
    private Long deptId;

    /**
     * 是否复诊
     */
    private Integer isReferral;

    /**
     * 第几次就诊
     */
    private Integer treatmentNum;

    /**
     * 就诊时间
     */
    private String treatmentTime;

    /**
     * 诊断描述
     */
    private String treatmentDescription;

    /**
     * 治疗方法
     */
    private String treatmentMethod;

    /**
     * 治疗过程
     */
    private String treatmentDuring;

    /**
     * 医嘱
     */
    private String docOrders;

    /**
     * 药物单号
     */
    @TableId
    private Long prescriptionId;

    /**
     * 对应药物单
     */
    @TableField(exist=false)
    private PrescriptionEntity prescriptionEntity;

    /**
     * 对应药品
     */
    @TableField(exist=false)
    private List<DrugPreEntity> drugPreEntityList;

    /**
     * 0：挂号   1：预约
     */
    private Integer isGhBooking;

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
