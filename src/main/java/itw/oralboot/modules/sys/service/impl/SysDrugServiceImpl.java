package itw.oralboot.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import itw.oralboot.modules.sys.dao.SysDrugDao;
import itw.oralboot.modules.sys.entity.SysDept;
import itw.oralboot.modules.sys.entity.SysDrugEntity;
import itw.oralboot.modules.sys.service.SysDrugService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SysDrugService")
public class SysDrugServiceImpl extends ServiceImpl<SysDrugDao, SysDrugEntity> implements SysDrugService {

    @Autowired
    private SysDrugDao sysDrugDao;

    @Override
    public IPage<SysDrugEntity> findPage(Integer current, Integer pageSize, String drugId, String drugName, String drugDosageForm, String starDate, String endDate) {
        IPage<SysDrugEntity> page=new Page<>(current,pageSize);
        LambdaQueryWrapper<SysDrugEntity> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.like(Strings.isNotEmpty(drugId), SysDrugEntity::getDrugId,drugId)
                .like(Strings.isNotEmpty(drugName), SysDrugEntity::getDrugName, drugName)
                .like(Strings.isNotEmpty(drugDosageForm), SysDrugEntity::getDrugDosageForm, drugDosageForm)
                .between(Strings.isNotEmpty(starDate) && Strings.isNotEmpty(endDate),SysDrugEntity::getCreateTime,starDate,endDate)
                .orderByDesc(SysDrugEntity::getCreateTime);

        page = sysDrugDao.selectPage(page,queryWrapper);

        return page;
    }
}
