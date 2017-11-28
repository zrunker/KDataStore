package com.apkbus.kdatastore.sqlite.bean

/**
 * 用户-实体类
 * Created by 邹峰立 on 2017/9/23 0023.
 */
class User {
    private var uId: Long = 0
    private var uRealName: String? = null
    private var uSex: String? = null
    private var uBirthday: String? = null
    private var uHeight: Float = 0.toFloat()
    private var uWeight: Float = 0.toFloat()
    private var uDomicile: String? = null
    private var uPhone: Long = 0
    private var uEmail: String? = null
    private var uWeibo: String? = null

    constructor() : super() {}

    constructor(uId: Long, uRealName: String, uSex: String, uBirthday: String, uHeight: Float, uWeight: Float, uDomicile: String, uPhone: Long, uEmail: String, uWeibo: String) {
        this.uId = uId
        this.uRealName = uRealName
        this.uSex = uSex
        this.uBirthday = uBirthday
        this.uHeight = uHeight
        this.uWeight = uWeight
        this.uDomicile = uDomicile
        this.uPhone = uPhone
        this.uEmail = uEmail
        this.uWeibo = uWeibo
    }

    fun getuId(): Long {
        return uId
    }

    fun setuId(uId: Long) {
        this.uId = uId
    }

    fun getuRealName(): String? {
        return uRealName
    }

    fun setuRealName(uRealName: String) {
        this.uRealName = uRealName
    }

    fun getuSex(): String? {
        return uSex
    }

    fun setuSex(uSex: String) {
        this.uSex = uSex
    }

    fun getuBirthday(): String? {
        return uBirthday
    }

    fun setuBirthday(uBirthday: String) {
        this.uBirthday = uBirthday
    }

    fun getuHeight(): Float {
        return uHeight
    }

    fun setuHeight(uHeight: Float) {
        this.uHeight = uHeight
    }

    fun getuWeight(): Float {
        return uWeight
    }

    fun setuWeight(uWeight: Float) {
        this.uWeight = uWeight
    }

    fun getuDomicile(): String? {
        return uDomicile
    }

    fun setuDomicile(uDomicile: String) {
        this.uDomicile = uDomicile
    }

    fun getuPhone(): Long {
        return uPhone
    }

    fun setuPhone(uPhone: Long) {
        this.uPhone = uPhone
    }

    fun getuEmail(): String? {
        return uEmail
    }

    fun setuEmail(uEmail: String) {
        this.uEmail = uEmail
    }

    fun getuWeibo(): String? {
        return uWeibo
    }

    fun setuWeibo(uWeibo: String) {
        this.uWeibo = uWeibo
    }

    override fun toString(): String {
        return "User{" +
                "uId=" + uId +
                ", uRealName='" + uRealName + '\'' +
                ", uSex='" + uSex + '\'' +
                ", uBirthday='" + uBirthday + '\'' +
                ", uHeight=" + uHeight +
                ", uWeight=" + uWeight +
                ", uDomicile='" + uDomicile + '\'' +
                ", uPhone=" + uPhone +
                ", uEmail='" + uEmail + '\'' +
                ", uWeibo='" + uWeibo + '\'' +
                '}'
    }
}
