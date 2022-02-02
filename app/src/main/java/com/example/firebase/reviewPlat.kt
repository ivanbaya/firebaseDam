package com.example.firebase

import android.content.ContentValues
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

class reviewPlat : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_plat)

        val db = Firebase.firestore

        findViewById<TextView>(R.id.platNom).text = intent.getStringExtra("nom")
        findViewById<TextView>(R.id.platDescripcio).text = intent.getStringExtra("descripcio")
        findViewById<TextView>(R.id.platPreu).text = intent.getStringExtra("preu")

        findViewById<Button>(R.id.buttonVolverLista).setOnClickListener{ view: View? ->
            startActivity(Intent(this, llistaPlats::class.java))
            finish()
        }

        findViewById<Button>(R.id.buttonEnviarReview).setOnClickListener{ view: View? ->
            val review = hashMapOf(
                "nota" to findViewById<EditText>(R.id.editTextNumber).text.toString(),
                "valoracio" to findViewById<EditText>(R.id.editReview).text.toString()
            )
            val email = SavedPreference.getEmail(applicationContext)
            if (email != null) {
                db.collection("users").document(email).collection("reviews").document(findViewById<TextView>(R.id.platNom).text.toString())
                    .set(review)
                    .addOnSuccessListener { documentReference ->
                        Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${documentReference}")
                    }
                    .addOnFailureListener { e ->
                        Log.w(ContentValues.TAG, "Error adding document", e)
                    }
            }
            startActivity(Intent(this, llistaPlats::class.java))
            finish()
        }
    }
}