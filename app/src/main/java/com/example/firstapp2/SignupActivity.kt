package com.example.firstapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.UserData
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val database = Firebase.database
        val name: EditText = findViewById(R.id.name_edittext)
        val email: EditText = findViewById(R.id.email_edittext)
        val submit_button: Button = findViewById(R.id.submit_button)
        val uid = FirebaseAuth.getInstance().uid


        submit_button.setOnClickListener {
            val userdata = User(name.text.toString(),email.text.toString())
            val data = userdata.toMap()
            val userupdates = hashMapOf<String,Any>("Users/$uid/" to data)
            database.reference.updateChildren(userupdates).addOnSuccessListener {

                Toast.makeText(this,"Welcome to our App!",Toast.LENGTH_LONG).show()
                val intent = Intent(this,MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)

            }
        }

    }
}