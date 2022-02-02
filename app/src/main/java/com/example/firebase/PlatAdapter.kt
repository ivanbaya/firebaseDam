package com.example.firebase

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK




class PlatAdapter(private val platList : ArrayList<plat>, context : Context) : RecyclerView.Adapter<PlatAdapter.MyViewHolder>() {
    val context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlatAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.plat, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PlatAdapter.MyViewHolder, position: Int) {
        val plat : plat = platList[position]
        holder.nom.text = plat.nom
        holder.descripcio.text = plat.descripcio
        holder.preu.text = plat.preu.toString()

        holder.itemView.setOnClickListener{
            val myactivity = Intent(context.applicationContext, reviewPlat::class.java)
            myactivity.addFlags(FLAG_ACTIVITY_NEW_TASK)
            myactivity.putExtra("nom", plat.nom)
            myactivity.putExtra("descripcio", plat.descripcio)
            myactivity.putExtra("preu", plat.preu.toString())
            context.applicationContext.startActivity(myactivity)
        }
    }

    override fun getItemCount(): Int {
        return platList.size
    }

    public class MyViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val nom : TextView = itemView.findViewById(R.id.nom)
        val descripcio : TextView = itemView.findViewById(R.id.descripcio)
        val preu : TextView = itemView.findViewById(R.id.preu)
    }
}