package com.example.projectwarrior

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Plain : AppCompatActivity() {

    //Firebase references
    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plain)



        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference!!.child("userr")
        mAuth = FirebaseAuth.getInstance()

        val button1 = findViewById(R.id.button) as Button
        button1.setOnClickListener {

            insertdata();
        }
    }

    private fun insertdata() {
        val number = findViewById(R.id.your_number) as TextView
        val name = findViewById(R.id.your_Name) as EditText
        val address = findViewById(R.id.your_Address) as EditText
        val emailid = findViewById(R.id.your_Email) as EditText
        val contactNum = findViewById(R.id.your_phone) as EditText





        /*  var addressUser:String = address.text.toString().trim()
          var onwnerName:String  = ownerName.text.toString().trim
          var emailId:String  = email.text.toString()
          var parkingAvailable:String  = parking.text.toString()
          var onwnerPhone:String  = phone.text.toString()

          if (!TextUtils.isEmpty(addressUser) && !TextUtils.isEmpty(onwnerName)
                  && !TextUtils.isEmpty(emailId) && !TextUtils.isEmpty(parkingAvailable)  && !TextUtils.isEmpty(onwnerPhone)) {
  */

        val userId = mAuth!!.currentUser!!.uid


        var map= mutableMapOf<String,Any>()






        map["address"]="Shukla parking near 7-seas mall fatehganj vadodara"
        map["emailid"] = "desaidhruv013@gmail.com"
        map["owner name"]="Dhruv Desai"
        map["available spot"]="50"
        map["phone number"]="7600059607"
        map[" parking type"]="Four Wheeler only"
        map["parking charges"]="30 rupees"

        mDatabaseReference?.child("warrior")?.child("1")?.setValue(map)



        map["address"]="Sharma parkings near 7-seas mall fatehganj vadodara"
        map["emailid"] = "desaidhruv@gmail.com"
        map["owner name"]="Shivam Sharma"
        map["available spot"]="30"
        map["phone number"]="9933776655"
        map[" parking type"]="Four Wheeler only"
        map["parking charges"]="40 rupees"

        mDatabaseReference?.child("warrior")?.child("2")?.setValue(map)



        map["address"]=" newki parkings near 7-seas mall fatehganj vadodara"
        map["emailid"] = "desaidhr@gmail.com"
        map["owner name"]="jatindra patil"
        map["available spot"]="80"
        map["phone number"]="9990776655"
        map[" parking type"]="Four Wheeler and Two Wheelers"
        map["parking charges"]="20 rupees"


        mDatabaseReference?.child("warrior")?.child("3")?.setValue(map)



        map["address"]="shree parkings near 7-seas mall fatehganj vadodara"
        map["owner name"]="abhijeet karanveer singh"
        map["emailid"] = "desaidhr11@gmail.com"
        map["available spot"]="40"
        map["phone number"]="9955776655"
        map[" parking type"]="Four Wheeler"
        map["parking charges"]="40 rupees"


        mDatabaseReference?.child("warrior")?.child("4")?.setValue(map)



        map["address"]=" mata rani parkins in sayajiganj , vadodara"
        map["emailid"] = "desaidhr123@gmail.com"
        map["owner name"]="mohit mandoliya"
        map["available spot"]="65"
        map["phone number"]="9945776655"
        map[" parking type"]="Four Wheeler"
        map["parking charges"]="30 rupees"

        mDatabaseReference?.child("warrior")?.child("5")?.setValue(map)












    }




}