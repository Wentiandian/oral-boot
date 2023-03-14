package itw.oralboot.modules.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import itw.oralboot.common.utils.R;
import itw.oralboot.modules.app.utils.ValidateCodeUtils;
import itw.oralboot.modules.sys.entity.PatientEntity;
import itw.oralboot.modules.sys.service.PatientService;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 移动端用户管理
 */
@RestController
@RequestMapping("/app/user")
public class UserController extends AbstractController{

    @Autowired
    private PatientService patientService;

    /**
     * 生成验证码并发生验证码
     * @param patientEntity
     * @param session
     * @return
     */
    @PostMapping("/sendMsg")
    public R sendMsg(@RequestBody PatientEntity patientEntity, HttpSession session){
        //获取手机号
        String mobile= patientEntity.getMobile();
        if(Strings.isNotEmpty(mobile)){
            //生成4为数验证码
            String code= ValidateCodeUtils.generateValidateCode(4).toString();
            logger.info("生成的验证码为:{}",code);

            //将验证码保存到session
            session.setAttribute(mobile,code);

            return R.ok("手机验证码发送成功");
        }
        return R.error("手机验证码发送失败");
    }

    /**
     * 移动端用户登录
     * @param map
     * @param session
     * @return
     */
    @PostMapping("/login")
    public R login(@RequestBody Map map, HttpSession session) throws ParseException {
        //获取手机号
        String mobile = map.get("mobile").toString();
        //获取用户填写的code
        String code=map.get("code").toString();
        //获取session 的code
        Object sessionCode=session.getAttribute(mobile);
        //将用户填写的验证码与session中的验证码对比
        if(sessionCode!=null&&sessionCode.equals(code)){
            //如果验证码争取判断是否为新用户
            LambdaQueryWrapper<PatientEntity> queryWrapper=new LambdaQueryWrapper<>();
            queryWrapper.eq(PatientEntity::getMobile,mobile);
            PatientEntity patient=patientService.getOne(queryWrapper);
            //如果数据库中查找不到该用户
            if(patient==null){
                patient=new PatientEntity();
                //新用户自动注册
                patient.setMobile(mobile);
                //生成id
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
                Long id = Long.valueOf(sdf.format(System.currentTimeMillis()));
                AbstractController.setCurrentId(id);
                patient.setPatientId(id);
                SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
                Date date = new Date(System.currentTimeMillis());
                patient.setCreateTime(formatter.parse(formatter.format(date)));
                patient.setCreateUserId(id);
                patient.setStatus(1);
                //用户状态默认为1
                patientService.save(patient);
            }
            //将用户id保存到session
            System.out.println(AbstractController.getCurrentId());
            session.setAttribute("id",AbstractController.getCurrentId());
            return R.ok().put("user",patient);
        }

        return R.error("用户登录失败");
    }




    /**
     * 保存用户信息
     * @param patient
     * @return
     */
    @PostMapping("/saveUser")
    public R saveUser(@RequestBody PatientEntity patient, HttpSession session) throws ParseException {
        Long id = (Long) session.getAttribute("id");
        patient.setPatientId(id);
        System.out.println(id);
        LambdaQueryWrapper<PatientEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PatientEntity::getPatientId,id);
        patientService.update(patient,queryWrapper);
        return R.ok();
    }

}
