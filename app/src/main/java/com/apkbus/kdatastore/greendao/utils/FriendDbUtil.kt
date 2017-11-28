package com.apkbus.kdatastore.greendao.utils

import android.util.Log

import com.apkbus.kdatastore.greendao.FriendDao
import com.apkbus.kdatastore.greendao.entity.Friend

import org.greenrobot.greendao.query.LazyList

import java.util.ArrayList


/**
 * 朋友表t_friend进行管理
 * Created by 邹峰立 on 2017/9/23 0023.
 */
class FriendDbUtil// 构造方法
private constructor() {
    private var friendDao: FriendDao? = null

    init {
        if (mInstance == null) {
            friendDao = GreenDaoManager.instance!!.session!!.friendDao
        }
    }

    // 查询全部朋友信息
    private fun queryAll(): List<Friend> {
        val friends = friendDao!!.queryBuilder().list()
        for (friend in friends)
            Log.d("queryAll", friend.toString())
        return friends
    }

    // 懒加载-一般用于级联查询-返回所需要的数据-使用完之后需要自行关闭游标
    fun queryLazyList(): List<Friend> {
        val friends = friendDao!!.queryBuilder().listLazy()
        for (friend in friends)
            Log.d("queryLazyList", friend.toString())
        friends.close()
        return friends
    }

    // 可以筛选想要的数据集
    fun queryIteratorList(): List<Friend> {
        val friends = ArrayList<Friend>()
        val iterator = friendDao!!.queryBuilder().listIterator()
        while (iterator.hasNext()) {
            val friend = iterator.next() as Friend
            Log.d("queryIteratorList", friend.toString())
            friends.add(friend)
        }
        return friends
    }

    // 插入数据
    fun insertFriend(friend: Friend): Boolean {
        val fId = friendDao!!.insert(friend)
        return fId > 0
    }

    // 修改Friend
    fun updateFriend(friend: Friend) {
        friendDao!!.update(friend)
    }

    companion object {
        @Volatile private var mInstance: FriendDbUtil? = null

        // 单例
        val instance: FriendDbUtil?
            get() {
                if (mInstance == null) {
                    synchronized(UserDbUtil::class.java) {
                        if (mInstance == null) {
                            mInstance = FriendDbUtil()
                        }
                    }
                }
                return mInstance
            }
    }
}
