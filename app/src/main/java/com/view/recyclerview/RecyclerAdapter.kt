package com.view.recyclerview

import android.icu.text.Transliterator.Position
import android.renderscript.ScriptGroup.Binding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(var counter:Int):RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    class ViewHolder (var view:View):RecyclerView.ViewHolder(view){
//        var tvName : TextView = view.findViewById(R.id.tvName)
//        var tvNumber:TextView=view.findViewById(R.id.tvNumber)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.recycleritems,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return counter
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }
    fun addData(newData: Int) {
        counter = newData
        notifyDataSetChanged()
    }
}