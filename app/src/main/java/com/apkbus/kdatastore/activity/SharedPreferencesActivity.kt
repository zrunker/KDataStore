package com.apkbus.kdatastore.activity

import android.app.Activity
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import com.apkbus.kdatastore.R

/**
 * SharedPreferences存储
 * Created by 邹峰立 on 2017/9/19 0019.
 */
class SharedPreferencesActivity : AppCompatActivity() {
    private var nameEd: EditText? = null
    private var passwdEd: EditText? = null
    private var checkBox: CheckBox? = null
    private val mKeyPasswd = "passwd"
    private val mKeyName = "name"

    // 获取sharedPreferences对象
    // SharedPreferences sharedPreferences = getSharedPreferences("mName", Context.MODE_PRIVATE);
    // SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    // 如果不需要与其他模块数据共享使用
    private val sharedPreferences: SharedPreferences
        get() = getPreferences(Activity.MODE_PRIVATE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sharedpreferences)

        initView()
    }

    // 初始化控件
    private fun initView() {
        nameEd = findViewById(R.id.ed_name)
        passwdEd = findViewById(R.id.ed_passwd)
        checkBox = findViewById(R.id.checkbox)
        // 赋值
        nameEd!!.setText(readData(mKeyName).toString())
        passwdEd!!.setText(readData(mKeyPasswd).toString())
        val button = findViewById<Button>(R.id.btn_submit)
        button.setOnClickListener {
            if (checkBox!!.isChecked) {
                val name = nameEd!!.text.toString().trim { it <= ' ' }
                val passwd = passwdEd!!.text.toString().trim { it <= ' ' }
                val nameBool = saveData(name, mKeyName)
                val passwdPool = saveData(passwd, mKeyPasswd)
                Log.d("bool", nameBool.toString() + "--" + passwdPool)
            } else {
                remove()
            }
            finish()
        }
    }

    // 移除数据
    private fun remove() {
        val sharedPreferences = sharedPreferences
        // 获取编辑对象
        val editor = sharedPreferences.edit()
        // 移除
        editor.remove(mKeyPasswd)
        // 提交
        editor.apply()// 后台提交
    }

    // 清空数据
    private fun clearAll() {
        val sharedPreferences = sharedPreferences
        // 获取编辑对象
        val editor = sharedPreferences.edit()
        // 清空
        editor.clear()
        // 提交
        editor.apply()// 后台提交
    }

    /**
     * 保存数据
     *
     * @param obj 待保存数据
     * @return true/false
     */
    private fun saveData(obj: Any, key: String): Boolean {
        val sharedPreferences = sharedPreferences
        // 获取编辑对象
        val editor = sharedPreferences.edit()
        // 添加数据
        editor.putString(key, obj.toString())
        // 提交保存
        // editor.apply();// 后台提交
        return editor.commit()// 马上提交
    }

    /**
     * 读取值
     *
     * @param key 键值
     * @return 待读取内容
     */
    private fun readData(key: String): Any {
        val sharedPreferences = sharedPreferences
        // 获取值
        return sharedPreferences.getString(key, "")
    }
}
