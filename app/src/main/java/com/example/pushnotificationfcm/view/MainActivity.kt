package com.example.pushnotificationfcm.view

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.pushnotificationfcm.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // If you want Sending a Test Message background apps(inactive/killed) or mobile POWER OFF
        // FCM Automatically Displays the Notification using tokens
        // No Code Execution in onMessageReceived() for notification payload
        // Limited control (system controls look & behavior)
        // Quick alerts like promotions, reminders


        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("TokenDetails: ", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            Log.d("Token: ", token)
            Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
        })
    }
}