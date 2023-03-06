package itw.oralboot.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import itw.oralboot.modules.sys.dao.EleRecordsDao;
import itw.oralboot.modules.sys.entity.EleRecordsEntity;
import itw.oralboot.modules.sys.service.EleRecordsService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("EleRecordsService")
public class EleRecordsServiceImpl extends ServiceImpl<EleRecordsDao, EleRecordsEntity> implements EleRecordsService {

    @Autowired
    private EleRecordsDao eleRecordsDao;

    @Override
    public IPage<EleRecordsEntity> findPage(Integer current, Integer pageSize, String eleRecordsId, String patientName,String deptId, String isReferral, String starDate, String endDate) {
        IPage<EleRecordsEntity> page=new Page<>(current,pageSize);

        LambdaQueryWrapper<EleRecordsEntity> queryWrapper=new LambdaQueryWrapper<>();

        queryWrapper.like(Strings.isNotEmpty(eleRecordsId), EleRecordsEntity::getEleRecordsId, eleRecordsId)
                .like(Strings.isNotEmpty(patientName), EleRecordsEntity::getPatientName, patientName)
                .like(Strings.isNotEmpty(deptId), EleRecordsEntity::getDeptId, deptId)
                .eq(Strings.isNotEmpty(isReferral),EleRecordsEntity::getIsReferral,isReferral)
                .orderByDesc(EleRecordsEntity::getTreatmentTime);

        if(Strings.isNotEmpty(starDate) && Strings.isNotEmpty(endDate)){
            queryWrapper.between(EleRecordsEntity::getTreatmentTime,starDate,endDate);
        }
        eleRecordsDao.selectPage(page,queryWrapper);
        return page;
    }
}
