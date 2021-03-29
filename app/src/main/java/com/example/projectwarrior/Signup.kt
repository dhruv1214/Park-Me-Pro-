package com.example.projectwarrior

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern

class Signup: AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)
 auth = FirebaseAuth.getInstance()
        val button2 = findViewById(R.id.button_signup) as Button

        button2.setOnClickListener{
                signUpUser()
        }
    }

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