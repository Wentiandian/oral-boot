package itw.oralboot.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import itw.oralboot.modules.sys.entity.EleRecordsEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 电子病历信息
 */
@Mapper
public interface EleRecordsDao extends BaseMapper<EleRecordsEntity> {
}
