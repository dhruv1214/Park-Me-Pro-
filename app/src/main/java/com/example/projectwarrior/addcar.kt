package com.example.projectwarrior

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TimePicker
import java.util.*

class addcar : AppCompatActivity(),DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener{
    var day=0
    var month=0
    var year=0
    var hour=0
    var minute=0
    var savedDay=0
    var savedMonth=0
    var savedYear=0
    var savedHour=0
    var savedMinute=0


    var day2=0
    var month2=0
    var year2=0
    var hour2=0
    var minute2=0
    var savedday2=0
    var savedmonth2=0
    var savedyear2=0
    var savedhour2=0
    var savedminute2=0

    var testvalue=0

   lateinit var edittext1time:EditText
    lateinit var edittext2time:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addcar)

        //removing the titlebar from top
        //-->
      //  window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
       // setContentView(R.layout.activity_main)
        val button = findViewById(R.id.button_add) as Button
        var timer1button=findViewById<Button>(R.id.time1btn)
        var timer2button=findViewById<Button>(R.id.time2btn)
         edittext1time=findViewById<EditText>(R.id.time1)
        edittext2time=findViewById<EditText>(R.id.time2)
        button.setOnClickListener {
            startActivity(Intent(this,mybackground::class.java))
            finish()

        }

        timer1button.setOnClickListener {
            getDateTimeCalender2()
            DatePickerDialog(this,this,year2,month2,day2).show()
            testvalue=2
        }


            timer2button.setOnClickListener {
            getDateTimeCalender()
            DatePickerDialog(this,this,year,month,day).show()
            testvalue=1
        }

    }

    private fun getDateTimeCalender(){
        val cal= Calendar.getInstance()
        day=cal.get(Calendar.DAY_OF_MONTH)
        month=cal.get(Calendar.MONTH)
        year=cal.get(Calendar.YEAR)
        hour=cal.get(Calendar.HOUR)
        minute=cal.get(Calendar.MINUTE)
    }


    private fun getDateTimeCalender2(){
        val cal1=Calendar.getInstance()
        day2=cal1.get(Calendar.DAY_OF_MONTH)
        month2=cal1.get(Calendar.MONTH)
        year2=cal1.get(Calendar.YEAR)
        hour2=cal1.get(Calendar.HOUR)
        minute2=cal1.get(Calendar.MINUTE)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

        if(testvalue==1) {
            savedDay = dayOfMonth
            savedMonth = month
            savedYear = year

            getDateTimeCalender()
            TimePickerDialog(this, this, hour, minute, true).show()
        }

        if(testvalue==2){
            savedday2 = dayOfMonth
            savedmonth2 = month
            savedyear2 = year


            getDateTimeCalender2()
            TimePickerDialog(this, this, hour2, minute2, true).show()

        }
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {

        if(testvalue==1) {
            savedHour = hourOfDay
            savedMinute = minute

            edittext2time.setText("($savedHour : $savedMinute ) , Date: $savedDay - $savedMonth - $savedYear")

        }

        if(testvalue==2) {
            savedhour2 = hourOfDay
            savedminute2 = minute

            edittext1time.setText(" ( $savedhour2 : $savedminute2 ) , Date: $savedday2 - $savedmonth2 - $savedyear2")

        }


    }


}