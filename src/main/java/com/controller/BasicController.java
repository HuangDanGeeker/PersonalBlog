package com.controller;

import com.bean.Blog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HuangDanGeeker on 2018/4/25.
 */
@Controller
public class BasicController {

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

    @RequestMapping("/blog/login")
    public String loginPage(){
        return "/admin/login";
    }


    @RequestMapping("/login/{userId}/{userPw}")
    @ResponseBody
    public Map testLogin(@PathVariable("userId") String userId, @PathVariable("userPw") String userPw){

        Map result = new HashMap<String, Object>(5);
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select password from user where id='"+userId+"';");
            if( resultSet.next() ){
                if(resultSet.getString(1).equals(userPw)){
                    result.put("loginStatus", "success");
                    return result;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        result.put("loginStatus", "failure");
        return result;
    }

    @RequestMapping("/profile/{userd}")
    public String testProfile(){
        return "/admin/profile";
    }


    @RequestMapping("/queryUserInfo/{userId}")
    @ResponseBody
    public Map queryUserInfo(@PathVariable("userId") String userId){

        Map<String, Object> result = new HashMap<String, Object>(5);
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from user where id='"+userId+"';");
            if( resultSet.next()){
                result.put("status", "success");
                result.put("id", resultSet.getString(1));
                result.put("email", resultSet.getString(2));
                result.put("phone", resultSet.getString(3));
                result.put("qqNum", resultSet.getString(4));
                result.put("introduction", resultSet.getString(5));
                result.put("name", resultSet.getString(6));
                result.put("pic", resultSet.getString(8));
                result.put("address", resultSet.getString(9));
                return result;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        result.put("status", "failure");
        return result;
    }

    @RequestMapping("/changeUserInfo/{userId}/{name}/{email}/{phone}/{qqNum}/{intro}")
    @ResponseBody
    public Map changeUserInfo(@PathVariable("userId") String userId, @PathVariable("name") String name, @PathVariable("email") String email, @PathVariable("phone") String phone,
                              @PathVariable("qqNum") String qqNum, @PathVariable("intro") String introduction){

        Map<String, Object> result = new HashMap<String, Object>(5);
        String sql = "update user set ";
        if(name != "") sql += "name='"+name+"'";
        if(email != "") sql += ",email='"+email+"'";
        if(phone != "") sql += ",phone='"+phone+"'";
        if(qqNum != "") sql += ",qqNum='"+qqNum+"'";
        if(introduction != "") sql += ",introduction='"+introduction+"'";
        sql += " where id='"+userId+"';";
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
            result.put("status", "success");
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        result.put("status", "failure");
        return result;
    }

    @RequestMapping("/submitBlog/{userId}/{code}/{blogTitle}/{blogContent}")
    @ResponseBody
    public Map changeUserInfo(@PathVariable("userId") String userId, @PathVariable("code") String blogCode, @PathVariable("blogTitle") String blogTitle, @PathVariable("blogContent") String blogContent){

        Map<String, Object> result = new HashMap<String, Object>(5);
        String sql = "insert into blog_"+userId+" values('"+blogCode+"', '"+blogTitle+"', '"+blogContent+"', CURRENT_TIME());";
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
            result.put("status", "success");
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        result.put("status", "failure");
        return result;
    }

    @RequestMapping("/index/{userId}")
    public String userIndex(@PathVariable("userId") String userId){
        return "index";
    }
    @RequestMapping("/signalIndex/{code}")
    public String signalIndex(@PathVariable("code") String userId){
        return "index_blog";
    }
    @RequestMapping("/queryBlogInfo/{userId}")
    @ResponseBody
    public Map queryBlogInfo(@PathVariable("userId") String userId){

        Map<String, Object> result = new HashMap<String, Object>(5);
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from blog_"+userId+";");
            List list = new ArrayList<Blog>(10);
            Blog item;
            while(resultSet.next()){
                item = new Blog(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
                list.add(item);
            }
            result.put("status", "success");
            result.put("result", list);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        result.put("status", "failure");
        return result;
    }

    @RequestMapping("/queryBlogInfo/{userId}/{blogCode}")
    @ResponseBody
    public Map queryBlogInfo(@PathVariable("userId") String userId, @PathVariable("blogCode") String blogCode){

        Map<String, Object> result = new HashMap<String, Object>(6);
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from blog_"+userId+" where code='"+blogCode+"';");

            if(resultSet.next()){
                result.put("code", resultSet.getString(1));
                result.put("title", resultSet.getString(2));
                result.put("content", resultSet.getString(3));
                result.put("submitTime", resultSet.getString(4));
            }
            result.put("status", "success");
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        result.put("status", "failure");
        return result;
    }



}


