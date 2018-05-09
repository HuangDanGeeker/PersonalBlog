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


    @RequestMapping("/loginpage")
    public String queryBlogInfo(){

        return "login";
    }

    @RequestMapping("/login/{userNo}/{userPasswd}")
    @ResponseBody
    public Map<Object, String> login(@PathVariable("userNo") String userNo, @PathVariable("userPasswd") String userPasswd){
        System.out.println("login");
        System.out.println("userPasswd " + userPasswd);
        Map result = new HashMap<String, String>();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from user where id='"+userNo+"' and password='"+userPasswd+"';");

            if(resultSet.next()){
                result.put("loginStatus", "success");
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        result.put("loginStatus", "failed");
        return result;
    }

    @RequestMapping("/regist/{registName}/{registPasswd}/{registQQNum}/{registPhone}/{registEmail}/{registAddress}")
    @ResponseBody
    public Map<String, String> regist(@PathVariable("registName") String registName, @PathVariable("registPasswd") String registPasswd, @PathVariable("registQQNum") String registQQNum, @PathVariable("registPhone") String registPhone, @PathVariable("registEmail") String registEmail, @PathVariable("registAddress") String registAddress){
        System.out.println("regist");
        Map result = new HashMap<String, String>();
        Integer registNo = 1;
        // you should use db_transcation to ensure all the sqls below executed successfully
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select id from id_set");
            resultSet.next();
            String noString = resultSet.getString(1);
            registNo = Integer.valueOf(noString);
            result.put("registNo", registNo);
            statement.executeUpdate("INSERT INTO `user` VALUES ('"+registNo+"', '"+registEmail+"', '"+registPhone+"', '"+registQQNum+"', '','"+registName+"', '"+registPasswd+"', '../images/profilePic/1.jpg', '"+registAddress+"');");
            statement.executeUpdate("create table blog_"+registNo+"(code varchar(40) NOT NULL, title varchar(30) DEFAULT NULL, content varchar(40) DEFAULT NULL, submitTime datetime DEFAULT NULL)");
            registNo ++;
            statement.executeUpdate("update id_set set id='"+registNo+"';");
            result.put("registStatus", "success");
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        result.put("registStatus", "failure");
        result.put("reasion", "your input is not valid");
        return result;
    }

}


