package com.example.memo;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.memo.dao.AdminDao;
import com.example.memo.util.DBUtil;

/**
 * 登录界面
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化数据库连接
        DBUtil dbUtil = new DBUtil(MainActivity.this);
        SQLiteDatabase db = dbUtil.getWritableDatabase();
        DBUtil.db = db;

        // 去注册界面
        Button toReg = findViewById(R.id.register_button);
        toReg.setOnClickListener(view -> {
            Toast.makeText(MainActivity.this, "注册界面", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        // 登录按钮
        EditText ac = findViewById(R.id.login_account);
        EditText pwd = findViewById(R.id.login_pwd);
        Button bu = findViewById(R.id.login_button);

        bu.setOnClickListener(view -> {
            String acT = ac.getText().toString().trim();
            String pwdT = pwd.getText().toString().trim();

            if (acT.isEmpty()) {
                Toast.makeText(MainActivity.this, "请输入账号！", Toast.LENGTH_SHORT).show();
            } else if (pwdT.isEmpty()) {
                Toast.makeText(MainActivity.this, "请输入密码！", Toast.LENGTH_SHORT).show();
            } else {
                AdminDao adminDao = new AdminDao();
                int result = adminDao.loginAdmin(acT, pwdT);
                if (result == 0) {
                    Toast.makeText(MainActivity.this, "账号不存在或密码错误！", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, ManageActivity.class);
                    intent.putExtra("account", acT);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}
