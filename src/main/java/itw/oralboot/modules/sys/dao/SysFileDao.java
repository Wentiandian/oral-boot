package itw.oralboot.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import itw.oralboot.modules.sys.entity.SysFileEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文件信息
 */
@Mapper
public interface SysFileDao extends BaseMapper<SysFileEntity> {
}
