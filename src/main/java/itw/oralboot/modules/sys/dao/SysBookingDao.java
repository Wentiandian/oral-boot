package itw.oralboot.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import itw.oralboot.modules.sys.entity.SysBooking;
import org.apache.ibatis.annotations.Mapper;

/**
 * 预约信息
 */
@Mapper
public interface SysBookingDao extends BaseMapper<SysBooking> {
}
