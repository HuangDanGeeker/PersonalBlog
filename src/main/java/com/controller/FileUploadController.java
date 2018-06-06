package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by HuangDanGeeker on 2018/5/10.
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class FileUploadController {

    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/blog?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Hongkong";
    private static final String username = "root";
    private static final String password = "";
    private static Connection conn = null;

    static
    {
        try
        {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("success");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            System.out.println("wrong");
        }
    }



    /**
     * 图片文件上传
     */
    @RequestMapping(value = "/photoUpload/{userId}",method = RequestMethod.POST)
    public String photoUpload(@PathVariable String userId,  MultipartFile file, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IllegalStateException, IOException {
        Map<String, Object> result = new HashMap<String, Object>(5);
        String s = request.getParameter("uploadId");
        if (file!=null) {// 判断上传的文件是否为空
            String path=null;// 文件路径
            String type=null;// 文件类型
            String fileName=file.getOriginalFilename();// 文件原名称
            System.out.println("上传的文件原名称:"+fileName);
            // 判断文件类型
            type=fileName.indexOf(".")!=-1?fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()):null;
            if (type!=null) {// 判断文件类型是否为空
                if ("GIF".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())) {
                    // 项目在容器中实际发布运行的根路径
                    String realPath=request.getSession().getServletContext().getRealPath("/");
                    // 自定义的文件名称
                    String trueFileName=String.valueOf(System.currentTimeMillis())+fileName;
                    // 设置存放图片文件的路径
                    path=realPath+"WEB-INF/images/profilePic/"+trueFileName;
                    System.out.println("存放图片文件的路径:"+path);
                    // 转存文件到指定的路径
                    file.transferTo(new File(path));
                    System.out.println("文件成功上传到指定目录下");
                    try {
                        Statement statement = conn.createStatement();
                        statement.executeUpdate("update user set pic='../images/profilePic/"+trueFileName+"' where id='"+userId+"'"); //TODO
                        result.put("uploadStatus", "success");
//                        return result;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }else {
                    System.out.println("不是我们想要的文件类型,请按要求重新上传");
                    return null;
                }
            }else {
                System.out.println("文件类型为空");
                return null;
            }
        }else {
            System.out.println("没有找到相对应的文件");
            return null;
        }
        result.put("uploadStatus", "failure");
//        return result;
        return "redirect:/loginpage";
    }
}
