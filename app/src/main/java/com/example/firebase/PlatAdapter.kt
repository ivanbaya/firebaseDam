package com.example.firebase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PlatAdapter(private val platList : ArrayList<plat>) : RecyclerView.Adapter<PlatAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlatAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_llista_plats, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PlatAdapter.MyViewHolder, position: Int) {
        val plat : plat = platList[position]
        holder.nom.text = plat.nom
        holder.descripcio.text = plat.descripcio
        holder.preu.text = plat.preu.toString()
    }

    override fun getItemCount(): Int {
        platList.size
    }

    public class MyViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val nom : TextView = itemView.findViewById<R.id.nom>()
        val descripcio : TextView = itemView.findViewById<R.id.descripcio>()
        val preu : TextView = itemView.findViewById<R.id.preu>()
    }
}