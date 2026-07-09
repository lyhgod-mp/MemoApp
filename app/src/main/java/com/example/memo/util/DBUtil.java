package com.example.memo.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库工具类：创建和管理数据库 db_memo
 */
public class DBUtil extends SQLiteOpenHelper {

    public static SQLiteDatabase db;
    private static final String DB_NAME = "db_memo";
    private static final int DB_VERSION = 1;

    public DBUtil(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建用户表 d_admin
        String createAdmin = "CREATE TABLE d_admin (" +
                "s_id VARCHAR PRIMARY KEY NOT NULL," +
                "s_pwd VARCHAR," +
                "s_name VARCHAR," +
                "s_sex VARCHAR," +
                "s_phone VARCHAR," +
                "s_age VARCHAR" +
                ")";
        db.execSQL(createAdmin);

        // 创建备忘录记录表 d_record
        String createRecord = "CREATE TABLE d_record (" +
                "s_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "s_title VARCHAR," +
                "s_con TEXT," +
                "s_uid VARCHAR," +
                "s_time VARCHAR" +
                ")";
        db.execSQL(createRecord);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS d_admin");
        db.execSQL("DROP TABLE IF EXISTS d_record");
        onCreate(db);
    }
}
