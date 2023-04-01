package itw.oralboot.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import itw.oralboot.modules.sys.dao.PatientSaveDao;
import itw.oralboot.modules.sys.form.PatientSaveFrom;
import itw.oralboot.modules.sys.service.PatientSaveService;
import org.springframework.stereotype.Service;

@Service("PatientSaveService")
public class PatientSaveServiceImpl extends ServiceImpl<PatientSaveDao, PatientSaveFrom> implements PatientSaveService {
}
