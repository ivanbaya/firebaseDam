package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        findViewById<TextView>(R.id.email).text = SavedPreference.getEmail(applicationContext)

        findViewById<Button>(R.id.buttonCreateUser).setOnClickListener{ view: View? ->
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        findViewById<Button>(R.id.buttonCreate).setOnClickListener{ view: View? ->
            startActivity(Intent(this, llistaPlats::class.java))
            finish()
        }
        findViewById<Button>(R.id.buttonLista).setOnClickListener{ view: View? ->
            startActivity(Intent(this, llistaResenya::class.java))
            finish()
        }
        findViewById<Button>(R.id.buttonCerrarSession).setOnClickListener{ view: View? ->
            Firebase.auth.signOut()
            startActivity(Intent(this, GoogleSignInActivity::class.java))
            finish()
        }
    }
}