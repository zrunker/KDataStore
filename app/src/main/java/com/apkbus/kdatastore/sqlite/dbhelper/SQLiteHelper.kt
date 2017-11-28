package com.apkbus.kdatastore.sqlite.dbhelper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.apkbus.kdatastore.sqlite.dbupgrade.DBMigrationHelper
import java.util.concurrent.atomic.AtomicInteger

/**
 * SQLiteOpenHelper作用：
 * 1.提高onCreate onUpgrade等创建数据库和更新数据库方法
 * 2.提供了获取数据库对象的函数
 *
 * @author 邹峰立
 */
internal class SQLiteHelper
/**
 * 构造函数
 *
 * @param context 上下文对象
 * @param name    创建数据库的名称
 * @param factory 游标工厂
 * @param version 创建数据库版本 >= 1
 */
private constructor(context: Context, name: String = SQLiteConstant.DB_NAME, factory: SQLiteDatabase.CursorFactory? = null, version: Int = SQLiteConstant.DB_VERSION) : SQLiteOpenHelper(context, name, factory, version) {
    private val mOpenCounter = AtomicInteger(0)
    private var mDatabase: SQLiteDatabase? = null

    /**
     * 打开数据库
     */
    @Synchronized
    fun openDatabase(): SQLiteDatabase? {
        if (mOpenCounter.incrementAndGet() == 1) {
            mDatabase = SQLiteHelper!!.writableDatabase
        }
        return mDatabase
    }

    /**
     * 关闭数据库
     */
    @Synchronized
    fun closeDatabase() {
        if (mOpenCounter.decrementAndGet() == 0) {
            mDatabase!!.close()
        }
    }

    /**
     * 当数据库创建时回调的函数
     *
     * @param db 数据库对象
     */
    override fun onCreate(db: SQLiteDatabase) {
        // 创建数据表
        db.execSQL(SQLiteConstant.SQL_CREATE_TABLE_USER)
    }

    /**
     * 当数据库版本更新的时候回调函数
     *
     * @param db         数据库对象
     * @param oldVersion 数据库旧版本
     * @param newVersion 数据库新版本
     */
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (newVersion > oldVersion) {
            val migratorHelper = DBMigrationHelper()
            migratorHelper.onUpgrade(db)
        }
    }

    // 伴生对象
    companion object {
        private var SQLiteHelper: SQLiteHelper? = null

        /**
         * 获取MySqliteHelper，单列模式
         *
         * @param context 上下文对象
         */
        @Synchronized
        fun getSqliteHelper(context: Context): SQLiteHelper {
            if (SQLiteHelper == null)
                SQLiteHelper = SQLiteHelper(context)
            return SQLiteHelper as SQLiteHelper
        }
    }

}


