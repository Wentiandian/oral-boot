package itw.oralboot.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import itw.oralboot.common.utils.R;
import itw.oralboot.modules.sys.entity.*;
import itw.oralboot.modules.sys.form.RoleListForm;
import itw.oralboot.modules.sys.service.SysRoleService;
import itw.oralboot.modules.sys.service.SysUserRoleService;
import itw.oralboot.modules.sys.service.SysUserService;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sys/yhgl")
public class WorkerController extends AbstractController{

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysRoleService sysRoleService;


    /**
     * 获取医护所有信息
     * @param params
     * @return
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        Integer current = Integer.parseInt(params.get("page").toString());
        Integer pageSize = Integer.parseInt(params.get("limit").toString());
        String userId = (String) params.get("userId");
        String name = (String) params.get("name");
        String status = (String) params.get("status");
        String starDate = (String) params.get("starDate");
        String endDate = (String) params.get("endDate");

        Page<SysUserEntity> page = (Page<SysUserEntity>) userService.findPage(current,pageSize,userId,name,status,starDate,endDate);
        Page<SysUserEntity> sysUserEntityPage = new Page<>();
        List<RoleListForm> roleListForms = new ArrayList<>();
        //拷贝对象
        BeanUtils.copyProperties(page,sysUserEntityPage,"records");
        List<SysUserEntity> userList = page.getRecords();
        List<SysUserEntity> sysUserEntityList = userList.stream().map((item)->{
            Long userId1 = item.getUserId();
            LambdaQueryWrapper<SysUserRoleEntity> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(SysUserRoleEntity::getUserId,userId1);
            List<SysUserRoleEntity> sysUserRoleEntityList = sysUserRoleService.list(queryWrapper);
            List<Long> list = new ArrayList<>();
            for (int i = 0; i < sysUserRoleEntityList.size(); i++) {
                list.add(sysUserRoleEntityList.get(i).getRoleId());
            }
            RoleListForm roleListForm = new RoleListForm();
            roleListForm.setRoleEntityList(sysRoleService.listByIds(list));
            roleListForm.setUserId(userId1);
            roleListForms.add(roleListForm);
            item.setRoleIdList(list);
            return item;
        }).collect(Collectors.toList());

        sysUserEntityPage.setRecords(sysUserEntityList);

        return R.ok().put("list",sysUserEntityPage).put("role",roleListForms);
    }

    /**
     * 根据患者ID查询医护工作者信息
     * @param userId
     * @return
     */
    @GetMapping("/info/{userId}")
    public R info(@PathVariable("userId") Long userId){
        //获取用户信息
        SysUserEntity user = userService.getById(userId);
        LambdaQueryWrapper<SysUserRoleEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUserRoleEntity::getUserId,userId);
        List<SysUserRoleEntity> sysUserRoleEntityList = sysUserRoleService.list(queryWrapper);
        List roleIdList = new ArrayList<>();
        for (int i = 0; i < sysUserRoleEntityList.size(); i++) {
            roleIdList.add(sysUserRoleEntityList.get(i).getRoleId());
        }
        //对应的角色信息
        List<SysRoleEntity> roleEntityList = sysRoleService.listByIds(roleIdList);
        return R.ok().put("info",user)
                .put("roleList",roleEntityList);
    }

}
