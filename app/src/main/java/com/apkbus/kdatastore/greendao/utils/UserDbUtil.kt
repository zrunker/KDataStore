package com.apkbus.kdatastore.greendao.utils

import android.util.Log

import com.apkbus.kdatastore.greendao.UserDao
import com.apkbus.kdatastore.greendao.entity.User

import org.greenrobot.greendao.query.LazyList

import java.util.ArrayList


/**
 * 对数据库t_user进行管理
 * Created by 邹峰立 on 2017/9/23 0023.
 */
class UserDbUtil// 构造方法
private constructor() {
    private var userDao: UserDao? = null

    init {
        if (mInstance == null) {
            userDao = GreenDaoManager.instance!!.session!!.userDao
        }
    }

    // 查询全部用户信息
    fun queryAll(): List<User> {
        val users = userDao!!.queryBuilder().list()
        for (user in users)
            Log.d("queryAll", user.toString())
        return users
    }

    // 懒加载-一般用户级联查询-返回所需要的数据-使用完之后需要自行关闭游标
    fun queryLazyList(): List<User> {
        val users = userDao!!.queryBuilder().listLazy()
        for (user in users)
            Log.d("queryLazyList", user.toString())
        users.close()
        return users
    }

    // 可以筛选想要的数据集
    fun queryIteratorList(): List<User> {
        val users = ArrayList<User>()
        val iterator = userDao!!.queryBuilder().listIterator()
        while (iterator.hasNext()) {
            val user = iterator.next() as User
            Log.d("queryIteratorList", user.toString())
            users.add(user)
        }
        return users
    }

    // uid相等查询eq
    fun queryEqUid(uId: Long): User {
        return userDao!!.queryBuilder().where(UserDao.Properties.UId.eq(uId)).unique()
    }

    // uid相等查询noteq
    fun queryNotEqUid(uId: Long): List<User> {
        return userDao!!.queryBuilder().where(UserDao.Properties.UId.notEq(uId)).list()
    }

    // uRealName模糊查询
    fun queryLikeRealName(uRealName: String): List<User> {
        return userDao!!.queryBuilder().where(UserDao.Properties.URealName.like(uRealName + "%")).list()
    }

    // uHeight范围查询
    fun queryBetweenHeight(minHeight: Float, maxHeight: Float): List<User> {
        return userDao!!.queryBuilder().where(UserDao.Properties.UHeight.between(minHeight, maxHeight)).list()
    }

    // uWeight大于某值集合
    fun queryGtWeight(uWeight: Float): List<User> {
        return userDao!!.queryBuilder().where(UserDao.Properties.UWeight.gt(uWeight)).list()
    }

    // uWeight大于等于某值集合
    fun queryGeWeight(uWeight: Float): List<User> {
        return userDao!!.queryBuilder().where(UserDao.Properties.UWeight.ge(uWeight)).list()
    }

    // uBirthday小于某值集合
    fun queryLtBirthday(uBirthday: String): List<User> {
        return userDao!!.queryBuilder().where(UserDao.Properties.UBirthday.lt(uBirthday)).list()
    }

    // uBirthday小于等于某值集合
    fun queryLeBirthday(uBirthday: String): List<User> {
        return userDao!!.queryBuilder().where(UserDao.Properties.UBirthday.le(uBirthday)).list()
    }

    // 排序查询-倒序
    fun queryListOrderDesc(): List<User> {
        return userDao!!.queryBuilder().orderDesc(UserDao.Properties.UBirthday).list()
    }

    // 根据id查找数据
    fun loadUser(_id: Long): User {
        return userDao!!.load(_id)
    }

    // 根据行号查找数据
    fun loadByRowId(rowId: Long): User {
        return userDao!!.loadByRowId(rowId)
    }

    // 查找全部数据
    fun loadAll(): List<User> {
        return userDao!!.loadAll()
    }

    // 插入数据-当指定主键在表中存在时会发生异常
    fun insertUser(user: User): Boolean {
        val uId = userDao!!.insert(user)
        return uId > 0
    }

    // 插入数据-当指定主键在表中存在时会覆盖数据
    fun insertOrReplaceUser(user: User): Boolean {
        val uId = userDao!!.insertOrReplace(user)
        return uId > 0
    }

    // 批量插入数据
    fun insertInTxUsers(users: List<User>) {
        userDao!!.insertInTx(users)
    }

    // 删除数据
    fun deleteUser(user: User) {
        userDao!!.delete(user)
    }

    // 指定主键删除数据
    fun deleteByKeyUser(_id: Long) {
        userDao!!.deleteByKey(_id)
    }

    // 批量删除数据
    fun deleteInTxUser(users: List<User>) {
        userDao!!.deleteInTx(users)
    }

    // 批量按数据删除数据
    fun deleteByKeyInTxUser(_ids: List<Long>) {
        userDao!!.deleteByKeyInTx(_ids)
    }

    // 删除所有数据
    fun deleteAll() {
        userDao!!.deleteAll()
    }

    // 修改User-主键需相同
    fun updateUser(user: User) {
        userDao!!.update(user)
    }

    // 批量更新数据
    fun updateInTxUser(users: List<User>) {
        userDao!!.updateInTx(users)
    }

    companion object {
        @Volatile private var mInstance: UserDbUtil? = null

        // 单例
        val instance: UserDbUtil?
            get() {
                if (mInstance == null) {
                    synchronized(UserDbUtil::class.java) {
                        if (mInstance == null) {
                            mInstance = UserDbUtil()
                        }
                    }
                }
                return mInstance
            }
    }
}
