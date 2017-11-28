package com.apkbus.kdatastore.greendao.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase

import com.apkbus.kdatastore.greendao.DaoMaster

/**
 * OpenHelper
 * Created by 邹峰立 on 2017/9/23 0023.
 */
class DBHelper @JvmOverloads constructor(context: Context, name: String, factory: SQLiteDatabase.CursorFactory? = null) : DaoMaster.OpenHelper(context, name, factory) {

    /**
     * 当数据库版本更新的时候回调函数
     *
     * @param db         数据库对象
     * @param oldVersion 数据库旧版本
     * @param newVersion 数据库新版本
     */
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        super.onUpgrade(db, oldVersion, newVersion)
        if (newVersion > oldVersion) {
            // 创建临时表
            // 复制旧数据到临时表
            // 数据库（表）删除
            // 临时表重命名
        }
    }

    companion object {
        val DBNAME = "zrunkerdata.db"
    }
}
