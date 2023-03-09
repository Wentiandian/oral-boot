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
     * 规格
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
