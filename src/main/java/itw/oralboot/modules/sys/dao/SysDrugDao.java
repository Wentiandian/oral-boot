package itw.oralboot.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import itw.oralboot.modules.sys.entity.SysDrugEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 药品信息
 */
@Mapper
public interface SysDrugDao extends BaseMapper<SysDrugEntity> {
}
