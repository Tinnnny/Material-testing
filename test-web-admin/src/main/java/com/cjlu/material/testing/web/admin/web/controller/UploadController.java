package com.cjlu.material.testing.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 文件上传控制器
 */
@Controller
public class UploadController {

    private static final String UPLOAD_PATH="/static/upload/";
    /**
     * 文件上传
     *dropFile dropzone de
     * editor wangEditor
     *
     * @return
     */
    //上传文件肯定不跳转页面，所以肯定要返回json来展示数据，这里用map的形式展示
    @ResponseBody
    @RequestMapping(value = "upload",method = RequestMethod.POST)
    public Map<String,Object> upload(MultipartFile dropFile, MultipartFile[] editorFiles,HttpServletRequest request){
        Map<String, Object> result = new HashMap<>();
//        dropzone上传
        if(dropFile !=null){
            result.put("fileName", writeFile(dropFile,request));
        }
        if (editorFiles!=null && editorFiles.length>0){
            List<String> fileNames=new ArrayList<>();
            for(MultipartFile editorFile:editorFiles){
                fileNames.add(writeFile(editorFile, request));
            }
            result.put("errno",0);
            result.put("data", fileNames);
        }
        return result;
    }

    private String writeFile(MultipartFile multipartFile,HttpServletRequest request){
        //文件名
        String fileName=multipartFile.getOriginalFilename();
        String fileSuffix=fileName.substring(fileName.lastIndexOf("."));
        String filePath = request.getSession().getServletContext().getRealPath(UPLOAD_PATH);
        //判断路径是否存在，不存在则创建文件夹
        File file=new File(filePath);
        if (!file.exists()){
            file.mkdir();
        }

        //将文件写入目标
        file=new File(filePath, UUID.randomUUID()+fileSuffix);
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String serverPath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
        return serverPath+UPLOAD_PATH+file.getName();
    }

}
