package com.example.firstapp2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(val data: List <String>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val textview = view.findViewById<TextView>(R.id.textView_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
       val holder = MyViewHolder(layout)
        return holder

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textview.text = data[position]
    }

    override fun getItemCount(): Int {
        return data.size
    }

}