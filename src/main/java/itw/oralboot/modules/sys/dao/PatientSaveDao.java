package itw.oralboot.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import itw.oralboot.modules.sys.form.PatientSaveFrom;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PatientSaveDao extends BaseMapper<PatientSaveFrom> {
}
