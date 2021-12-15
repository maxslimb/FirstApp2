package com.example.firstapp2

import android.util.Log
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService: FirebaseMessagingService() {


    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        Log.d("MyFirebaseMessagingService","Got new notification")


        if(p0.data.isNotEmpty()){
            Log.d("MyFirebaseMessagingService","data: ${p0.data} ")

        }
    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.d("MyFirebaseMessagingService","New Token: $p0")

    }
}