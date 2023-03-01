package itw.oralboot.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("doc_dept")
public class DocDept implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 关系ID
     */
    @TableId
    private Long docDeptId;

    /**
     * 医生ID
     */
    private Long docId;

    /**
     * 科室ID
     */
    @TableId
    private Long deptId;

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
