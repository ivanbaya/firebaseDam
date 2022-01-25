package com.example.firebase

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val db = Firebase.firestore

        findViewById<Button>(R.id.button2).setOnClickListener{ view: View? ->
            val user = hashMapOf(
                "email" to findViewById<EditText>(R.id.editTextTextEmailAddress).text.toString(),
                "nom" to findViewById<EditText>(R.id.editTextTextPersonName).text.toString(),
                "telefon" to findViewById<EditText>(R.id.editTextPhone).text.toString()
            )
            db.collection("users")
                .add(user)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
            startActivity(Intent(this, menu::class.java))
            finish()
        }
    }
}