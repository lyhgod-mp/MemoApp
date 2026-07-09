package com.example.memo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.memo.dao.AdminDao;

/**
 * 修改密码界面
 */
public class ChangePwdActivity extends AppCompatActivity {

    private String account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd);

        account = getIntent().getStringExtra("account");

        EditText etOldPwd = findViewById(R.id.et_old_pwd);
        EditText etNewPwd = findViewById(R.id.et_new_pwd);
        EditText etConfirmPwd = findViewById(R.id.et_confirm_pwd);
        Button btnConfirm = findViewById(R.id.btn_confirm_pwd);
        Button btnBack = findViewById(R.id.btn_back_pwd);

        btnConfirm.setOnClickListener(view -> {
            String oldPwd = etOldPwd.getText().toString().trim();
            String newPwd = etNewPwd.getText().toString().trim();
            String confirmPwd = etConfirmPwd.getText().toString().trim();

            if (oldPwd.isEmpty() || newPwd.isEmpty() || confirmPwd.isEmpty()) {
                Toast.makeText(ChangePwdActivity.this, "请填写所有字段！", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!newPwd.equals(confirmPwd)) {
                Toast.makeText(ChangePwdActivity.this, "两次输入的新密码不一致！", Toast.LENGTH_SHORT).show();
                return;
            }

            AdminDao adminDao = new AdminDao();
            // 验证旧密码
            int result = adminDao.loginAdmin(account, oldPwd);
            if (result == 0) {
                Toast.makeText(ChangePwdActivity.this, "旧密码错误！", Toast.LENGTH_SHORT).show();
                return;
            }

            // 更新密码
            int updateResult = adminDao.updatePassword(account, newPwd);
            if (updateResult > 0) {
                Toast.makeText(ChangePwdActivity.this, "密码修改成功！", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(ChangePwdActivity.this, "修改失败，请重试！", Toast.LENGTH_SHORT).show();
            }
        });

        btnBack.setOnClickListener(view -> finish());
    }
}
