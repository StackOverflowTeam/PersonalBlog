package team.stackoverflow.personalsite.controller;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.model.DefaultPutRet;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import team.stackoverflow.personalsite.util.QiniuUtil;
import team.stackoverflow.personalsite.util.RespBean;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName UploadController
 * @Description TODO
 * @Author Zhang Qing
 * @Date 2020/7/7 19:56
 */
@RestController
public class UploadController {

    @RequestMapping("/upload")
    public RespBean uploadImg(@RequestParam MultipartFile picture
            , HttpServletRequest request) throws QiniuException {
        //获取文件在服务器的储存位置
        String path = request.getSession().getServletContext().getRealPath("/upload/");
        File filePath = new File(path);
        System.out.println("filePath:" + path);
        if (!filePath.exists() && !filePath.isDirectory()) {
            System.out.println("if not exist，create directory:" + filePath);
            filePath.mkdir();
        }
        //获取原始文件名称(包含格式)
        String originalFileName = picture.getOriginalFilename();
        System.out.println("originalFileName:" + originalFileName);
        //获取文件类型，以最后一个`.`为标识
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        System.out.println("fileType:" + type);
        //获取文件名称（不包含格式）
        String name = originalFileName.substring(0, originalFileName.lastIndexOf("."));
        //设置文件新名称: 当前时间+文件名称（不包含格式）
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = sdf.format(d);
        String fileName = date + name + "." + type;
        System.out.println("newFileName:" + fileName);
        //在指定路径下创建一个文件
        File targetFile = new File(path, fileName);
        //将文件保存到服务器指定位置
        try {
            picture.transferTo(targetFile);
            System.out.println("success");
            //将文件在服务器的存储路径返回
            return new RespBean("success", fileName);
        } catch (IOException e) {
            System.out.println("failure");
            e.printStackTrace();
            return new RespBean("error", "上传失败");
        }

       /* DefaultPutRet putRet =  QiniuUtil.upload(path, originalFileName);
        if (putRet.hash != null) {
            return new RespBean("success", "success");
        } else {
            return new RespBean("error", "failure");
        }*/
    }
}
