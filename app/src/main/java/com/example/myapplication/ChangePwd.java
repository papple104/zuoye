package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePwd extends AppCompatActivity {
    EditText uname,opasswd,npasswd,cpasswd;
    Button conchangepwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd);
        findViews();
        conchangepwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cpd_uname=uname.getText().toString().trim();
                String cpd_opasswd=opasswd.getText().toString().trim();
                String cpd_npasswd=npasswd.getText().toString().trim();
                String cpd_cpasswd=cpasswd.getText().toString().trim();
                if(cpd_uname.equals("")||cpd_opasswd.equals("")||cpd_npasswd.equals("")||cpd_cpasswd.equals("")){

                }
                else if(cpd_opasswd.equals(cpd_npasswd)){

                }
                else if(!cpd_npasswd.equals(cpd_cpasswd)){

                }
                else{
                    UserActivity activity=new UserActivity(ChangePwd.this);
                    UserInfo user=new UserInfo();
                    user.setUname(cpd_uname);
                    user.setPasswd(cpd_opasswd);
                    if(!activity.doLogin(user)){

                    }
                    else{
                        user.setPasswd(cpd_npasswd);
                        if(activity.doChangePwd(user)){
                            Toast.makeText(ChangePwd.this,"密码修改成功",Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                }
                Toast.makeText(ChangePwd.this,"密码修改失败",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void findViews(){
        uname=findViewById(R.id.editText6);
        opasswd=findViewById(R.id.editText7);
        npasswd=findViewById(R.id.editText8);
        cpasswd=findViewById(R.id.editText9);
        conchangepwd=findViewById(R.id.button5);
    }
}
