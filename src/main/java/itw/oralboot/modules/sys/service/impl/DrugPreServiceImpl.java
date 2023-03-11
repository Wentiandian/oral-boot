package itw.oralboot.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import itw.oralboot.modules.sys.dao.DrugPreDao;
import itw.oralboot.modules.sys.entity.DrugPreEntity;
import itw.oralboot.modules.sys.service.DrugPreService;
import org.springframework.stereotype.Service;

@Service("DrugPreService")
public class DrugPreServiceImpl extends ServiceImpl<DrugPreDao, DrugPreEntity> implements DrugPreService {
}
