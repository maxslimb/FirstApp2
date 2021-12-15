package com.example.firstapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val imageview = findViewById<ImageView>(R.id.imageView2)

        Glide.with(this)
            .load("https://firebasestorage.googleapis.com/v0/b/firstapp2-e4d09.appspot.com/o/df.png?alt=media&token=f2be0868-f4da-409b-801a-9ba88abf746f")
            .centerCrop()
            .into(imageview)

        val name_edt = findViewById<EditText>(R.id.editText)
        val email_edt = findViewById<EditText>(R.id.editText2)
        val sumbit_button = findViewById<Button>(R.id.profile_submit_button)
        val pbar = findViewById<ProgressBar>(R.id.profile_progressbar)



        val databaseref = Firebase.database.getReference("Users/${FirebaseAuth.getInstance().uid}")

        databaseref.addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                    name_edt.setText(snapshot.child("name").value.toString())
                    email_edt.setText(snapshot.child("email").value.toString())
                    pbar.visibility = View.INVISIBLE
            }
            override fun onCancelled(error: DatabaseError) {
                Log.e("ProfileActivity","Firebase database error $error")
            }
        })

        sumbit_button.setOnClickListener {
            updateuserinfo(name_edt.text.toString(),email_edt.text.toString())
        }
    }

    private fun updateuserinfo(name: String, email: String?) {

        val database = Firebase.database.reference
        val user = User(name,email)
        val map = user.toMap()
        val usermap = mapOf<String,Any>(
            "Users/${FirebaseAuth.getInstance().uid}" to map
        )
        database.updateChildren(usermap).addOnSuccessListener {
            Toast.makeText(this,"User data Updated!",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}