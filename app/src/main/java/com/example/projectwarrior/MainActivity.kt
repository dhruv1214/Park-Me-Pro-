package com.example.projectwarrior

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.projectwarrior.R.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        //removing the titlebar from top
        //-->
     //   window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
      //  setContentView(layout.activity_main)


        //adding animation xml files in the kotlin
        //-->
        lateinit var topanim: Animation
        lateinit var bottom: Animation
        lateinit var bottom2: Animation
        //splash screen timer value defined constant
        val SPLASH_SCREEN=4000


//addoing the animation.xml files in the
        topanim= AnimationUtils.loadAnimation(this, anim.upanimation)
        bottom= AnimationUtils.loadAnimation(this, anim.downanimation)
        bottom2= AnimationUtils.loadAnimation(this, anim.downanimation2)
        //setting the animation for the single element of this activity


        val logo = findViewById(R.id.logo) as ImageView
        val appname1 = findViewById(R.id.appname1) as TextView
        val appname2 = findViewById(R.id.appname2) as TextView

        logo.animation = topanim
        appname1.animation = bottom
        appname2.animation=bottom2

        //giving the splash screen for the element
        Handler().postDelayed(
            { startActivity( Intent(this,entering::class.java))
                finish()
            }, SPLASH_SCREEN.toLong()//timer which is declared as constant above

        )
    }
}