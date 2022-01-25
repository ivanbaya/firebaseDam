package com.example.firebase

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.firebase.databinding.ActivityLlistaPlatsBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.firebase.ui.firestore.FirestoreRecyclerOptions





class llistaPlats : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_llista_plats)

        val query: Query = FirebaseFirestore.getInstance()
            .collection("plats")
            .orderBy("timestamp") //
            .limit(50)

        val options: FirestoreRecyclerOptions<plat> = FirestoreRecyclerOptions.Builder<plat>()
            .setQuery(query, plat::class.java)
            .build()
    }
}