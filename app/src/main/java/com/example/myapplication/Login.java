package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText uname, passwd;
    Button login, register, changepwd;
    public String currentuser="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String log_uname = uname.getText().toString().trim();
                String log_passwd = passwd.getText().toString().trim();
                if (log_uname.equals("") || log_passwd.equals("")) {

                } else {
                    UserActivity activity = new UserActivity(Login.this);
                    UserInfo user = new UserInfo();
                    user.setUname(log_uname);
                    user.setPasswd(log_passwd);
                    if (activity.doLogin(user)) {
                        Toast.makeText(Login.this, "登入成功", Toast.LENGTH_LONG).show();
                        currentuser=log_uname;
                        Intent i = new Intent(Login.this,Media.class);
                        i.putExtra("currentuser",currentuser);
                        startActivity(i);
                        return;
                    }
                }
                Toast.makeText(Login.this, "登入失败", Toast.LENGTH_LONG).show();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
            }
        });
        changepwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, ChangePwd.class);
                startActivity(i);
            }
        });
    }
    public void findViews() {
        uname = findViewById(R.id.editText);
        passwd = findViewById(R.id.editText2);
        login = findViewById(R.id.button);
        register = findViewById(R.id.button2);
        changepwd = findViewById(R.id.button3);
    }
}