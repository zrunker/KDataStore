package com.apkbus.kdatastore.greendao.utils

import com.apkbus.kdatastore.greendao.DaoMaster
import com.apkbus.kdatastore.greendao.DaoSession
import com.apkbus.kdatastore.myapplition.MyApplication

import org.greenrobot.greendao.query.QueryBuilder


/**
 * GreenDao管理类
 *
 * Created by 邹峰立 on 2017/9/23 0023.
 */
class GreenDaoManager// 构造方法
private constructor() {
    var master: DaoMaster? = null
        private set
    var session: DaoSession? = null
        private set

    // 获取新的DaoSession
    val newSession: DaoSession?
        get() {
            session = master!!.newSession()
            return session
        }

    init {
        if (mInstance == null) {
            // 重写DBHelper数据库升级，数据不丢失
            // MyApplication.getContext()上下文表示了数据库存储路径为手机内存
            val helper = DBHelper(MyApplication.instance!!, DBHelper.DBNAME, null)
            master = DaoMaster(helper.writableDatabase)
            session = master!!.newSession()
        }
    }

    companion object {
        @Volatile private var mInstance: GreenDaoManager? = null

        // 单例模式
        val instance: GreenDaoManager?
            get() {
                if (mInstance == null) {
                    synchronized(GreenDaoManager::class.java) {
                        if (mInstance == null) {
                            mInstance = GreenDaoManager()
                        }
                    }
                }
                return mInstance
            }

        // 打印查询日志
        fun enableQueryBuilderLog() {
            QueryBuilder.LOG_SQL = true
            QueryBuilder.LOG_VALUES = true
        }
    }
}