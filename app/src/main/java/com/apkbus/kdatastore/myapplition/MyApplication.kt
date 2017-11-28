package com.apkbus.kdatastore.myapplition

import android.app.Application
import com.apkbus.kdatastore.greendao.utils.GreenDaoManager


/**
 * 自定义MyApplication
 * Created by 邹峰立 on 2017/9/23 0023.
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // 全局上下文赋值
        instance = this
        // 初始化数据库
        GreenDaoManager.instance
    }

    companion object {
        // 获取全局上下文对象
        var instance: MyApplication? = null
            private set
    }
}
