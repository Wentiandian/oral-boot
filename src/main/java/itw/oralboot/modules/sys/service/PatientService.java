package itw.oralboot.modules.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import itw.oralboot.modules.sys.entity.PatientEntity;

public interface PatientService extends IService<PatientEntity> {

    IPage<PatientEntity> findPage(Integer current, Integer pageSize,String patientId,String patientName,String status,String starDate, String endDate);
}
