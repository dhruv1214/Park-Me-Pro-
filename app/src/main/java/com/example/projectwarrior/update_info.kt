package com.example.projectwarrior

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_plain.*
import kotlinx.android.synthetic.main.activity_update_info.*

class update_info : AppCompatActivity() {
    private var userId: String? = null
    private var mFirebaseDatabase: DatabaseReference? = null
    private var mFirebaseInstance: FirebaseDatabase? = null
    lateinit var mykeyvalue: String
    lateinit var universaladdress: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_update_info)
        mFirebaseInstance = FirebaseDatabase.getInstance()
        //queryobserveread()
        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance!!.getReference("userr")
        val user = FirebaseAuth.getInstance().getCurrentUser()

        userId = user?.getUid()
        //   getparentnode()


        button_update.setOnClickListener {
            updateUser(info_edittext)
        }
    }

    private fun updateUser(name_edittext : EditText) {


        val number = info_edittext.getText().toString()
        val email = name_edittext.getText().toString()
     

            mFirebaseDatabase!!.child("warrior").child("1").child("available spot").setValue(number)
            Toast.makeText(applicationContext, "Successfully updated user", Toast.LENGTH_SHORT).show()

        name_edittext.setText("")
    }
}


   /* fun queryobserveread(){

        FirebaseDatabase.getInstance().reference
                .child("userr").child("warrior")
                .orderByChild("emailid").equalTo(FirebaseAuth.getInstance().currentUser.email)
                .addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {

                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        var map=p0.children.first().value as Map<String,Any>
                        universaladdress=map["emailid"].toString()


                    }
                })

    }*/

   /* fun getparentnode() {
        if (!TextUtils.isEmpty(universaladdress)) {
            val rootRef = FirebaseDatabase.getInstance().getReference("userr")
            val query: Query = rootRef.child("warrior").orderByChild("emailid").equalTo(universaladdress)
            val valueEventListener: ValueEventListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (ds in dataSnapshot.children) {
                        val key = ds.key
                        chek_textview.text = key!!.toString()


                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {

                }
            }
            query.addListenerForSingleValueEvent(valueEventListener)
        }
    }*/


