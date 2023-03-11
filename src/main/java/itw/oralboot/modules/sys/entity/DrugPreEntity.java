package itw.oralboot.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;

@Data
@TableName("drug_pre")
public class DrugPreEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 药物单和药品关系ID
     */
    @TableId
    private Long drugPreId;

    /**
     * 药物单ID
     */
    private Long prescriptionId;

    /**
     * 药品ID
     */
    private Long drugId;

    /**
     *药品数量
     */
    private Integer drugNum;

}
