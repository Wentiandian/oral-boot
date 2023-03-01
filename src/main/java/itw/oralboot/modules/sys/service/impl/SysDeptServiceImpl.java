package itw.oralboot.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import itw.oralboot.modules.sys.dao.SysDeptDao;
import itw.oralboot.modules.sys.entity.SysDept;
import itw.oralboot.modules.sys.service.SysDeptService;
import org.springframework.stereotype.Service;

@Service("SysDeptService")
public class SysDeptServiceImpl extends ServiceImpl<SysDeptDao, SysDept> implements SysDeptService {

}
