package com.example.memo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.memo.bean.Admin;
import com.example.memo.dao.AdminDao;

/**
 * 用户注册界面
 */
public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText etAccount = findViewById(R.id.reg_account);
        EditText etPwd = findViewById(R.id.reg_pwd);
        EditText etConfirmPwd = findViewById(R.id.reg_confirm_pwd);
        EditText etName = findViewById(R.id.reg_name);
        EditText etSex = findViewById(R.id.reg_sex);
        EditText etPhone = findViewById(R.id.reg_phone);
        EditText etAge = findViewById(R.id.reg_age);
        Button btnRegister = findViewById(R.id.btn_register);
        Button btnBack = findViewById(R.id.btn_back);

        btnRegister.setOnClickListener(view -> {
            String account = etAccount.getText().toString().trim();
            String pwd = etPwd.getText().toString().trim();
            String confirmPwd = etConfirmPwd.getText().toString().trim();
            String name = etName.getText().toString().trim();
            String sex = etSex.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            String age = etAge.getText().toString().trim();

            // 表单验证：所有字段必须填写
            if (account.isEmpty() || pwd.isEmpty() || confirmPwd.isEmpty()
                    || name.isEmpty() || sex.isEmpty() || phone.isEmpty() || age.isEmpty()) {
                Toast.makeText(RegisterActivity.this, "请填写所有字段！", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!pwd.equals(confirmPwd)) {
                Toast.makeText(RegisterActivity.this, "两次输入的密码不一致！", Toast.LENGTH_SHORT).show();
                return;
            }

            Admin admin = new Admin(account, pwd, name, sex, phone, age);
            AdminDao adminDao = new AdminDao();
            int result = adminDao.registerAdmin(admin);

            if (result == 0) {
                Toast.makeText(RegisterActivity.this, "该账号已存在，请更换账号！", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(RegisterActivity.this, "注册成功，请登录！", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btnBack.setOnClickListener(view -> finish());
    }
}
