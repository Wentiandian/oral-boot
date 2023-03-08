package itw.oralboot.modules.sys.form;

import itw.oralboot.modules.sys.entity.SysFileEntity;
import lombok.Data;

import java.util.List;

@Data
public class SysFileListForm extends SysFileEntity {
    /**
     * 文件列表
     */
    private List<SysFileEntity> fileList;
}
