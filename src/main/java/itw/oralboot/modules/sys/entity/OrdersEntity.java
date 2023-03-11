package itw.oralboot.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
@TableName("orders")
public class OrdersEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    @TableId
    private Long orderId;

    /**
     * 药物单ID
     */
    private Long prescriptionId;

    /**
     * 患者ID
     */
    private Long patientId;

    /**
     *总价
     */
    private String sum;

    /**
     *订单时间
     */
    private Date orderTime;

    /**
     *状态
     */
    private String status;

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
