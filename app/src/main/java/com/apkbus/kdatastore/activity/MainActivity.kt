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
        val fileBtn = findViewById<Button>(R.id.btn_file)
        fileBtn.setOnClickListener(this)
    }

    // 控件点击事件监听
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_file -> {//文件存储
                val intent_file = Intent(this, FileActivity::class.java)
                startActivity(intent_file)
            }

        }
    }
}
