package itw.oralboot.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import itw.oralboot.modules.sys.entity.DocDept;
import org.apache.ibatis.annotations.Mapper;

/**
 * 医生与科室关系信息
 */
@Mapper
public interface DocDeptDao extends BaseMapper<DocDept> {
}
