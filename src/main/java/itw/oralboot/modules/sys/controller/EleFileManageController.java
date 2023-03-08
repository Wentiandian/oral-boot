package itw.oralboot.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import itw.oralboot.common.utils.R;
import itw.oralboot.modules.sys.entity.EleRecordsEntity;
import itw.oralboot.modules.sys.entity.PatientEntity;
import itw.oralboot.modules.sys.entity.SysFileEntity;
import itw.oralboot.modules.sys.form.EleRecordsFileListFrom;
import itw.oralboot.modules.sys.form.SysFileListForm;
import itw.oralboot.modules.sys.service.SysFileService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 病历文档管理
 */
@RestController
@RequestMapping("/sys/blwdgl")
public class EleFileManageController extends AbstractController{

    @Autowired
    private SysFileService sysFileService;

    private String basePath="E:/work/oral-files/";

    /**
     * 查询所有有文件
     * @param params
     * @return
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        String relationId = (String) params.get("eleRecordsId");
        LambdaQueryWrapper<SysFileEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysFileEntity::getStatus,0)
                .eq(Strings.isNotEmpty(relationId),SysFileEntity::getRelationId,relationId);
        List<SysFileEntity> sysFileEntityList = sysFileService.list(queryWrapper);
        return R.ok().put("list",sysFileEntityList);
    }

    /**
     * 根据文件ID查询文件信息
     * @param fileId
     * @return
     * @throws ParseException
     */
    @GetMapping("/info/{fileId}/{relationId}")
    public R getInfoById(@PathVariable("fileId") Long fileId,@PathVariable("relationId") Long relationId) throws ParseException {

        SysFileEntity sysFile  =  sysFileService.getById(fileId);
        LambdaQueryWrapper<SysFileEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysFileEntity::getRelationId,relationId);
        List<SysFileEntity> fileEntityList = sysFileService.list(queryWrapper);
        return R.ok().put("info",sysFile).put("fileList",fileEntityList);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody SysFileListForm sysFileListForm) throws ParseException {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        List<SysFileEntity> sysFileEntityList = sysFileListForm.getFileList();
        List<SysFileEntity> list = new ArrayList<>();
        for (int i = 0; i < sysFileEntityList.size(); i++) {
            SysFileEntity sysFile = new SysFileEntity();
            sysFile.setRelationId(sysFileListForm.getRelationId());
            sysFile.setSize(sysFileListForm.getSize());
            sysFile.setFileName(sysFileEntityList.get(i).getFileName());
            sysFile.setStatus(sysFileEntityList.get(i).getStatus());
            sysFile.setFileTime(formatter.parse(formatter.format(date)));
            list.add(sysFile);
        }
        sysFileService.saveBatch(list);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public R update(@RequestBody SysFileEntity sysFileEntity) throws ParseException {
        sysFileService.updateById(sysFileEntity);
        return R.ok();
    }

    /**
     * 对文件名进行删除
     * @param fileName
     * @return
     */
    @PostMapping("/delete")
    public R deleteGh(@RequestBody String fileName){
        logger.info(fileName);
        LambdaQueryWrapper<SysFileEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Strings.isNotEmpty(fileName),SysFileEntity::getFileName,fileName);
        sysFileService.remove(queryWrapper);
        File file = new File(basePath + fileName);
        if (file.exists()){
            file.delete();
        }
        else{
            R.error("文件移除失败");
        }
        return R.ok("文件移除成功");
    }
}
