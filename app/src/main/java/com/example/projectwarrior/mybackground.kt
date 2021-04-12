package com.example.projectwarrior

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.mybackground.*

class mybackground : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.mybackground)

        queryobserveread()

        update_proper_spots.setOnClickListener {
            startActivity(Intent(this,update_info::class.java))
        }


        val chekavailability=findViewById<Button>(R.id.examine_car_status1)

        chekavailability.setOnClickListener {
            startActivity(Intent(this,check_car_status::class.java))
        }
        val button = findViewById(R.id.button1) as Button
        button.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, entering::class.java))
            finish()

        }
        var add_car_manually = this.findViewById(R.id.add_car_manually) as Button
        add_car_manually.setOnClickListener {
            startActivity(Intent(this, addcar::class.java))

        }
        var remove_car_manually1 = this.findViewById(R.id.remove_car_manually1) as Button
        remove_car_manually1.setOnClickListener {
            startActivity(Intent(this,removecar::class.java))
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
