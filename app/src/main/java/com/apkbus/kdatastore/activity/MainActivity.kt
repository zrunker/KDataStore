package com.apkbus.kdatastore.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import com.apkbus.kdatastore.R

/**
 * Kotlin工程入口页
 *
 * create by 邹峰立
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    // 初始化控件
    private fun init() {
        val spBtn = findViewById<Button>(R.id.btn_sp)
        spBtn.setOnClickListener(this)
        val fileBtn = findViewById<Button>(R.id.btn_file)
        fileBtn.setOnClickListener(this)
        val sqliteBtn = findViewById<Button>(R.id.btn_sqlite)
        sqliteBtn.setOnClickListener(this)
        val greendaoBtn = findViewById<Button>(R.id.btn_greendao)
        greendaoBtn.setOnClickListener(this)
    }

    // 控件点击事件监听
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_sp -> {// SharedPreferences
                val intent_sp = Intent(this, SharedPreferencesActivity::class.java)
                startActivity(intent_sp)
            }
            R.id.btn_file -> {// 文件存储
                val intent_file = Intent(this, FileActivity::class.java)
                startActivity(intent_file)
            }
            R.id.btn_sqlite -> {// SQLite
                val intent_sqlite = Intent(this, SQLiteActivity::class.java)
                startActivity(intent_sqlite)
            }
            R.id.btn_greendao -> {// GreenDao
                val intent_greendao = Intent(this, GreenDaoActivity::class.java)
                startActivity(intent_greendao)
            }
        }
    }
}
