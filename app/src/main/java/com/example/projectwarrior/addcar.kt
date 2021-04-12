package com.example.projectwarrior

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowManager
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.*

class addcar : AppCompatActivity(),DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {

    var status: String = "Active"

    var day = 0
    var month = 0
    var year = 0
    var hour = 0
    var minute = 0
    var savedDay = 0
    var savedMonth = 0
    var savedYear = 0
    var savedHour = 0
    var savedMinute = 0


    var day2 = 0
    var month2 = 0
    var year2 = 0
    var hour2 = 0
    var minute2 = 0
    var savedday2 = 0
    var savedmonth2 = 0
    var savedyear2 = 0
    var savedhour2 = 0
    var savedminute2 = 0

    var testvalue = 0

    lateinit var edittext1time: EditText
    lateinit var edittext2time: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.addcar)

        //removing the titlebar from top

        //  window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        // setContentView(R.layout.activity_main)
        val button = findViewById(R.id.button_add) as Button
        var timer1button = findViewById<Button>(R.id.time1btn)
        var timer2button = findViewById<Button>(R.id.time2btn)
        edittext1time = findViewById<EditText>(R.id.time1)
        edittext2time = findViewById<EditText>(R.id.time2)
        queryobserveread()
        button.setOnClickListener {
            insertdata()
            Toast.makeText(this, "Car is added", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, mybackground::class.java))
            finish()

        }
        timer1button.setOnClickListener {
            getDateTimeCalender2()
            DatePickerDialog(this, this, year2, month2, day2).show()
            testvalue = 2
        }


        timer2button.setOnClickListener {
            getDateTimeCalender()
            DatePickerDialog(this, this, year, month, day).show()
            testvalue = 1
        }

    }

    private fun getDateTimeCalender() {
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)
    }


    private fun getDateTimeCalender2() {
        val cal1 = Calendar.getInstance()
        day2 = cal1.get(Calendar.DAY_OF_MONTH)
        month2 = cal1.get(Calendar.MONTH)
        year2 = cal1.get(Calendar.YEAR)
        hour2 = cal1.get(Calendar.HOUR)
        minute2 = cal1.get(Calendar.MINUTE)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

        if (testvalue == 1) {
            savedDay = dayOfMonth
            savedMonth = month
            savedYear = year

            getDateTimeCalender()
            TimePickerDialog(this, this, hour, minute, true).show()
        }

        if (testvalue == 2) {
            savedday2 = dayOfMonth
            savedmonth2 = month
            savedyear2 = year


            getDateTimeCalender2()
            TimePickerDialog(this, this, hour2, minute2, true).show()

        }
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {

        if (testvalue == 1) {
            savedHour = hourOfDay
            savedMinute = minute

            edittext2time.setText("($savedHour : $savedMinute ) , Date: $savedDay - $savedMonth - $savedYear")

        }

        if (testvalue == 2) {
            savedhour2 = hourOfDay
            savedminute2 = minute

            edittext1time.setText(" ( $savedhour2 : $savedminute2 ) , Date: $savedday2 - $savedmonth2 - $savedyear2")

        }


    }

    fun insertdata() {
        var address_text=findViewById<EditText>(R.id.name_edittext)
        var parking_time = findViewById<EditText>(R.id.time1)
        var parking_time2 = findViewById<EditText>(R.id.time2)
        var emailof_user = findViewById<EditText>(R.id.emailofuser)
        var hour_hault = findViewById<EditText>(R.id.numberofhours)
        var user_phone = findViewById<EditText>(R.id.phonenumber)
        var vehicle_num = findViewById<EditText>(R.id.vehiclenumber)

        var addressValue=address_text.text.toString()
        var parkingTime = parking_time.text.toString()
        var delayTime = parking_time2.text.toString()
        var emailIdOfUser = emailof_user.text.toString()
        var numberOfHours = hour_hault.text.toString()
        var phonenumberofuser = user_phone.text.toString()
        var vehiclenumber = vehicle_num.text.toString()




        if (!TextUtils.isEmpty(addressValue) && !TextUtils.isEmpty(parkingTime)
                && !TextUtils.isEmpty(delayTime) && !TextUtils.isEmpty(numberOfHours) && !TextUtils.isEmpty(emailIdOfUser) && !TextUtils.isEmpty(vehiclenumber)) {
            //  val userId = mAuth!!.currentUser!!.uid

            var map = mutableMapOf<String, Any>()
            map["Address"] = addressValue
            map["Parking Time"] = parkingTime
            map["Delay Time"] = delayTime
            map["Number Of Hours"] = numberOfHours
            map["email_of_user"] = emailIdOfUser
            map["phonenumber"] = phonenumberofuser
            map["vehiclenumber"] = vehiclenumber
            map["Status"] = status

            // map["number of vehicle"]=numberplate
            // map["name of owner"]=name


            FirebaseDatabase.getInstance().reference
                    .child("userr").child("parking").push().setValue(map)
        } else {

            Toast.makeText(this, "Please enter all details ", Toast.LENGTH_SHORT).show()

        }
    }


    fun queryobserveread() {

        var addresstext=findViewById<EditText>(R.id.name_edittext)
        FirebaseDatabase.getInstance().reference
                .child("userr").child("warrior")
                .orderByChild("emailid").equalTo(FirebaseAuth.getInstance().currentUser.email)
                .addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {

                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        var map = p0.children.first().value as Map<String, Any>
                        addresstext.setText(map["address"].toString())
                    }
                })

    }
}
