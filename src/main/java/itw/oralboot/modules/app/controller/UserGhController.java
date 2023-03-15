package itw.oralboot.modules.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import itw.oralboot.common.utils.R;
import itw.oralboot.modules.sys.entity.SysGhEntity;
import itw.oralboot.modules.sys.service.SysGhService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 移动端用户挂号管理
 */
@RestController
@RequestMapping("/app/gh")
public class UserGhController extends AbstractController{

    @Autowired
    private SysGhService sysGhService;


    /**
     * 患者挂号
     * @param ghEntity
     * @return
     * @throws ParseException
     */
    @PostMapping("/save")
    public R saveGhInfo(@RequestBody SysGhEntity ghEntity) throws ParseException {
        logger.info(ghEntity.toString());
        AbstractController.setCurrentId(ghEntity.getPatientId());
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        ghEntity.setGhTime(formatter.parse(formatter.format(date)));
        ghEntity.setCreateUserId(AbstractController.getCurrentId());
        ghEntity.setCreateTime(formatter.parse(formatter.format(date)));
        ghEntity.setStatus(1);
        sysGhService.save(ghEntity);
        return R.ok();
    }

    /**
     * 检查是否已挂过号
     * @param patientId
     * @return
     */
    @GetMapping("/info/{patientId}")
    public R getGhInfo(@PathVariable Long patientId){
        LambdaQueryWrapper<SysGhEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(patientId != null,SysGhEntity::getPatientId,patientId)
                .eq(SysGhEntity::getStatus,1);
        SysGhEntity sysGhEntity = sysGhService.getOne(queryWrapper);
        if(sysGhEntity != null){
            return R.error(200,"已挂过号，无需再次挂号");
        }else {
            return R.ok();
        }
    }
}
