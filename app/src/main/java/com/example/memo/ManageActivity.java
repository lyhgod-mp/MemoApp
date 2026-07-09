package com.example.memo;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.memo.adapter.RecordAdapter;
import com.example.memo.bean.Record;
import com.example.memo.dao.RecordDao;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

/**
 * 主管理界面 — 备忘录列表 + 侧边栏导航
 */
public class ManageActivity extends AppCompatActivity {

    private ListView listView;
    private RecordAdapter adapter;
    private List<Record> recordList;
    private RecordDao recordDao;
    private String account;
    private DrawerLayout drawerLayout;
    private TextView tvRecordCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        account = getIntent().getStringExtra("account");
        recordDao = new RecordDao();

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // DrawerLayout + NavigationView
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // 侧边栏菜单点击事件
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.menu_to_exit) {
                // 退出应用
                finishAffinity();
            } else if (id == R.id.menu_to_login) {
                // 切换账号
                Intent intent = new Intent(ManageActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else if (id == R.id.menu_up_pwd) {
                // 修改密码
                Intent intent = new Intent(ManageActivity.this, ChangePwdActivity.class);
                intent.putExtra("account", account);
                startActivity(intent);
            } else if (id == R.id.menu_mes) {
                // 个人信息
                Intent intent = new Intent(ManageActivity.this, MesActivity.class);
                intent.putExtra("account", account);
                startActivity(intent);
            }
            drawerLayout.closeDrawers();
            return true;
        });

        // 设置侧边栏头部用户名
        View headerView = navigationView.getHeaderView(0);
        TextView tvHeaderAccount = headerView.findViewById(R.id.tv_header_account);
        tvHeaderAccount.setText(account);

        // 列表
        listView = findViewById(R.id.list_view);
        tvRecordCount = findViewById(R.id.tv_record_count);

        // 添加按钮
        FloatingActionButton fabAdd = findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(view -> {
            Intent intent = new Intent(ManageActivity.this, AddRecordActivity.class);
            intent.putExtra("account", account);
            startActivity(intent);
        });

        // 列表点击事件 — 查看/编辑
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Record record = recordList.get(position);
            Intent intent = new Intent(ManageActivity.this, EditRecordActivity.class);
            intent.putExtra("record_id", record.getS_id());
            intent.putExtra("account", account);
            startActivity(intent);
        });

        // 长按删除
        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            Record record = recordList.get(position);
            recordDao.deleteRecord(record.getS_id());
            Toast.makeText(ManageActivity.this, "已删除", android.widget.Toast.LENGTH_SHORT).show();
            loadRecords();
            return true;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadRecords();
    }

    private void loadRecords() {
        recordList = recordDao.getRecordsByUser(account);
        adapter = new RecordAdapter(this, recordList);
        listView.setAdapter(adapter);

        int count = recordDao.getRecordCount(account);
        tvRecordCount.setText("共 " + count + " 条记录");
    }
}
