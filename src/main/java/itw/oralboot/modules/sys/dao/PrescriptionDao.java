package itw.oralboot.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import itw.oralboot.modules.sys.entity.PrescriptionEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PrescriptionDao extends BaseMapper<PrescriptionEntity> {
}
