package com.example.memo.bean;

/**
 * 用户实体类 (对应数据表 d_admin)
 */
public class Admin {
    private String s_id;       // 用户账号（主键）
    private String s_pwd;      // 密码
    private String s_name;     // 姓名
    private String s_sex;      // 性别
    private String s_phone;    // 电话
    private String s_age;      // 年龄

    public Admin() {}

    public Admin(String s_id, String s_pwd, String s_name, String s_sex, String s_phone, String s_age) {
        this.s_id = s_id;
        this.s_pwd = s_pwd;
        this.s_name = s_name;
        this.s_sex = s_sex;
        this.s_phone = s_phone;
        this.s_age = s_age;
    }

    public String getS_id() { return s_id; }
    public void setS_id(String s_id) { this.s_id = s_id; }

    public String getS_pwd() { return s_pwd; }
    public void setS_pwd(String s_pwd) { this.s_pwd = s_pwd; }

    public String getS_name() { return s_name; }
    public void setS_name(String s_name) { this.s_name = s_name; }

    public String getS_sex() { return s_sex; }
    public void setS_sex(String s_sex) { this.s_sex = s_sex; }

    public String getS_phone() { return s_phone; }
    public void setS_phone(String s_phone) { this.s_phone = s_phone; }

    public String getS_age() { return s_age; }
    public void setS_age(String s_age) { this.s_age = s_age; }
}
