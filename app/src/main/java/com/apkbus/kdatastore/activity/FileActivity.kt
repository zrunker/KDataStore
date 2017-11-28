package com.apkbus.kdatastore.activity

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.apkbus.kdatastore.R
import java.io.*
import java.util.*

/**
 * 文件存储
 * 注意：文件存储需要在清单文件中添加操作权限 Android6.0+还需要动态申请权限
 *
 * Created by 邹峰立 on 2017/9/19 0019.
 */
class FileActivity : AppCompatActivity(), View.OnClickListener {
    private val PERMISSION_OPER_EXTERNAL_STORAGE = 55
    private val permissions = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS,
            Manifest.permission.WRITE_EXTERNAL_STORAGE)
    private var sdOperType = 0
    private var editText: EditText? = null
    private var textView: TextView? = null
    private val mKey = "mKey"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file)

        initView()
    }

    // 初始化控件
    private fun initView() {
        editText = findViewById(R.id.edittext)
        textView = findViewById(R.id.text)
        val saveMemoryBtn = findViewById<Button>(R.id.btn_memory)
        saveMemoryBtn.setOnClickListener(this)
        val sdBtn = findViewById<Button>(R.id.btn_sd)
        sdBtn.setOnClickListener(this)
        val readMemoryBtn = findViewById<Button>(R.id.btn_read_memory)
        readMemoryBtn.setOnClickListener(this)
        val readSdBtn = findViewById<Button>(R.id.btn_read_sd)
        readSdBtn.setOnClickListener(this)
    }

    // 按钮点击事件监听
    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_memory -> {// 保存到内存
                val text = editText!!.text.toString().trim { it <= ' ' }
                if (!TextUtils.isEmpty(text)) {
                    val bool = writeMemoryData(text)
                    if (bool) {
                        Toast.makeText(this, "写入成功", Toast.LENGTH_SHORT).show()
                        editText!!.setText("")
                    } else
                        Toast.makeText(this, "写入失败", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.btn_sd -> {// 保存到SD卡
                applyPermission()
                sdOperType = 1
            }
            R.id.btn_read_memory -> {// 读取内存
                val str = readMemoryData(mKey)!!.toString()
                textView!!.text = str
            }
            R.id.btn_read_sd -> {// 读取SD卡
                applyPermission()
                sdOperType = 2
            }
        }
    }

    /**
     * 保存数据到内存
     *
     * @param obj 待保存数据
     * @return true/false(成功/失败)
     */
    private fun writeMemoryData(obj: Any): Boolean {
        var bool = false
        var fos: FileOutputStream? = null
        try {
            // 构建Properties
            val properties = Properties()
            // Properties添加数据
            properties.put(mKey, obj)
            fos = this.openFileOutput("testmemory.json", Context.MODE_PRIVATE)
            // 将数据写入文件（流）
            properties.store(fos, "测试文件")
            bool = true
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (fos != null)
                try {
                    fos.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

        }
        return bool
    }

    /**
     * 读取内存数据
     *
     * @param key 数据对应键值
     * @return 待读取的数据
     */
    private fun readMemoryData(key: String): Any? {
        var obj: Any? = null
        var fis: FileInputStream? = null
        try {
            // 构建Properties
            val properties = Properties()
            fis = this.openFileInput("testmemory.json")
            // 加载文件
            properties.load(fis)
            obj = properties[key]
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (fis != null)
                try {
                    fis.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

        }
        return obj
    }

    /**
     * 操作SD卡
     */
    private fun operSD() {
        when (sdOperType) {
            1 -> {// 保存数据到SD卡
                val text1 = editText!!.text.toString().trim { it <= ' ' }
                if (!TextUtils.isEmpty(text1)) {
                    val bool = writeSdData(text1)
                    if (bool) {
                        Toast.makeText(this, "写入成功", Toast.LENGTH_SHORT).show()
                        editText!!.setText("")
                    } else
                        Toast.makeText(this, "写入失败", Toast.LENGTH_SHORT).show()
                }
            }
            2 -> {// 读取SD卡数据
                val str1 = readSdData()
                textView!!.text = str1
            }
        }
    }

    /**
     * 写入SD卡文件
     *
     * @param obj 待写入对象
     */
    private fun writeSdData(obj: Any): Boolean {
        var bool = false
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {// 判断外部存储是否可读可写
            var raf: RandomAccessFile? = null
            try {
                // 获取SD卡路径
                val sdDir = Environment.getExternalStorageDirectory()
                // 获取SD卡目录 /mnt/sdcard。
                val sdPath = sdDir.absolutePath
                // 创建文件
                val file = File(sdPath, "testsd.json")
                if (!file.exists()) {
                    val bool1 = file.createNewFile()
                    if (!bool1)
                        return false
                }


//                var fos: FileOutputStream? = null
//                try {
//                    fos = FileOutputStream(file)
//                    fos.write(obj.toString().toByteArray())
//                } catch (e: FileNotFoundException) {
//                    e.printStackTrace()
//                } catch (e: IOException) {
//                    e.printStackTrace()
//                } finally {
//                    try {
//                        if (fos != null)
//                            fos.close()
//                    } catch (e: IOException) {
//                        e.printStackTrace()
//                    }
//
//                }


                // 指定文件创建RandomAccessFile对象
                raf = RandomAccessFile(file, "rw")
                // 将文件记录指针移动最后
                raf.seek(file.length())
                // 写入内容
                raf.write(obj.toString().toByteArray())
                bool = true
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                try {
                    if (raf != null)
                        raf.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
        }
        return bool
    }

    /**
     * 读取SD卡文件内容
     */
    private fun readSdData(): String {
        val sb = StringBuilder()
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {// 判断外部存储是否可读可写
            // 获取SD卡路径
            val sdDir = Environment.getExternalStorageDirectory()
            // 获取SD卡目录 /mnt/sdcard。
            val sdPath = sdDir.absolutePath
            // 创建文件
            val file = File(sdPath, "testsd.json")

            var ism: InputStream? = null
            try {
                ism = FileInputStream(file)
                val buffer = ByteArray(1024)
                var len: Int
                while (true) {
                    len = ism.read(buffer)
                    if (len != -1)
                        sb.append(String(buffer, 0, len))
                    else
                        break
                }
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                try {
                    if (ism != null)
                        ism.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
        }
        return sb.toString()
    }

    // Android6.0+ 动态申请文件读写权限
    private fun applyPermission() {
        if (!hasPermission(*permissions)) {
            requestPermission(PERMISSION_OPER_EXTERNAL_STORAGE, *permissions)
        } else {
            operSD()
        }
    }

    // 权限请求回调
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_OPER_EXTERNAL_STORAGE ->// SD卡读写权限成功
                operSD()
        }
    }

    /**
     * 权限检查方法
     */
    fun hasPermission(vararg permissions: String): Boolean {
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    /**
     * 权限请求方法
     */
    fun requestPermission(code: Int, vararg permissions: String) {
        ActivityCompat.requestPermissions(this, permissions, code)
    }

}