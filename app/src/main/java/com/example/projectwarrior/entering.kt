package com.example.projectwarrior

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class entering : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
// ...
// Initialize Firebase Auth

// ...
// Initialize Firebase Auth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.entering)

        val email = findViewById<EditText>(R.id.email_edittext)
        val password = findViewById<EditText>(R.id.password_edittext)
        val button = findViewById<Button>(R.id.button_login)
        val button1 = findViewById<Button>(R.id.button_signup)
        auth = FirebaseAuth.getInstance()

        button1.setOnClickListener {
        startActivity(Intent(this,mybackground::class.java))

        }
        button.setOnClickListener{


            doLogin()
            }

        }

    private fun doLogin() {
        val email = findViewById<EditText>(R.id.email_edittext)
        val password= findViewById<EditText>(R.id.password_edittext)
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

        auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        updateUI(user)

                    } else {

                        Toast.makeText(baseContext, " ",
                                Toast.LENGTH_SHORT).show()
                            updateUI(null)

                    }
                }
    }


    public override fun onStart() {
        super.onStart()
        val user = auth.currentUser
        updateUI(user)
    }
    private fun updateUI(user: FirebaseUser?) {
        if(user != null) {
            if(user.isEmailVerified){
            startActivity(Intent(this,Plain::class.java))
            finish()
        }
        else {
                Toast.makeText(
                        baseContext, "Please verified your email id ",
                        Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(
                    baseContext, " ",Toast.LENGTH_SHORT).show()

        }

    }
}























       