package itw.oralboot.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import itw.oralboot.modules.sys.dao.PatientDao;
import itw.oralboot.modules.sys.entity.PatientEntity;
import itw.oralboot.modules.sys.service.PatientService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("PatientService")
public class PatientServiceImpl extends ServiceImpl<PatientDao, PatientEntity> implements PatientService {

    @Autowired
    private PatientDao patientDao;

    @Override
    public IPage<PatientEntity> findPage(Integer current, Integer pageSize,String patientId,String patientName,String status,String starDate, String endDate) {
        //构造分页构造器
        IPage<PatientEntity> page=new Page<>(current,pageSize);

        //条件构造器
        LambdaQueryWrapper<PatientEntity> queryWrapper=new LambdaQueryWrapper<>();

        //添加过滤条件
        queryWrapper.like(Strings.isNotEmpty(patientId),PatientEntity::getPatientId,patientId)
                .like(Strings.isNotEmpty(patientName),PatientEntity::getPatientName,patientName)
                .eq(Strings.isNotEmpty(status),PatientEntity::getStatus,status)
                .between(Strings.isNotEmpty(starDate) && Strings.isNotEmpty(endDate), PatientEntity::getCreateTime,starDate,endDate)
                .orderByDesc(PatientEntity::getCreateTime);//添加排序条件

        patientDao.selectPage(page,queryWrapper);
        return page;
    }
}
