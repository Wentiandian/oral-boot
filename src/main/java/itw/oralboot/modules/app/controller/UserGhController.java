package itw.oralboot.modules.app.controller;

import itw.oralboot.common.utils.R;
import itw.oralboot.modules.sys.entity.SysGhEntity;
import itw.oralboot.modules.sys.service.SysGhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping("/info")
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
}
