package itw.oralboot.modules.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import itw.oralboot.modules.sys.entity.PrescriptionEntity;

public interface PrescriptionService extends IService<PrescriptionEntity> {

    IPage<PrescriptionEntity> findPage(Integer current, Integer pageSize, String prescriptionId, String patientId, String status, String starDate, String endDate);

}
