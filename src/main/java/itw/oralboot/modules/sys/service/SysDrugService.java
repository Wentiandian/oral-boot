package itw.oralboot.modules.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import itw.oralboot.modules.sys.entity.SysDrugEntity;

public interface SysDrugService extends IService<SysDrugEntity> {
    IPage<SysDrugEntity> findPage(Integer current, Integer pageSize, String drugId, String drugName, String drugDosageForm, String starDate, String endDate);

}
