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
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val db = Firebase.firestore

        findViewById<Button>(R.id.button2).setOnClickListener{ view: View? ->
            val user = hashMapOf(
                "nom" to findViewById<EditText>(R.id.editTextTextPersonName).text.toString(),
                "telefon" to findViewById<EditText>(R.id.editTextPhone).text.toString()
            )
            db.collection("users").document(findViewById<EditText>(R.id.editTextTextEmailAddress).text.toString())
                .set(user)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
            startActivity(Intent(this, menu::class.java))
            finish()
        }
        findViewById<Button>(R.id.buttonBorrar).setOnClickListener { view: View? ->
            db.collection("users").document(findViewById<EditText>(R.id.editTextTextEmailAddress).text.toString())
                .delete()
                .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
                .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }
            startActivity(Intent(this, menu::class.java))
            finish()
        }
        findViewById<Button>(R.id.buttonVolver).setOnClickListener{ view: View? ->
            startActivity(Intent(this, menu::class.java))
            finish()
        }
    }
}