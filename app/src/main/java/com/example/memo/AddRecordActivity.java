package com.example.memo;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.memo.bean.Record;
import com.example.memo.dao.RecordDao;

/**
 * 添加备忘录界面
 */
public class AddRecordActivity extends AppCompatActivity {

    private String account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        account = getIntent().getStringExtra("account");

        EditText etTitle = findViewById(R.id.et_record_title);
        EditText etContent = findViewById(R.id.et_record_content);
        TextView tvWordCount = findViewById(R.id.tv_word_count);
        Button btnSave = findViewById(R.id.btn_save_record);
        Button btnBack = findViewById(R.id.btn_back_record);

        // 字数统计 — 实时统计内容字符数
        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvWordCount.setText("字数：" + s.length());
            }

            @Override
            public void afterTextChanged(android.text.Editable s) {}
        });

        btnSave.setOnClickListener(view -> {
            String title = etTitle.getText().toString().trim();
            String content = etContent.getText().toString().trim();

            if (title.isEmpty()) {
                Toast.makeText(AddRecordActivity.this, "请输入标题！", Toast.LENGTH_SHORT).show();
                return;
            }
            if (content.isEmpty()) {
                Toast.makeText(AddRecordActivity.this, "请输入内容！", Toast.LENGTH_SHORT).show();
                return;
            }

            Record record = new Record();
            record.setS_title(title);
            record.setS_con(content);
            record.setS_uid(account);

            RecordDao recordDao = new RecordDao();
            long result = recordDao.addRecord(record);

            if (result != -1) {
                Toast.makeText(AddRecordActivity.this, "保存成功！", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(AddRecordActivity.this, "保存失败！", Toast.LENGTH_SHORT).show();
            }
        });

        btnBack.setOnClickListener(view -> finish());
    }
}
