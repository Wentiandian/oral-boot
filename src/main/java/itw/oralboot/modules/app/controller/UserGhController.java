package itw.oralboot.modules.app.controller;

import itw.oralboot.common.utils.R;
import itw.oralboot.modules.sys.entity.SysGhEntity;
import itw.oralboot.modules.sys.service.SysGhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 移动端用户挂号管理
 */
@RestController
@RequestMapping("/app/gh")
public class UserGhController extends AbstractController{

    @Autowired
    private SysGhService sysGhService;

    public R saveGhInfo(@RequestBody SysGhEntity ghEntity){
        logger.info(ghEntity.toString());
        return R.ok();
    }
}
