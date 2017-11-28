package com.apkbus.kdatastore.sqlite.dbhelper

import com.apkbus.kdatastore.sqlite.bean.User

/**
 * 数据库访问接口
 * Created by 邹封立 on 2017/2/16 0016.
 */
interface SQLiteDao {

    /**
     * 获取用户集合
     */
    fun selectUser(): List<User>

    /**
     * 根据用户ID获取用户信息
     *
     * @param uId 用户ID
     */
    fun selectUserByuId(uId: Long): User

    /**
     * 根据用户ID修改用户信息
     */
    fun updateUserByuId(user: User)

    /**
     * 插入用户信息
     */
    fun insertUser(user: User)

    /**
     * 根据用户ID删除用户ID
     *
     * @param uId 用户ID
     */
    fun deleteUserByUid(uId: Long)
}
