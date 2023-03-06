package itw.oralboot.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import itw.oralboot.modules.sys.dao.SysFileDao;
import itw.oralboot.modules.sys.entity.SysFileEntity;
import itw.oralboot.modules.sys.service.SysFileService;
import org.springframework.stereotype.Service;

@Service("SysFileService")
public class SysFileServiceImpl extends ServiceImpl<SysFileDao, SysFileEntity> implements SysFileService {
}
