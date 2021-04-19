package com.ywc.agric.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ywc.agric.entity.Editor;
import com.ywc.agric.util.QiNiuUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zombie
 */
@RestController
@RequestMapping("/backstage/payauto/")
public class PayautoController {

    @RequestMapping("upload")
    @ResponseBody
    public Map<String, String> upload(@RequestParam(value = "myFileName") MultipartFile imgFile, HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
//        String separator = System.getProperty("file.separator");
//
//        // 用于前端图片显示的路径  http://localhost:8080/upload/
//        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()
//                + separator +"upload" + separator;
//        // 用于保存图片至项目的路径 D:\_eclipsework\.metadata\.plugins\org.eclipse.wst.server.core\tmp2\wtpwebapps\JYSystem\ upload\
//        // 或者 String uploadDir = request.getSession().getServletContext().getRealPath("upload") + separator;
//        String uploadDir = ProjectPath.getProjectPath() + separator +"upload" + separator;
//
//        byte[] bytes = null;
//        try {
//
//            bytes = file.getBytes();
//            File dirPath = new File(uploadDir);
//            if (!dirPath.exists()) {
//                if (!dirPath.mkdirs()) {
//                }
//            }
//
//            /**
//             * 构建新的图片名称
//             */
//            String fileName = file.getOriginalFilename();
//            int index = fileName.lastIndexOf(".");
//            String extName = index > -1 ? fileName.substring(index) : ""; // .jpg
//            String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
//            String newFileName = uuid + extName;
//
//            /**
//             * 保存图片至项目
//             */
//            String filePath = uploadDir + newFileName;
//            File descFile = new File(filePath);
//            FileCopyUtils.copy(bytes, descFile);
//
//            map.put("data", basePath + newFileName);

        //获取原有的图片名称，截取到后缀名
        //获取图片名称
        String originalFilename = imgFile.getOriginalFilename();
        //截取到后缀名
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        //- 生成唯一文件名，拼接后缀名
        String filename = UUID.randomUUID() + extension;

        try {
            //- 调用七牛上传文件方法
            QiNiuUtils.uploadViaByte(imgFile.getBytes(), filename);
            //- 返回数据给页面
            //{
            //    flag:
            //    message:
            //    data:{
            //        imgName: 图片名,
            //        domain: QiNiuUtils.DOMAIN
            //    }
            //}
            //存储容器·
//            Map<String, String> map = new HashMap<String, String>();
//            map.put("imgName", filename);
//            map.put("domain", QiNiuUtils.DOMAIN);
            String fileImg = QiNiuUtils.DOMAIN + filename;
            map.put("data", fileImg);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public String[] upload(MultipartFile imgFile) {
        //获取原有的图片名称，截取到后缀名
        //获取图片名称
        String originalFilename = imgFile.getOriginalFilename();
        //截取到后缀名
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        //- 生成唯一文件名，拼接后缀名
        String filename = UUID.randomUUID() + extension;


        //- 调用七牛上传文件方法
        try {
            QiNiuUtils.uploadViaByte(imgFile.getBytes(), filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //- 返回数据给页面
        //{
        //    flag:
        //    message:
        //    data:{
        //        imgName: 图片名,
        //        domain: QiNiuUtils.DOMAIN
        //    }
        //}
        //存储容器·
//            Map<String, String> map = new HashMap<String, String>();
//            map.put("imgName", filename);
//            map.put("domain", QiNiuUtils.DOMAIN);
        String fileImg = QiNiuUtils.DOMAIN + filename;
        String[] str = {fileImg};
        return str;


    }

    /**
     *富文本上传图片
     */
    @PostMapping("/editor")
    @ResponseBody
    public Editor uploadImageHtml(MultipartFile multiple, HttpSession session, HttpServletRequest request) throws IOException {
        if (multiple != null) {
            String [] str = upload(multiple);
            return Editor.ResultUtil.success(str);
        }
        return Editor.ResultUtil.success();
    }

}