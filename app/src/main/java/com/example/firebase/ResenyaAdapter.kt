package com.example.firebase

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import java.io.File


class ResenyaAdapter(private val resenyaList : ArrayList<resenya>, context : Context) : RecyclerView.Adapter<ResenyaAdapter.MyViewHolder>() {
    val context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResenyaAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.resenya, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ResenyaAdapter.MyViewHolder, position: Int) {
        val resenya : resenya = resenyaList[position]
        holder.nom.text = resenya.nom
        holder.nota.text = resenya.nota.toString()
        holder.valoracio.text = resenya.valoracio

        var imagenNom = resenya.imagen
        if(imagenNom != "") {
            var storageRef = FirebaseStorage.getInstance().reference.child("images/$imagenNom")
            val localFile = File.createTempFile("tempImage", "jpg")

            storageRef.getFile(localFile).addOnSuccessListener {

                val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
                holder.image.setImageBitmap(bitmap)
            }.addOnFailureListener {
                print("error")
            }
        }
    }

    override fun getItemCount(): Int {
        return resenyaList.size
    }

    public class MyViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val nom : TextView = itemView.findViewById(R.id.nom)
        val nota : TextView = itemView.findViewById(R.id.nota)
        val valoracio : TextView = itemView.findViewById(R.id.valoracio)
        val image : ImageView = itemView.findViewById(R.id.imagen)
    }
}