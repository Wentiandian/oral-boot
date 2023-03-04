package itw.oralboot.modules.sys.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import itw.oralboot.common.annotation.SysLog;
import itw.oralboot.common.utils.R;
import itw.oralboot.common.validator.ValidatorUtils;
import itw.oralboot.common.validator.group.AddGroup;
import itw.oralboot.common.validator.group.UpdateGroup;
import itw.oralboot.modules.sys.entity.SysDept;
import itw.oralboot.modules.sys.entity.SysUserEntity;
import itw.oralboot.modules.sys.service.SysDeptService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * 科室信息管理
 */
@RestController
@RequestMapping("/sys/ksgl")
public class SysDeptController extends AbstractController{

    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 查询所有科室信息
     * @param params
     * @return
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        Integer current = Integer.parseInt(params.get("page").toString());
        Integer pageSize = Integer.parseInt(params.get("limit").toString());
        String deptId = (String) params.get("deptId");
        String deptName = (String) params.get("deptName");
        String director = (String) params.get("director");
        String starDate = (String) params.get("starDate");
        String endDate = (String) params.get("endDate");
        String status = (String) params.get("status");

        Page<SysDept> sysDeptPage = (Page<SysDept>) sysDeptService.findPage(current,pageSize,deptId,deptName,director,status,starDate,endDate);
        return R.ok().put("list",sysDeptPage);
    }

    /**
     * 根据科室ID查询科室消息
     * @param deptId
     * @return
     * @throws ParseException
     */
    @GetMapping("/info/{deptId}")
    public R getInfoById(@PathVariable("deptId") Long deptId) throws ParseException {

        SysDept sysDept =  sysDeptService.getById(deptId);

        return R.ok().put("info",sysDept);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody SysDept sysDept) throws ParseException {
        sysDept.setCreateUserId(getUserId());
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        sysDept.setCreateTime(formatter.parse(formatter.format(date)));
        sysDeptService.save(sysDept);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public R update(@RequestBody SysDept sysDept) throws ParseException {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        sysDept.setModifyTime(formatter.parse(formatter.format(date)));
        sysDept.setModifyUserId(getUserId());
        sysDeptService.updateById(sysDept);
        return R.ok();
    }

    /**
     * 对科室信息进行删除
     * @param deptIds
     * @return
     */
    @PostMapping("/delete")
    public R deleteGh(@RequestBody Long[] deptIds){
        sysDeptService.removeByIds(Arrays.asList(deptIds));
        return R.ok();
    }
}
