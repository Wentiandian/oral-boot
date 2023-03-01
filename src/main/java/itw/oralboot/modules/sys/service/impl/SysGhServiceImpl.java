package itw.oralboot.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import itw.oralboot.modules.sys.dao.SysGhDao;
import itw.oralboot.modules.sys.entity.SysGhEntity;
import itw.oralboot.modules.sys.service.SysGhService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SysGhService")
public class SysGhServiceImpl extends ServiceImpl<SysGhDao, SysGhEntity> implements SysGhService {

    @Autowired
    private SysGhDao sysGhDao;

   /* @Override
    public List<SysGhEntity> queryPage(Map<String, Object> params) {
        String patient_name = (String)params.get("patient_name");

        *//*List<SysGhEntity> sysGhEntityList= sysGhDao.queryGhList(patient_name);*//*

        return null;
    }*/

    @Override
    public IPage<SysGhEntity> findPage(Integer current, Integer pageSize, String patientName, String starDate, String endDate, String status) {

        //构造分页构造器
        IPage<SysGhEntity> page=new Page<>(current,pageSize);

        //条件构造器
        LambdaQueryWrapper<SysGhEntity> queryWrapper=new LambdaQueryWrapper<>();

        queryWrapper.like(Strings.isNotEmpty(patientName), SysGhEntity::getPatientName, patientName);

        if(Strings.isNotEmpty(starDate) && Strings.isNotEmpty(endDate)){
            queryWrapper.between(SysGhEntity::getGhTime,starDate,endDate);
        }

        if(Strings.isNotEmpty(status)) {
            queryWrapper.eq(SysGhEntity::getStatus,status);
        }
        //添加排序条件
        queryWrapper.orderByDesc(SysGhEntity::getGhTime);

        sysGhDao.selectPage(page,queryWrapper);
        return page;
    }
}
