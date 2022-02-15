package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore

class llistaResenya : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var resenyaArrayList : ArrayList<resenya>
    private lateinit var resenyaAdapter: ResenyaAdapter
    private lateinit var db : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_llista_resenya)

        recyclerView = findViewById(R.id.recycler2)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        resenyaArrayList = arrayListOf()

        resenyaAdapter = ResenyaAdapter(resenyaArrayList, applicationContext)

        recyclerView.adapter = resenyaAdapter

        EventChangeListener()

        findViewById<Button>(R.id.Volver).setOnClickListener{ view: View? ->
            startActivity(Intent(this, menu::class.java))
            finish()
        }
    }

    private fun EventChangeListener(){
        db = FirebaseFirestore.getInstance()
        val email = SavedPreference.getEmail(applicationContext)
        if(email != null) {
            db.collection("users").document(email).collection("reviews")
                .addSnapshotListener { value, error ->
                    if (error != null) {
                        Log.e("Firestore error", error.message.toString())
                    }

                    for (dc: DocumentChange in value?.documentChanges!!) {
                        if (dc.type == DocumentChange.Type.ADDED) {
                            resenyaArrayList.add(dc.document.toObject(resenya::class.java))
                        }
                    }
                    resenyaAdapter.notifyDataSetChanged()
        }
    }
}
}