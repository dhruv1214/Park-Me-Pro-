package com.example.projectwarrior

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.signup.*
import java.util.regex.Pattern

class Signup: AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.signup)
         auth = FirebaseAuth.getInstance()
        val button2 = findViewById(R.id.button_signup) as Button

        button2.setOnClickListener{
                signUpUser()
        }
       // initialise()
    }
   /* private fun initialise() {


        button_forget!!.setOnClickListener {
            sendPassword() }
    }

    private fun sendPassword() {
        val email = email_edittext?.text.toString()
        if (!TextUtils.isEmpty(email)) {
            auth!!
                    .sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val message = "Email sent."

                            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

                        } else {

                            Toast.makeText(this, "No user found with this email.", Toast.LENGTH_SHORT).show()
                        }
                    }
        } else {
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show()
        }
    } */

    private fun signUpUser() {
        val email = findViewById(R.id.email_edittext) as EditText
        val password= findViewById(R.id.password_edittext) as EditText
        if(email.text.toString().isEmpty()) {
            email.error = "Please provide email id"
            email.requestFocus()
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()){
            email.error = "Please Provide Valid email id"
            email.requestFocus()
            return
        }

        if(password.text.toString().isEmpty()) {
            password.error = "Please provide Password"
            password.requestFocus()
            return
        }


       auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
               .addOnCompleteListener(this) { task ->
                   if (task.isSuccessful) {
                       val user = auth.currentUser
                       user!!.sendEmailVerification()
                               .addOnCompleteListener { task ->
                                   if (task.isSuccessful) {
                                       startActivity(Intent(this,entering::class.java))
                                       finish()
                                   }
                               }


                   } else {
                       Toast.makeText(baseContext, "User Already exists",
                               Toast.LENGTH_SHORT).show()

                   }


               }

       }



    }
