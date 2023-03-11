package itw.oralboot.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("sys_drug")
public class SysDrugEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 药品ID
     */
    @TableId
    private Long drugId;

    /**
     * 药品名称
     */
    private String drugName;

    /**
     * 剂型
     */
    private String drugDosageForm;

    /**
     * 规格
     */
    private String drugSize;

    /**
     * 价格
     */
    private String price;

    /**
     * 图片名称
     */
    private String imageName;

    /**
     * 药品描述
     */
    private String description;

    /**
     * 库存
     */
    private Integer inventory;

    /**
     * 用法用量
     */
    private String usageDosage;

    /**
     * 存放地址
     */
    private String address;

    /**
     * 保质期
     */
    private String lifeTime;

    /**
     * 供应商
     */
    private String vendor;

    /**
     * 入库日期
     */
    private Date inboundTime;

    /**
     * 出库日期
     */
    private Date outboundTime;

    /**
     * 入库批次
     */
    private String inboundBatch;

    /**
     * 出库批次
     */
    private String outboundBatch;

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
