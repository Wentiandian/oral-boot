package itw.oralboot.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import itw.oralboot.modules.sys.dao.SysDeptDao;
import itw.oralboot.modules.sys.entity.SysDept;
import itw.oralboot.modules.sys.service.SysDeptService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SysDeptService")
public class SysDeptServiceImpl extends ServiceImpl<SysDeptDao, SysDept> implements SysDeptService {

    @Autowired
    private SysDeptDao sysDeptDao;

    @Override
    public IPage<SysDept> findPage(Integer current, Integer pageSize, String deptId, String deptName, String director, String status, String starDate, String endDate) {

        IPage<SysDept> page=new Page<>(current,pageSize);
        LambdaQueryWrapper<SysDept> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.like(Strings.isNotEmpty(deptId), SysDept::getDeptId,deptId)
                .like(Strings.isNotEmpty(deptName), SysDept::getDeptName, deptName)
                .like(Strings.isNotEmpty(director), SysDept::getDirector, director)
                .eq(Strings.isNotEmpty(status),SysDept::getStatus,status)
                .between(Strings.isNotEmpty(starDate) && Strings.isNotEmpty(endDate),SysDept::getCreateTime,starDate,endDate)
                .orderByDesc(SysDept::getCreateTime);

        page = sysDeptDao.selectPage(page,queryWrapper);

        return page;
    }
}
