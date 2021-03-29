package com.example.projectwarrior



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager

class removecar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.removecar)

        //removing the titlebar from top
        //-->
       // window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
       // setContentView(R.layout.activity_main)
    }
}