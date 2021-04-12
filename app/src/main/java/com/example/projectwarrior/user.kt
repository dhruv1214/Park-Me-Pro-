package com.example.projectwarrior

import android.service.voice.VoiceInteractionSession

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract

class User{

    lateinit var email:String
    var number : Int=0
    //Default constructor required for calls to
    //DataSnapshot.getValue(User.class)
    constructor(){

    }

    constructor(number: Int,email: String){
       this.number=number
        this.email=email
    }
}