package com.bean;

/**
 * Created by HuangDanGeeker on 2018/5/9.
 */
public class Blog{
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String title;
    private String submitTime;
    private String content;

    public Blog(String code, String title, String content, String submitTime){
        this.code = code;
        this.title = title;
        this.content = content;
        this.submitTime = submitTime;
    }
}