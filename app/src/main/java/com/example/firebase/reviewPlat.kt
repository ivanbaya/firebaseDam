package com.example.firebase

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference

import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import java.util.*


class reviewPlat : AppCompatActivity() {

    private val GALLERY_REQUEST_CODE = 22
    var fileName = ""

    @SuppressLint("CutPasteId")
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

        findViewById<Button>(R.id.buttonUploadImage).setOnClickListener{ view: View? ->
            selectImageFromGallery()
        }

        findViewById<Button>(R.id.buttonEnviarReview).setOnClickListener{ view: View? ->
            val review = hashMapOf(
                "nom" to findViewById<TextView>(R.id.platNom).text.toString(),
                "nota" to findViewById<EditText>(R.id.editTextNumber).text.toString().toInt(),
                "valoracio" to findViewById<EditText>(R.id.editReview).text.toString(),
                "imagen" to fileName
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
    private fun selectImageFromGallery() {

        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent,GALLERY_REQUEST_CODE)
    }
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {

        super.onActivityResult(
            requestCode,
            resultCode,
            data
        )

        if (requestCode == GALLERY_REQUEST_CODE
            && resultCode == Activity.RESULT_OK
            && data != null
            && data.data != null
        ) {

            // Get the Uri of data
            var file_uri = data.data
            if (file_uri != null) {
                uploadImageToFirebase(file_uri)
            }
        }
    }
    private fun uploadImageToFirebase(fileUri: Uri) {
        fileName = UUID.randomUUID().toString() +".jpg"

        val database = FirebaseStorage.getInstance()
        val refStorage = database.reference.child("images/$fileName")

        refStorage.putFile(fileUri)
            .addOnSuccessListener{
                print("funciona")


            }.addOnFailureListener(OnFailureListener { e ->
                print(e.message)
            })
    }
}