package com.marco.github

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("lhc","email:${Setting.email}")
        Log.d("lhc","password:${Setting.password}")
        findViewById<Button>(R.id.btnClick).setOnClickListener {
            Setting.email = "haochenli200@qq.com"
            Setting.password = "123456"
        }
    }
}