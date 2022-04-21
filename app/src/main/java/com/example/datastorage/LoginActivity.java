package com.example.datastorage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;
    private CheckBox rememberData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        accountEdit = (EditText) findViewById(R.id.username);
        passwordEdit = (EditText) findViewById(R.id.password);
        rememberData = (CheckBox) findViewById(R.id.remember_data);
        login = (Button) findViewById(R.id.Button_login);
        boolean isRemember = pref.getBoolean("rememberData",false);
        if(isRemember){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                //如果账号密码都是admin，则认为登陆成功
                if(account.equals("admin") && password.equals("admin")){
                    editor = pref.edit();
                    if(rememberData.isChecked()){
                        editor.putString("account",account);
                        editor.putString("password",password);
                        editor.putBoolean("rememberData",true);
                    }else{
                        editor.clear();
                    }
                    editor.apply();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(LoginActivity.this);
                    dialog.setTitle("错误警告");
                    dialog.setMessage("用户名或者密码错误");
                    dialog.setCancelable(true);
                    dialog.setPositiveButton("确认",new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog,int which){
                        }
                    });
                    dialog.show();
                }
            }
        });
    }
}