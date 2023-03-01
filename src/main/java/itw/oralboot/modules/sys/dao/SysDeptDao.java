package itw.oralboot.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import itw.oralboot.modules.sys.entity.SysDept;
import org.apache.ibatis.annotations.Mapper;

/**
 * 科室信息
 */
@Mapper
public interface SysDeptDao extends BaseMapper<SysDept> {
}
