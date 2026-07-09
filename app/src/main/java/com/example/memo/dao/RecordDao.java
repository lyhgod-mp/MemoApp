package com.example.memo.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.memo.bean.Record;
import com.example.memo.util.DBUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 备忘录数据访问对象
 */
public class RecordDao {

    /**
     * 添加备忘录
     */
    public long addRecord(Record record) {
        SQLiteDatabase db = DBUtil.db;
        ContentValues values = new ContentValues();
        values.put("s_title", record.getS_title());
        values.put("s_con", record.getS_con());
        values.put("s_uid", record.getS_uid());
        values.put("s_time", getCurrentTime());
        return db.insert("d_record", null, values);
    }

    /**
     * 更新备忘录
     */
    public int updateRecord(Record record) {
        SQLiteDatabase db = DBUtil.db;
        ContentValues values = new ContentValues();
        values.put("s_title", record.getS_title());
        values.put("s_con", record.getS_con());
        return db.update("d_record", values, "s_id=?", new String[]{String.valueOf(record.getS_id())});
    }

    /**
     * 删除备忘录
     */
    public int deleteRecord(int id) {
        SQLiteDatabase db = DBUtil.db;
        return db.delete("d_record", "s_id=?", new String[]{String.valueOf(id)});
    }

    /**
     * 查询某用户的所有备忘录
     */
    public List<Record> getRecordsByUser(String userId) {
        SQLiteDatabase db = DBUtil.db;
        List<Record> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM d_record WHERE s_uid=? ORDER BY s_id DESC",
                new String[]{userId}
        );
        while (cursor.moveToNext()) {
            Record record = new Record(
                    cursor.getInt(cursor.getColumnIndexOrThrow("s_id")),
                    cursor.getString(cursor.getColumnIndexOrThrow("s_title")),
                    cursor.getString(cursor.getColumnIndexOrThrow("s_con")),
                    cursor.getString(cursor.getColumnIndexOrThrow("s_uid")),
                    cursor.getString(cursor.getColumnIndexOrThrow("s_time"))
            );
            list.add(record);
        }
        cursor.close();
        return list;
    }

    /**
     * 根据ID查询单条备忘录
     */
    public Record getRecordById(int id) {
        SQLiteDatabase db = DBUtil.db;
        Cursor cursor = db.rawQuery(
                "SELECT * FROM d_record WHERE s_id=?",
                new String[]{String.valueOf(id)}
        );
        Record record = null;
        if (cursor.moveToFirst()) {
            record = new Record(
                    cursor.getInt(cursor.getColumnIndexOrThrow("s_id")),
                    cursor.getString(cursor.getColumnIndexOrThrow("s_title")),
                    cursor.getString(cursor.getColumnIndexOrThrow("s_con")),
                    cursor.getString(cursor.getColumnIndexOrThrow("s_uid")),
                    cursor.getString(cursor.getColumnIndexOrThrow("s_time"))
            );
        }
        cursor.close();
        return record;
    }

    /**
     * 获取某用户的备忘录总数
     */
    public int getRecordCount(String userId) {
        SQLiteDatabase db = DBUtil.db;
        Cursor cursor = db.rawQuery(
                "SELECT COUNT(*) FROM d_record WHERE s_uid=?",
                new String[]{userId}
        );
        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        return count;
    }

    private String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }
}
