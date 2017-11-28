package com.apkbus.kdatastore.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.apkbus.kdatastore.greendao.entity.User
import com.apkbus.kdatastore.greendao.utils.UserDbUtil


/**
 * greenDao3.0实现orm
 * Created by 邹峰立 on 2017/9/23 0023.
 */
class GreenDaoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 初始化UserDbUtil
        val userDbUtil = UserDbUtil.instance
        // 删除数据中所有数据
        userDbUtil!!.deleteAll()
        // 插入数据
        for (k in 0..3) {
            val user = User()
            user.setuId(110L + k)
            user.setuBirthday("2000-10-10" + k)
            user.setuDomicile("福建" + k)
            user.setuEmail("zrunker" + k)
            user.setuPhone(10086L + k)
            user.setuHeight(175F + k)
            user.setuSex("男" + k)
            user.setuRealName("zrunker" + k)
            userDbUtil.insertOrReplaceUser(user)
        }
        // 查询数据
        val users = userDbUtil.queryAll()
        for (i in users.indices) {
            Log.d("User=", users.get(i).toString())
        }
    }

}
