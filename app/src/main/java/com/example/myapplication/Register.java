package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText uname,passwd,cpasswd;
    Button conregister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViews();
        conregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reg_uname=uname.getText().toString().trim();
                String reg_passwd=passwd.getText().toString().trim();
                String reg_cpasswd=cpasswd.getText().toString().trim();
                if(reg_uname.equals("") || reg_passwd.equals("") || reg_cpasswd.equals("")){

                }
                else if(!reg_passwd.equals(reg_cpasswd)){

                }
                else{
                    UserActivity activity=new UserActivity(Register.this);
                    UserInfo user=new UserInfo();
                    user.setUname(reg_uname);
                    user.setPasswd(reg_passwd);
                    if(activity.doRegister(user))
                        Toast.makeText(Register.this,"注册成功",Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(Register.this,"注册失败",Toast.LENGTH_LONG).show();
            }
        });
    }


    public void findViews(){
        uname=findViewById(R.id.editText3);
        passwd=findViewById(R.id.editText4);
        cpasswd=findViewById(R.id.editText5);
        conregister=findViewById(R.id.button4);
    }
}
