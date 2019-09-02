package com.ruolan.spring.controller;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * SpringBoot进行单文件上传
 * @author xiaodingjian
 */
@Controller
public class FileUploadController {
    @PostMapping("/upload")
    @ResponseBody
    public void upload(@RequestParam("file") MultipartFile file,HttpServletResponse response) {
        if (file.isEmpty()) {
        }
        String fileName = file.getOriginalFilename();
        String filePath = "D:/JavaProject/spring/src/main/resources/upload/";
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            response.sendRedirect("layui/WEB/teacher_update_resource.html");
        } catch (IOException e) {
        }
    }

    @PostMapping("/uploadFile")
    public @ResponseBody String singleFileUpload(@RequestParam("file")MultipartFile file) {
        String FILE_DIR = "D://JavaProject//spring//src//main//resources//upload//";
        //判断文件是否为空
        if (file.isEmpty()) {
            return "文件为空，上传失败!";
        }
        try {
            //获得文件的字节流
            byte[] bytes = file.getBytes();
            //获得path对象，也即是文件保存的路径对象
            Path path = Paths.get(FILE_DIR + decodeUnicode(file.getOriginalFilename()));
            //调用静态方法完成将文件写入到目标路径
            Files.write(path, bytes);
            return "恭喜上传成功!";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "未知异常";
    }

    @RequestMapping(value="/download",method = RequestMethod.GET)
    public void download(HttpServletResponse response, HttpServletRequest httpServletRequest) throws UnsupportedEncodingException {
        //要上传的文件名字
        String name = httpServletRequest.getParameter("filename");
        String FILE_DIR = "D://JavaProject//spring//src//main//resources//upload//";
        String fileRealName=null;
        if (httpServletRequest.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
            fileRealName = URLEncoder.encode(name, "UTF-8");
        } else {
            fileRealName = new String(name.getBytes("UTF-8"), "ISO8859-1");
        }
        //通过文件的保存文件夹路径加上文件的名字来获得文件
        File file=new File(FILE_DIR,name);
        //当文件存在
        if(file.exists()) {

            //首先设置响应的内容格式是force-download，那么你一旦点击下载按钮就会自动下载文件了
            response.setContentType("application/force-download");
            //通过设置头信息给文件命名，也即是，在前端，文件流被接受完还原成原文件的时候会以你传递的文件名来命名
            response.addHeader("Content-Disposition", String.format("attachment; filename=\"%s\"",fileRealName));
            //进行读写操作
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                //从源文件中读
                int i = bis.read(buffer);
                while (i != -1) {
                    //写到response的输出流中
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //善后工作，关闭各种流
                try {
                    if (bis != null) {
                        bis.close();
                    }
                    if (fis != null) {
                        fis.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String decodeUnicode(final String dataStr) {
        try{
            final StringBuffer buffer = new StringBuffer(dataStr==null?"":dataStr);
            if(StringUtils.isNotBlank(dataStr) && dataStr.contains("\\u")) {
                buffer.delete(0,buffer.length());
                int start = 0;
                int end = 0;
                while (start > -1) {
                    end = dataStr.indexOf("\\u", start + 2);
                    String a="";//如果夹着非unicode编码的字符串，存放在这
                    String charStr = "";
                    if (end == -1) {
                        if(dataStr.substring(start + 2, dataStr.length()).length()>4){
                            charStr = dataStr.substring(start + 2, start + 6);
                            a = dataStr.substring(start + 6, dataStr.length())  ;
                        }else{
                            charStr = dataStr.substring(start + 2, dataStr.length());
                        }
                    } else {
                        charStr = dataStr.substring(start + 2, end);
                    }
                    char letter = (char) Integer.parseInt(charStr.trim(), 16); // 16进制parse整形字符串。
                    buffer.append(new Character(letter).toString());
                    if(StringUtils.isNotBlank(a)){
                        buffer.append(a);
                    }
                    start = end;
                }
            }
            return buffer.toString();
        } catch (Exception e){
            System.out.println("转化失败");
        }
        return dataStr;
    }
}
