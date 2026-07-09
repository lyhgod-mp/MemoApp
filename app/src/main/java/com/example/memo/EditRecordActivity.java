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
 * 编辑/查看备忘录界面
 */
public class EditRecordActivity extends AppCompatActivity {

    private int recordId;
    private String account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_record);

        recordId = getIntent().getIntExtra("record_id", -1);
        account = getIntent().getStringExtra("account");

        EditText etTitle = findViewById(R.id.et_edit_title);
        EditText etContent = findViewById(R.id.et_edit_content);
        TextView tvWordCount = findViewById(R.id.tv_edit_word_count);
        Button btnUpdate = findViewById(R.id.btn_update_record);
        Button btnDelete = findViewById(R.id.btn_delete_record);
        Button btnBack = findViewById(R.id.btn_back_edit);

        // 加载原内容
        RecordDao recordDao = new RecordDao();
        Record record = recordDao.getRecordById(recordId);
        if (record != null) {
            etTitle.setText(record.getS_title());
            etContent.setText(record.getS_con());
            tvWordCount.setText("字数：" + (record.getS_con() != null ? record.getS_con().length() : 0));
        }

        // 字数统计
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

        // 更新按钮
        btnUpdate.setOnClickListener(view -> {
            String title = etTitle.getText().toString().trim();
            String content = etContent.getText().toString().trim();

            if (title.isEmpty()) {
                Toast.makeText(EditRecordActivity.this, "请输入标题！", Toast.LENGTH_SHORT).show();
                return;
            }

            Record updatedRecord = new Record();
            updatedRecord.setS_id(recordId);
            updatedRecord.setS_title(title);
            updatedRecord.setS_con(content);

            int result = recordDao.updateRecord(updatedRecord);
            if (result > 0) {
                Toast.makeText(EditRecordActivity.this, "更新成功！", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(EditRecordActivity.this, "更新失败！", Toast.LENGTH_SHORT).show();
            }
        });

        // 删除按钮
        btnDelete.setOnClickListener(view -> {
            int result = recordDao.deleteRecord(recordId);
            if (result > 0) {
                Toast.makeText(EditRecordActivity.this, "删除成功！", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(EditRecordActivity.this, "删除失败！", Toast.LENGTH_SHORT).show();
            }
        });

        btnBack.setOnClickListener(view -> finish());
    }
}
