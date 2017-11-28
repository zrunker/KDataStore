package com.apkbus.kdatastore.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.apkbus.kdatastore.sqlite.bean.User

import com.apkbus.kdatastore.sqlite.dbhelper.SQLiteDaoImpl

/**
 * SQLite数据库存储
 * Created by 邹峰立 on 2017/9/19 0019.
 */
class SQLiteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 初始化SqlIteDao
        val sqLiteDao = SQLiteDaoImpl(this)
        // 插入数据
        val user = User(110, "zrunker", "男", "2000-10-10", 175F, 63F, "福建", 10086L, "ibooker@xx.com", "zrunker")
        sqLiteDao.insertUser(user)
        // 查询数据
        val users = sqLiteDao.selectUser()
        // 打印数据
        for (user1 in users) {
            Log.d("User=", user1.toString())
        }

//        /**
//         * 方式一
//         * @param file
//         * @param factory
//         */
//        val db = SQLiteDatabase.openOrCreateDatabase(File file, SQLiteDatabase.CursorFactory factory)
//
//        /**
//         * 方式二
//         * @param path
//         * @param factory
//         * @param flags
//         */
//        val db = SQLiteDatabase.openDatabase(String path, SQLiteDatabase.CursorFactory factory, int flags)
//
//
//        // 初始化SQLiteDataBase
//        val path = Environment.getExternalStorageDirectory().absolutePath + File.separator + "zrunkerdata.db"
//        val db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE)
//
//        // 创建t_user表
//        val create_user = "CREATE TABLE IF NOT EXISTS t_user(_id INTEGER PRIMARY KEY autoincrement,u_id BIGINT UNSIGNED NOT NULL UNIQUE,u_phone BIGINT NOT NULL UNIQUE,u_realname VARCHAR(21),u_sex VARCHAR(6),u_height FLOAT(5,2),u_weight FLOAT(6,2),u_birthday VARCHAR(10),u_domicile VARCHAR(50),u_email VARCHAR(40),u_weibo VARCHAR(40))"
//        db.execSQL(create_user)
//
//        // 插入数据
//        // 方式一
//        val insert_values = ContentValues()
//        insert_values.put("u_id", 1102)
//        insert_values.put("u_phone", 18011111254L)
//        insert_values.put("u_weibo", "@zrunker")
//        db.insert("t_user", null, insert_values)
//
//        // 方式二
//        val insert_sql = "insert into t_user(u_id, u_phone,  u_realname, u_sex ,u_height ,u_weight ,u_birthday, u_domicile, u_email ,u_weibo) values(?,?,?,?,?,?,?,?,?,?)"
//        db.execSQL(insert_sql, arrayOf(1102, 18011111254L, "zrunker", "男", 175, 65, "1992-10-63", "fujian", "zrunker@xx.com", "@zrunker"))
//
//        // 修改数据
//        // 方式一
//        val update_values = ContentValues()
//        update_values.put("u_phone", 15300025864L)
//        update_values.put("u_weibo", "zrunker")
//        db.update("t_user", update_values, "u_id=?", arrayOf("1102"))
//
//        // 方式二
//        val update_sql = "update t_user set u_phone=? ,u_realname=?, u_sex=? ,u_height=? ,u_weight=? ,u_birthday=?, u_domicile=?, u_email=? ,u_weibo=? where u_id = ?"
//        db.execSQL(update_sql, arrayOf(15300025864L, "zrunker1", "女", 173, 62, "1999-10-16", "beijing", "zrunker1@aa.com", "zrunker", 1102))
//
//        // 删除数据
//        // 方式一
//        db.delete("t_user", "u_id=?", arrayOf("1102"))
//
//        // 方式二
//        val delete_sql = "delete from t_user where u_id=?"
//        db.execSQL(delete_sql, arrayOf(1102L))
//
//        // 查询数据
//        // 方式一：定义类User，作为t_user的关系映射对象
//        val cursor = db.rawQuery("select * from t_user", null)
//        val users = ArrayList<User>()
//        while (cursor.moveToNext()) {
//            val user = User()
//            user.setuId(cursor.getLong(cursor.getColumnIndex("u_id")))
//            user.setuPhone(cursor.getLong(cursor.getColumnIndex("u_phone")))
//            user.setuRealName(cursor.getString(cursor.getColumnIndex("u_realname")))
//            user.setuSex(cursor.getString(cursor.getColumnIndex("u_sex")))
//            user.setuHeight(cursor.getFloat(cursor.getColumnIndex("u_height")))
//            user.setuWeight(cursor.getFloat(cursor.getColumnIndex("u_weight")))
//            user.setuBirthday(cursor.getString(cursor.getColumnIndex("u_birthday")))
//            user.setuDomicile(cursor.getString(cursor.getColumnIndex("u_domicile")))
//            user.setuEmail(cursor.getString(cursor.getColumnIndex("u_email")))
//            user.setuWeibo(cursor.getString(cursor.getColumnIndex("u_weibo")))
//            users.add(user)
//        }
//        cursor.close()
//
//        // 方式二
//        val cursor1 = db.query("t_user", null, null, null, null, null, "u_id desc", "0, 15")
//
//        // 关闭database
//        db.close()
//
//        // 删除数据库
//        this.deleteDatabase("zrunkerdata.db")
    }
}
