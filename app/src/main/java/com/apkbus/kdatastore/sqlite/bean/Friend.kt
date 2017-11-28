package com.apkbus.kdatastore.sqlite.bean

/**
 * 朋友-实体类
 * Created by 邹峰立 on 2017/9/23 0023.
 */
class Friend {
    private var fId: Long = 0// ID
    private var fGname: String? = null// 组名
    private var fUid: Long = 0// 用户ID

    constructor() : super() {}

    constructor(fId: Long, fGname: String, fUid: Long) {
        this.fId = fId
        this.fGname = fGname
        this.fUid = fUid
    }

    fun getfId(): Long {
        return fId
    }

    fun setfId(fId: Long) {
        this.fId = fId
    }

    fun getfGname(): String? {
        return fGname
    }

    fun setfGname(fGname: String) {
        this.fGname = fGname
    }

    fun getfUid(): Long {
        return fUid
    }

    fun setfUid(fUid: Long) {
        this.fUid = fUid
    }

    override fun toString(): String {
        return "Friend{" +
                "fId=" + fId +
                ", fGname='" + fGname + '\'' +
                ", fUid=" + fUid +
                '}'
    }
}
