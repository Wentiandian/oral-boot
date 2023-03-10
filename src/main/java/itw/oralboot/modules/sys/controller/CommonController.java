package itw.oralboot.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import itw.oralboot.common.utils.R;
import itw.oralboot.modules.sys.entity.SysDept;
import itw.oralboot.modules.sys.entity.SysDrugEntity;
import itw.oralboot.modules.sys.entity.SysFileEntity;
import itw.oralboot.modules.sys.service.SysDeptService;
import itw.oralboot.modules.sys.service.SysDrugService;
import itw.oralboot.modules.sys.service.SysFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

   /* *//**
     * 取消编辑，移除所有文件
     * @param
     * @return
     *//*
    @PostMapping("/deleteFiles")
    public R deleteFiles(@RequestBody List<String> fileNames){
        logger.info("aaa");;
        for (int i = 0; i < fileNames.size(); i++) {
            File file = new File(basePath + fileNames.get(i));
            if (file.exists()){
                file.delete();
            }else{
                R.error("文件移除失败");
            }
        }
        return R.error("文件移除成功");
    }*/

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
}
