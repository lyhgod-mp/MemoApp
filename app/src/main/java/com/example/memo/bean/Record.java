package com.example.memo.bean;

/**
 * 备忘录实体类 (对应数据表 d_record)
 */
public class Record {
    private int s_id;          // 记录ID（主键，自增）
    private String s_title;    // 标题
    private String s_con;      // 内容
    private String s_uid;      // 所属用户账号
    private String s_time;     // 创建时间

    public Record() {}

    public Record(int s_id, String s_title, String s_con, String s_uid, String s_time) {
        this.s_id = s_id;
        this.s_title = s_title;
        this.s_con = s_con;
        this.s_uid = s_uid;
        this.s_time = s_time;
    }

    public int getS_id() { return s_id; }
    public void setS_id(int s_id) { this.s_id = s_id; }

    public String getS_title() { return s_title; }
    public void setS_title(String s_title) { this.s_title = s_title; }

    public String getS_con() { return s_con; }
    public void setS_con(String s_con) { this.s_con = s_con; }

    public String getS_uid() { return s_uid; }
    public void setS_uid(String s_uid) { this.s_uid = s_uid; }

    public String getS_time() { return s_time; }
    public void setS_time(String s_time) { this.s_time = s_time; }
}
