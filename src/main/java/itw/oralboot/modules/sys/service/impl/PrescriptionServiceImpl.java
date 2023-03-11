package itw.oralboot.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import itw.oralboot.modules.sys.dao.PrescriptionDao;
import itw.oralboot.modules.sys.entity.PrescriptionEntity;
import itw.oralboot.modules.sys.entity.SysDrugEntity;
import itw.oralboot.modules.sys.service.PrescriptionService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("PrescriptionService")
public class PrescriptionServiceImpl extends ServiceImpl<PrescriptionDao, PrescriptionEntity>implements PrescriptionService {

    @Autowired
    private PrescriptionDao prescriptionDao;

    @Override
    public IPage<PrescriptionEntity> findPage(Integer current, Integer pageSize, String prescriptionId, String patientId, String status, String starDate, String endDate) {
        IPage<PrescriptionEntity> page=new Page<>(current,pageSize);
        LambdaQueryWrapper<PrescriptionEntity> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.like(Strings.isNotEmpty(prescriptionId), PrescriptionEntity::getPrescriptionId,prescriptionId)
                .like(Strings.isNotEmpty(patientId), PrescriptionEntity::getPatientId, patientId)
                .eq(Strings.isNotEmpty(status),PrescriptionEntity::getStatus,status)
                .between(Strings.isNotEmpty(starDate) && Strings.isNotEmpty(endDate),PrescriptionEntity::getPrescriptionTime,starDate,endDate)
                .orderByDesc(PrescriptionEntity::getCreateTime);
        page = prescriptionDao.selectPage(page,queryWrapper);

        return page;
    }
}
