package itw.oralboot.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import itw.oralboot.common.utils.R;
import itw.oralboot.modules.sys.entity.*;
import itw.oralboot.modules.sys.form.GhBookingListForm;
import itw.oralboot.modules.sys.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 公共控制类
 */
@RestController
@RequestMapping("/sys/common")
public class CommonController extends AbstractController{

    @Autowired
    private SysDeptService sysDeptService;

    @Autowired
    private SysFileService sysFileService;

    @Autowired
    private SysDrugService sysDrugService;

    @Autowired
    private SysGhService sysGhService;

    @Autowired
    private SysBookingService sysBookingService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysUserService sysUserService;

    private String basePath="E:/work/oral-files/";

    /**
     * 获取科室列表
     * @return
     */
    @GetMapping("/deptList")
    public R getDeptList(){
        List<SysDept> sysDeptList = sysDeptService.list();
        return R.ok().put("list",sysDeptList);
    }

    /**
     * 上传文件
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public R upload(MultipartFile file) throws IOException {
        //logger.info("上传文件信息:{}",file.toString());
        //原始文件名
        String originalFile=file.getOriginalFilename();
        String suffix=originalFile.substring(originalFile.lastIndexOf("."));
        //重新命名防止重名
        String newFilename= UUID.randomUUID()+suffix;

        //创建目录对象
        File dir=new File(basePath);
        //判断该目录是否存在不存在则创建一个目录
        if(!dir.exists()){
            dir.mkdir();
        }
        //保存文件
        file.transferTo(new File(basePath+newFilename));
        return R.ok().put("fileName",newFilename);
    }

    /**
     * 下载文件
     * @param name
     * @param response
     */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response) throws Exception {
        //logger.info("文件名：{}",name);
        //通过输入流读取文件
        FileInputStream fileInputStream=new FileInputStream(new File(basePath+name));

        //通过输出流把图片写回到浏览器，页面展示,得到向客户端输出二进制数据的对象
        OutputStream toClient = response.getOutputStream();

        // 得到文件大小
        int i=fileInputStream.available();
        byte[] bytes=new byte[i];

        // 读数据
        fileInputStream.read(bytes);

        //设置返回的文件类型
        response.setContentType("image/jpeg");

        // 输出数据
        toClient.write(bytes);

        //关闭资源
        toClient.close();
        fileInputStream.close();
    }

    /**
     * 移除文件
     * @param fileName
     * @return
     */
    @GetMapping("/deleteFile")
    public R deleteFile(@RequestParam("fileName") String fileName){
        File file = new File(basePath + fileName);
        if (file.exists()){
            file.delete();
        }
        else{
            R.error("文件移除失败");
        }
        LambdaQueryWrapper<SysFileEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysFileEntity::getFileName,fileName);
        sysFileService.remove(queryWrapper);
        return R.ok("文件移除成功");
    }

    /**
     * 获取药品剂型列表
     * @return
     */
    @GetMapping("/drugDosageList")
    public R drugDosageList(){
        List<SysDrugEntity> sysDrugEntityList = sysDrugService.list();
        for (int i = 0; i < sysDrugEntityList.size(); i++) {
            for (int j = 0; j < sysDrugEntityList.size(); j++) {
                if (i != j && sysDrugEntityList.get(i).getDrugDosageForm().equals(sysDrugEntityList.get(j).getDrugDosageForm())) {
                    sysDrugEntityList.remove(j);
                }
            }
        }
        return R.ok().put("list",sysDrugEntityList);
    }


    /**
     * 获取药品名称剂型列表
     * @return
     */
    @GetMapping("/drugNameList")
    public R drugNameList(){
        List<SysDrugEntity> sysDrugEntityList = sysDrugService.list();
        for (int i = 0; i < sysDrugEntityList.size(); i++) {
            for (int j = 0; j < sysDrugEntityList.size(); j++) {
                if (i != j && sysDrugEntityList.get(i).getDrugName().equals(sysDrugEntityList.get(j).getDrugName())) {
                    sysDrugEntityList.remove(j);
                }
            }
        }
        return R.ok().put("list",sysDrugEntityList);
    }

    /**
     * 获取挂号和预约中未就诊的患者列表
     * @return
     */
    @GetMapping("/patientList")
    public R patientList(){
        LambdaQueryWrapper<SysGhEntity> sysGhEntityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        sysGhEntityLambdaQueryWrapper.eq(SysGhEntity::getStatus,1);
        List<SysGhEntity> sysGhEntityList = sysGhService.list(sysGhEntityLambdaQueryWrapper);

        LambdaQueryWrapper<SysBooking> sysBookingLambdaQueryWrapper = new LambdaQueryWrapper<>();
        sysBookingLambdaQueryWrapper.eq(SysBooking::getStatus,1);
        List<SysBooking> sysBookingList = sysBookingService.list(sysBookingLambdaQueryWrapper);

        List<GhBookingListForm> list1 = sysGhEntityList.stream().map((item)->{
            GhBookingListForm ghBookingListForm = new GhBookingListForm();
            ghBookingListForm.setPatientId(item.getPatientId());
            ghBookingListForm.setPatientName(item.getPatientName());
            return ghBookingListForm;
        }).collect(Collectors.toList());

        List<GhBookingListForm> list2 = sysBookingList.stream().map((item)->{
            GhBookingListForm ghBookingListForm = new GhBookingListForm();
            ghBookingListForm.setPatientId(item.getPatientId());
            ghBookingListForm.setPatientName(item.getPatientName());
            return ghBookingListForm;
        }).collect(Collectors.toList());

        List<GhBookingListForm> ghBookingListFormList = new ArrayList<>();
        ghBookingListFormList.addAll(list1);
        ghBookingListFormList.addAll(list2);
        return R.ok().put("list",ghBookingListFormList);
    }

    /**
     * 获取角色列表
     * @return
     */
    @GetMapping("/roleList")
    public R roleList(){
        List<SysRoleEntity> sysRoleEntityList = sysRoleService.list();
        return R.ok().put("list",sysRoleEntityList);
    }

    /**
     * 获取角色列表
     * @param roleId
     * @return
     */
    @GetMapping("/roleList/{roleId}")
    public R docList(@PathVariable String roleId){
        //通过角色ID查询所有的该角色用户ID
        LambdaQueryWrapper<SysUserRoleEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUserRoleEntity::getRoleId,roleId);
        List<SysUserRoleEntity> sysUserRoleEntityList = sysUserRoleService.list(queryWrapper);
        //获取用户信息
        List<SysUserEntity> userList = sysUserRoleEntityList.stream().map((item)->{
            SysUserEntity user = sysUserService.getById(item.getUserId());
            return user;
        }).collect(Collectors.toList());

        return R.ok().put("list",userList);
    }
}
