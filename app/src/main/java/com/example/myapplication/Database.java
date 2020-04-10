package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper{
        static String name="user.db";
        static int ver=1;
        public Database(Context context){
            super(context,name,null,ver);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("drop table if exists userlist");
            db.execSQL("create table userlist(uname varchar(20) not null,passwd varchar(20) not null)");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }

