package com.apkbus.kdatastore.sqlite.dbhelper

import android.content.Context
import com.apkbus.kdatastore.sqlite.bean.User
import java.util.*

/**
 * 数据库访问接口实现类，注意在修改方法前加上synchronized（同步）
 * Created by 邹峰立 on 2017/2/16 0016.
 */
class SQLiteDaoImpl
/**
 * 构造方法
 *
 * @param context 上下文对象
 */
(context: Context) : SQLiteDao {
    private var dbHelper: SQLiteHelper? = null

    init {
        dbHelper = SQLiteHelper.getSqliteHelper(context)
    }

    /**
     * 获取用户集合
     */
    @Synchronized override fun selectUser(): List<User> {
        val db = dbHelper!!.openDatabase() // 获取一个可读的数据库
        val cursor = db?.rawQuery("select * from t_user", null)
        val users = ArrayList<User>()
        while (cursor?.moveToNext()!!) {
            val user = User()
            user.setuId(cursor.getLong(cursor.getColumnIndex("u_id")))
            user.setuPhone(cursor.getLong(cursor.getColumnIndex("u_phone")))
            user.setuRealName(cursor.getString(cursor.getColumnIndex("u_realname")))
            user.setuSex(cursor.getString(cursor.getColumnIndex("u_sex")))
            user.setuHeight(cursor.getFloat(cursor.getColumnIndex("u_height")))
            user.setuWeight(cursor.getFloat(cursor.getColumnIndex("u_weight")))
            user.setuBirthday(cursor.getString(cursor.getColumnIndex("u_birthday")))
            user.setuDomicile(cursor.getString(cursor.getColumnIndex("u_domicile")))
            user.setuEmail(cursor.getString(cursor.getColumnIndex("u_email")))
            user.setuWeibo(cursor.getString(cursor.getColumnIndex("u_weibo")))
            users.add(user)
        }
        cursor.close()
        dbHelper!!.closeDatabase()
        return users
    }

    /**
     * 根据用户ID获取用户信息
     *
     * @param uId 用户ID
     */
    @Synchronized override fun selectUserByuId(uId: Long): User {
        val db = dbHelper!!.openDatabase() // 获取一个可读的数据库
        val sql = "select * from t_user where u_id=?"
        val cursor = db?.rawQuery(sql, arrayOf(uId.toString() + ""))
        var user: User? = null
        while (cursor?.moveToNext()!!) {
            user = User()
            user.setuId(cursor.getLong(cursor.getColumnIndex("u_id")))
            user.setuPhone(cursor.getLong(cursor.getColumnIndex("u_phone")))
            user.setuRealName(cursor.getString(cursor.getColumnIndex("u_realname")))
            user.setuSex(cursor.getString(cursor.getColumnIndex("u_sex")))
            user.setuHeight(cursor.getFloat(cursor.getColumnIndex("u_height")))
            user.setuWeight(cursor.getFloat(cursor.getColumnIndex("u_weight")))
            user.setuBirthday(cursor.getString(cursor.getColumnIndex("u_birthday")))
            user.setuDomicile(cursor.getString(cursor.getColumnIndex("u_domicile")))
            user.setuEmail(cursor.getString(cursor.getColumnIndex("u_email")))
            user.setuWeibo(cursor.getString(cursor.getColumnIndex("u_weibo")))
        }
        cursor.close()
        dbHelper!!.closeDatabase()
        return user!!
    }

    /**
     * 根据用户ID修改用户信息
     */
    @Synchronized override fun updateUserByuId(user: User) {
        val db = dbHelper!!.openDatabase() // 获取一个可写的数据库
        val sql = ("update t_user set u_phone=? ,u_realname=?"
                + ", u_sex=? ,u_height=? ,u_weight=? ,u_birthday=?, u_domicile=?"
                + ", u_email=? ,u_weibo=? where u_id = ?")
        db?.execSQL(sql, arrayOf(user.getuPhone(), user.getuRealName(), user.getuSex(), user.getuHeight(), user.getuWeight(), user.getuBirthday(), user.getuDomicile(), user.getuEmail(), user.getuWeibo(), user.getuId()))
        dbHelper!!.closeDatabase()
    }

    /**
     * 插入用户信息
     */
    @Synchronized override fun insertUser(user: User) {
        val db = dbHelper!!.openDatabase() // 获取一个可写的数据库
        val sql = ("insert into t_user(u_id, u_phone,  u_realname"
                + ", u_sex ,u_height ,u_weight ,u_birthday"
                + ", u_domicile, u_email ,u_weibo)"
                + "values(?,?,?,?,?,?,?,?,?,?)")
        db?.execSQL(sql, arrayOf(user.getuId(), user.getuPhone(), user.getuRealName(), user.getuSex(), user.getuHeight(), user.getuWeight(), user.getuBirthday(), user.getuDomicile(), user.getuEmail(), user.getuWeibo()))
        dbHelper!!.closeDatabase()
    }

    /**
     * 根据用户ID删除用户ID
     *
     * @param uId 用户ID
     */
    @Synchronized override fun deleteUserByUid(uId: Long) {
        val db = dbHelper!!.openDatabase() // 获取一个可写的数据库
        val sql = "delete from t_user where u_id=?"
        db?.execSQL(sql, arrayOf(uId))
        dbHelper!!.closeDatabase()
    }

}
