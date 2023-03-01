package itw.oralboot.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import itw.oralboot.modules.sys.dao.DocDeptDao;
import itw.oralboot.modules.sys.entity.DocDept;
import itw.oralboot.modules.sys.service.DocDeptService;
import org.springframework.stereotype.Service;

@Service("DocDeptService")
public class DocDeptServiceImpl extends ServiceImpl<DocDeptDao, DocDept> implements DocDeptService {

}
