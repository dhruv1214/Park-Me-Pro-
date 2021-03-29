package com.example.projectwarrior

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.concurrent.ThreadLocalRandom.current


class mybackground : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mybackground)

        queryobserveread()

        //removing the titlebar from top
        //run okk okk

      //  var add_car_manually = this.findViewById(R.id.add_car_manually) as Button
       // add_car_manually.setOnClickListener {
         //   startActivity(Intent(this, addcar::class.java))
       // }
       // var button_end = findViewById(R.id.button1) as Button
        //button_end.setOnClickListener {
         //   startActivity(Intent(this,entering::class.java))
          //  finish()
        //}
        //var remove_car_manually1 = this.findViewById(R.id.remove_car_manually1) as Button
        //remove_car_manually1.setOnClickListener {
          //  startActivity(
            //    Intent(
              //      this,
                //    entering::class.java
                //)
            //)
       // }// what to do next? there isnumber pver there in textvie see??


        val button = findViewById(R.id.button1) as Button
        button.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this,entering::class.java))
            finish()

        }
        var add_car_manually = this.findViewById(R.id.add_car_manually) as Button
         add_car_manually.setOnClickListener {
           startActivity(Intent(this, addcar::class.java))

         }
    }
    fun queryobserveread(){
        var passemailid = findViewById<TextView>(R.id.passemail)
        FirebaseDatabase.getInstance().reference
            .child("userr").child("warrior")
            .orderByChild("emailid").equalTo(FirebaseAuth.getInstance().currentUser.email)
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(p0: DataSnapshot) {
                    var map=p0.children.first().value as Map<String,Any>
                    passemailid.text=map["available spot"].toString()
                }
            })
    }
    }
