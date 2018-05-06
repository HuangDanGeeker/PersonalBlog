package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.*;
import java.util.HashMap;
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
                return result;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        result.put("status", "failure");
        return result;
    }



}
