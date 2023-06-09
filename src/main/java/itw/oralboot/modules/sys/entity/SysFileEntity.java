package itw.oralboot.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@TableName("sys_file")
public class SysFileEntity implements Serializable {

    /**
     * 文件编号
     */
    @TableId
    private Long fileId;

    /**
     * 关系表ID
     */
    @TableId
    private Long relationId;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件地址
     */
    private String url;

    /**
     * 文件大小
     */
    private double size;

    /**
     * 文件类型
     */
    private Integer status;

    /**
     * 文件上传时间
     */
    private Date fileTime;
}
