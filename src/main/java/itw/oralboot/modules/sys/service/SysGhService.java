package itw.oralboot.modules.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import itw.oralboot.modules.sys.entity.SysGhEntity;


public interface SysGhService extends IService<SysGhEntity> {

    IPage<SysGhEntity> findPage(Integer current, Integer pageSize, String patientName, String starDate, String endDate, String status);
}
