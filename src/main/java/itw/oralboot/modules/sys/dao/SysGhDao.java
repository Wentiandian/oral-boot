package itw.oralboot.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import itw.oralboot.modules.sys.entity.SysGhEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 挂号处理
 */
@Mapper
public interface SysGhDao extends BaseMapper<SysGhEntity> {

    /**
     * 查询挂号表和患者表信息列表
     * @return
     */
    /*@Select("select * from sys_gh,patient where sys_gh.patient_id = patient.patient_id and patient.patient_name like '%#{patient_name}%';")
    List<SysGhEntity> queryGhList (String patient_name);*/
}
