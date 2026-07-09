package com.example.memo.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.memo.bean.Admin;
import com.example.memo.util.DBUtil;

/**
 * 用户数据访问对象
 */
public class AdminDao {

    /**
     * 用户登录验证
     * @return 1 成功, 0 失败
     */
    public int loginAdmin(String account, String password) {
        SQLiteDatabase db = DBUtil.db;
        Cursor cursor = db.rawQuery(
                "SELECT * FROM d_admin WHERE s_id=? AND s_pwd=?",
                new String[]{account, password}
        );
        int count = cursor.getCount();
        cursor.close();
        return count > 0 ? 1 : 0;
    }

    /**
     * 用户注册
     * @return 1 成功, 0 账号已存在
     */
    public int registerAdmin(Admin admin) {
        SQLiteDatabase db = DBUtil.db;

        // 检查账号是否已存在
        Cursor cursor = db.rawQuery(
                "SELECT * FROM d_admin WHERE s_id=?",
                new String[]{admin.getS_id()}
        );
        if (cursor.getCount() > 0) {
            cursor.close();
            return 0; // 账号已存在
        }
        cursor.close();

        ContentValues values = new ContentValues();
        values.put("s_id", admin.getS_id());
        values.put("s_pwd", admin.getS_pwd());
        values.put("s_name", admin.getS_name());
        values.put("s_sex", admin.getS_sex());
        values.put("s_phone", admin.getS_phone());
        values.put("s_age", admin.getS_age());

        long result = db.insert("d_admin", null, values);
        return result != -1 ? 1 : 0;
    }

    /**
     * 根据账号查询用户信息
     */
    public Admin getAdminByAccount(String account) {
        SQLiteDatabase db = DBUtil.db;
        Cursor cursor = db.rawQuery(
                "SELECT * FROM d_admin WHERE s_id=?",
                new String[]{account}
        );
        Admin admin = null;
        if (cursor.moveToFirst()) {
            admin = new Admin(
                    cursor.getString(cursor.getColumnIndexOrThrow("s_id")),
                    cursor.getString(cursor.getColumnIndexOrThrow("s_pwd")),
                    cursor.getString(cursor.getColumnIndexOrThrow("s_name")),
                    cursor.getString(cursor.getColumnIndexOrThrow("s_sex")),
                    cursor.getString(cursor.getColumnIndexOrThrow("s_phone")),
                    cursor.getString(cursor.getColumnIndexOrThrow("s_age"))
            );
        }
        cursor.close();
        return admin;
    }

    /**
     * 修改用户信息
     */
    public int updateAdmin(Admin admin) {
        SQLiteDatabase db = DBUtil.db;
        ContentValues values = new ContentValues();
        values.put("s_name", admin.getS_name());
        values.put("s_sex", admin.getS_sex());
        values.put("s_phone", admin.getS_phone());
        values.put("s_age", admin.getS_age());

        return db.update("d_admin", values, "s_id=?", new String[]{admin.getS_id()});
    }

    /**
     * 修改密码
     */
    public int updatePassword(String account, String newPassword) {
        SQLiteDatabase db = DBUtil.db;
        ContentValues values = new ContentValues();
        values.put("s_pwd", newPassword);
        return db.update("d_admin", values, "s_id=?", new String[]{account});
    }
}
