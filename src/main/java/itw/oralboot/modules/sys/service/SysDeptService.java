package itw.oralboot.modules.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import itw.oralboot.modules.sys.entity.SysDept;

public interface SysDeptService extends IService<SysDept> {

    IPage<SysDept> findPage(Integer current, Integer pageSize, String deptId, String deptName, String director, String status, String starDate, String endDate);

}
