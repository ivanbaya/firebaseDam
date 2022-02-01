package com.example.firebase

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.collections.ArrayList

class llistaPlats : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var platArrayList : ArrayList<plat>
    private lateinit var platAdapter: PlatAdapter
    private lateinit var db : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_llista_plats)

        recyclerView = findViewById(R.id.recycler1)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        platArrayList = arrayListOf()

        platAdapter = PlatAdapter(platArrayList)

        recyclerView.adapter = platAdapter

        EventChangeListener()

        findViewById<Button>(R.id.Volver).setOnClickListener{ view: View? ->
            startActivity(Intent(this, menu::class.java))
            finish()
        }
    }

    private fun EventChangeListener(){
        db = FirebaseFirestore.getInstance()
        db.collection("plats").
                addSnapshotListener { value, error ->
                        if (error != null){
                            Log.e("Firestore error",error.message.toString())
                        }

                        for(dc : DocumentChange in value?.documentChanges!!){
                            if(dc.type == DocumentChange.Type.ADDED){
                                platArrayList.add(dc.document.toObject(plat::class.java))
                            }
                        }
                        platAdapter.notifyDataSetChanged()
                    }
    }
}