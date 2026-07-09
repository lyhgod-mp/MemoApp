# 备忘录管理系统 (Memo Management System)

## 📋 项目简介

基于 **Android** 平台的备忘录管理系统，采用 **Java** 语言开发，使用 **SQLite** 数据库进行本地数据存储。系统实现了用户注册/登录、备忘录的增删改查、个人信息管理、密码修改等功能，界面遵循 Material Design 设计规范。

## 🎯 功能特性

- **用户认证**：注册、登录、密码修改
- **备忘录管理**：新建、编辑、删除、查看备忘录
- **字数统计**：实时统计备忘录内容字符数
- **个人信息管理**：修改姓名、性别、电话、年龄等信息
- **侧边栏导航**：使用 Navigation Drawer 实现功能切换
- **数据隔离**：不同用户的数据完全隔离
- **Material Design**：简洁美观的 UI 设计

## 🏗️ 系统架构

采用 **MVC** 设计模式，分层架构：

```
├── bean/          # 数据实体层 (Admin, Record)
├── dao/           # 数据访问层 (AdminDao, RecordDao)
├── util/          # 工具类 (DBUtil - SQLiteOpenHelper)
├── adapter/       # 适配器 (RecordAdapter)
└── Activity       # 视图层 (Main, Register, Manage, Mes, ChangePwd, AddRecord, EditRecord)
```

## 📦 数据库设计

**数据库名**：`db_memo`

### 用户表 d_admin

| 字段   | 类型    | 说明     |
|--------|---------|----------|
| s_id   | VARCHAR | 账号(主键) |
| s_pwd  | VARCHAR | 密码     |
| s_name | VARCHAR | 姓名     |
| s_sex  | VARCHAR | 性别     |
| s_phone| VARCHAR | 电话     |
| s_age  | VARCHAR | 年龄     |

### 备忘录表 d_record

| 字段    | 类型    | 说明         |
|---------|---------|--------------|
| s_id    | INTEGER | 记录ID(主键自增) |
| s_title | VARCHAR | 标题         |
| s_con   | TEXT    | 内容         |
| s_uid   | VARCHAR | 所属用户账号  |
| s_time  | VARCHAR | 创建时间     |

## 🛠️ 技术栈

- **开发语言**：Java 8
- **开发工具**：Android Studio
- **最低SDK**：Android 7.0 (API 24)
- **目标SDK**：Android 14 (API 34)
- **数据库**：SQLite
- **UI框架**：Material Design Components
- **构建工具**：Gradle 8.2

## 🚀 快速开始

1. 克隆项目到本地
2. 使用 Android Studio 打开项目
3. 等待 Gradle 同步完成
4. 连接 Android 设备或启动模拟器
5. 点击 Run 运行应用

## 📱 运行截图说明

| 界面 | 说明 |
|------|------|
| 登录界面 | 用户输入账号密码登录 |
| 注册界面 | 新用户填写信息注册 |
| 备忘录列表 | 展示当前用户的所有备忘录 |
| 添加/编辑 | 新建或修改备忘录，支持字数统计 |
| 个人信息 | 查看和修改用户资料 |
| 修改密码 | 验证旧密码后设置新密码 |
| 侧边栏 | 切换功能、退出应用 |

## 📄 项目来源

本项目为《Android开发技术》课程综合项目，成都理工大学计算机与网络安全学院。

- **设计题目**：备忘录管理系统
- **学生姓名**：李治洋
- **学生学号**：202219120504
- **任课教师**：温泉

## 📝 许可证

本项目仅供学习参考。
