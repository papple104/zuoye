package com.example.myapplication;

public class UserInfo {
    private String uname,passwd;
    public UserInfo(){
        super();
    }
    public UserInfo(String uname,String passwd){
        this.uname=uname;
        this.passwd=passwd;
    }
    public String getUname(){
        return uname;
    }
    public void setUname(String uname){
        this.uname=uname;
    }
    public String getPasswd(){
        return passwd;
    }
    public void setPasswd(String passwd){
        this.passwd=passwd;
    }
}
