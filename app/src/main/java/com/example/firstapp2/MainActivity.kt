package com.example.firstapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.StringRequestListener
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val goto_profile_button = findViewById<Button>(R.id.main_profile_button)
        val textview = findViewById<TextView>(R.id.textview1)
        goto_profile_button.setOnClickListener {
            startActivity(Intent(this,ProfileActivity::class.java))
        }

        AndroidNetworking.initialize(applicationContext)

        AndroidNetworking.get("https://doubtconnect.in:3001/")
            .build()
            .getAsString(object: StringRequestListener{
                override fun onResponse(response: String?) {
                    Log.d("MainActivity","Response from server: $response")
                    textview.text = response
                }
                override fun onError(anError: ANError?) {
                    Log.e("MainActivity","Error: ${anError.toString()}")
                }
            })

        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("MainActivity", "Fetching FCM registration token failed", task.exception)
            }
            val token = task.result
            Log.d("MainActivity", "FCM TOKEN: $token")
        }


        val name = listOf("1")

        val recycler_view = findViewById<RecyclerView>(R.id.recyclerview)

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = MyAdapter(name)

        val signout_button = findViewById<Button>(R.id.signout_button)
        signout_button.setOnClickListener {
            AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener {
                    Toast.makeText(this,"User signed out successfully",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,LoginActivity::class.java))
                }


        }

//        val colorDao = ColorDatabase.getInstance(applicationContext).colorDao()
//
//        val new_color = Color(hex = "#FFFFFFF", name = "WHITE", _id = 1)
//
//        colorDao.insert(new_color)
//        Log.d("MianActivity", colorDao.getAll().toString())

    }
}