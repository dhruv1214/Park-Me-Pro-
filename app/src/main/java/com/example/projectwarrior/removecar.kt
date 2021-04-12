package com.example.projectwarrior



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_update_info.*
import kotlinx.android.synthetic.main.addcar.*
import kotlinx.android.synthetic.main.removecar.*

class removecar : AppCompatActivity() {


    private var mFirebaseDatabase: DatabaseReference? = null
    private var mFirebaseInstance: FirebaseDatabase? = null

    private var userId: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.removecar)

        var carremove = findViewById<Button>(R.id.button_removal)
        var checkdetail = findViewById<Button>(R.id.button_check)



        mFirebaseInstance = FirebaseDatabase.getInstance()

        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance!!.getReference("userr")

        val user = FirebaseAuth.getInstance().getCurrentUser()

        // add it only if it is not saved to database
        userId = user?.getUid()
button_check.setOnClickListener {  searchquerybasedonemail() }
        button_removal.setOnClickListener {
            //val name = info_edittext.getText().toString()
            DeleteUser(email_edittext)
        }

    }

    fun DeleteUser(email_edittext: EditText) {

        mFirebaseDatabase!!.child("parking").removeValue()
        Toast.makeText(applicationContext, "Successfully deleted user", Toast.LENGTH_SHORT).show()

        // clear information

        email_edittext.setText("")

    }


    fun searchquerybasedonemail() {

        var myemail = findViewById<EditText>(R.id.email_edittext)
        var phonenumber = findViewById<TextView>(R.id.nametextview)
        var vehiclenumber = findViewById<TextView>(R.id.vehicleno_textview)
        FirebaseDatabase.getInstance().reference
                .child("userr").child("parking")
                .orderByChild("email_of_user").equalTo(myemail.text.toString())
                .addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {

                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        var map = p0.children.first().value as Map<String, Any>
                        phonenumber.text = map["phonenumber"].toString()
                        vehiclenumber.text = map["vehiclenumber"].toString()

                    }
                })

    }
}
