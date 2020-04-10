package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserActivity {
    private Database db;
    public UserActivity(Context context){
        db=new Database(context);
    }
    public boolean doLogin(UserInfo user){
        SQLiteDatabase sdb=db.getReadableDatabase();
        Cursor cr=sdb.rawQuery("select * from userlist where uname=? and passwd=?", new String[]{user.getUname(),user.getPasswd()});
        int result=cr.getCount();
        if(result==1)
        {
            cr.close();
            return true;
        }
        return false;
    }
    public boolean doRegister(UserInfo user){
        SQLiteDatabase sdb=db.getReadableDatabase();
        sdb.execSQL("insert into userlist values(?,?)",new Object[]{user.getUname(),user.getPasswd()});
        return true;
    }
    public boolean doChangePwd(UserInfo user){
        SQLiteDatabase sdb=db.getReadableDatabase();
        sdb.execSQL("update userlist set passwd=? where uname=?",new Object[]{user.getPasswd(),user.getUname()});
        return true;
    }
}
