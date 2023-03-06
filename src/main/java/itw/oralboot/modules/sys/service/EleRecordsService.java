package itw.oralboot.modules.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import itw.oralboot.modules.sys.entity.EleRecordsEntity;

public interface EleRecordsService extends IService<EleRecordsEntity> {

    IPage<EleRecordsEntity> findPage(Integer current, Integer pageSize,String eleRecordsId, String patientName,String deptId, String isReferral, String starDate, String endDate);

}
