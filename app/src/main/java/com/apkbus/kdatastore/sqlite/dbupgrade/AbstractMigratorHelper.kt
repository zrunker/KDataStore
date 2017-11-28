package com.apkbus.kdatastore.sqlite.dbupgrade

import android.database.sqlite.SQLiteDatabase

/**
 * 抽象类用于数据库升级
 * Created by 邹峰立 on 2017/9/24 0024.
 */
abstract class AbstractMigratorHelper {
    abstract fun onUpgrade(db: SQLiteDatabase)
}
