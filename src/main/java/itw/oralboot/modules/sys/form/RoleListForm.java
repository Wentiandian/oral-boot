package itw.oralboot.modules.sys.form;

import itw.oralboot.modules.sys.entity.SysRoleEntity;
import itw.oralboot.modules.sys.entity.SysUserEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RoleListForm {

    private Long userId;

    List<SysRoleEntity> roleEntityList = new ArrayList<>();
}
