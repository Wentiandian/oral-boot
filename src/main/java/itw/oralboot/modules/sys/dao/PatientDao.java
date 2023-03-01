package itw.oralboot.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import itw.oralboot.modules.sys.entity.PatientEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 患者信息
 */
@Mapper
public interface PatientDao extends BaseMapper<PatientEntity> {
}
