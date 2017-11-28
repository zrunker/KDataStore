package com.apkbus.kdatastore.sqlite.dbhelper

/**
 * SQLite常量类
 * Created by 邹峰立 on 2017/2/16 0016.
 */
object SQLiteConstant {
    val DB_NAME = "zrunkerdata.db" //数据库名称
    val DB_VERSION = 1 //数据库版本号
    // 创建User表的SQL语句
    val SQL_CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS t_user(_id INTEGER PRIMARY KEY autoincrement,u_id BIGINT UNSIGNED NOT NULL UNIQUE,u_phone BIGINT NOT NULL UNIQUE,u_realname VARCHAR(21),u_sex VARCHAR(6),u_height FLOAT(5,2),u_weight FLOAT(6,2),u_birthday VARCHAR(10),u_domicile VARCHAR(50),u_email VARCHAR(40),u_weibo VARCHAR(40))"
    // 删除User表的SQL语句
    val SQL_DROP_TABLE_USER = "DROP TABLE IF EXISTS t_user"
    // 创建User临时表t_user2
    val SQL_CREATE_TABLE_USER2 = "CREATE TABLE IF NOT EXISTS t_user2(_id INTEGER PRIMARY KEY autoincrement,u_id BIGINT UNSIGNED NOT NULL UNIQUE,u_phone BIGINT NOT NULL UNIQUE,u_realname VARCHAR(21),u_sex VARCHAR(6),u_height FLOAT(5,2),u_weight FLOAT(6,2),u_birthday VARCHAR(10),u_domicile VARCHAR(50),u_email VARCHAR(40),u_weibo VARCHAR(40))"
    // 复制t_user数据到t_user2
    val SQL_COPY_TABLE_USER_USER2 = "INSERT INTO t_user2(_id, u_id, u_phone, u_realname, u_sex, u_height, u_weight, u_birthday, u_domicile, u_email, u_weibo)SELECT _id, u_id, u_phone, u_realname, u_sex, u_height, u_weight, u_birthday, u_domicile, u_email, u_weibo FROM t_user"
    // 删除User临时表t_user2
    val SQL_DROP_TABLE_USER2 = "DROP TABLE IF EXISTS t_user2"
    // User临时表t_user2重命名t_user
    val SQL_USER2_RENAME_USER = "ALTER TABLE t_user2 RENAME TO t_user"
    // 添加索引idxSex
    val SQL_ADD_IDXSEX = "ALTER TABLE t_user ADD INDEX idxSex(`u_sex`)"
}
