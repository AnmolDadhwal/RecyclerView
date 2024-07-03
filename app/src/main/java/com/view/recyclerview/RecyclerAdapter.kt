package com.view.recyclerview

import android.icu.text.CaseMap.Title
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class RecyclerAdapter(var informationList:ArrayList<Information>,var recyclerInterface: RecyclerInterface):RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    class ViewHolder (var view:View):RecyclerView.ViewHolder(view){
        var tvTitle : TextView= view.findViewById(R.id.title)
        var tvDes : TextView = view.findViewById(R.id.des)
        var btnRemove : ImageButton = view.findViewById(R.id.btnRemove)
        var btnUpdate : ImageButton = view.findViewById(R.id.btnUpdate)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.recycleritems,parent,false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return informationList.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.setText(informationList[position].title.toString())
        holder.tvDes.setText(informationList[position].description.toString())
        holder.btnRemove.setOnClickListener {
            recyclerInterface.remove(position)
        }
        holder.btnUpdate.setOnClickListener {
            recyclerInterface.update(position)
        }
    }
}