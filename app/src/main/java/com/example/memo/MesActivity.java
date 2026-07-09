package com.example.memo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.memo.bean.Admin;
import com.example.memo.dao.AdminDao;

/**
 * 个人信息管理界面
 */
public class MesActivity extends AppCompatActivity {

    private String account;
    private EditText etName, etSex, etPhone, etAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes);

        account = getIntent().getStringExtra("account");

        etName = findViewById(R.id.mes_name);
        etSex = findViewById(R.id.mes_sex);
        etPhone = findViewById(R.id.mes_phone);
        etAge = findViewById(R.id.mes_age);
        Button btnSave = findViewById(R.id.btn_save_mes);
        Button btnBack = findViewById(R.id.btn_back_mes);

        // 加载当前用户信息
        loadUserInfo();

        btnSave.setOnClickListener(view -> saveUserInfo());

        btnBack.setOnClickListener(view -> finish());
    }

    private void loadUserInfo() {
        AdminDao adminDao = new AdminDao();
        Admin admin = adminDao.getAdminByAccount(account);
        if (admin != null) {
            etName.setText(admin.getS_name());
            etSex.setText(admin.getS_sex());
            etPhone.setText(admin.getS_phone());
            etAge.setText(admin.getS_age());
        }
    }

    private void saveUserInfo() {
        String name = etName.getText().toString().trim();
        String sex = etSex.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String age = etAge.getText().toString().trim();

        if (name.isEmpty() || sex.isEmpty() || phone.isEmpty() || age.isEmpty()) {
            Toast.makeText(MesActivity.this, "所有字段不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }

        Admin admin = new Admin(account, "", name, sex, phone, age);
        AdminDao adminDao = new AdminDao();
        int result = adminDao.updateAdmin(admin);
        if (result > 0) {
            Toast.makeText(MesActivity.this, "个人信息修改成功！", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(MesActivity.this, "修改失败，请重试！", Toast.LENGTH_SHORT).show();
        }
    }
}
