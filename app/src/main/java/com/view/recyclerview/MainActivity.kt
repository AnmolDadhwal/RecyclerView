package com.view.recyclerview

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.view.recyclerview.databinding.ActivityMainBinding
import com.view.recyclerview.databinding.CustomdialogBinding

class MainActivity : AppCompatActivity() {
    var binding:ActivityMainBinding?=null
    lateinit var linearLayoutManager:LinearLayoutManager
    var counter=1
    var recyclerAdapter=RecyclerAdapter(counter)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        recyclerAdapter= RecyclerAdapter(counter)
        linearLayoutManager = LinearLayoutManager(this)
        binding?.recyclerView?.layoutManager = linearLayoutManager
        binding?.recyclerView?.adapter = recyclerAdapter
        binding?.btnAdd?.setOnClickListener {
            counter++
            recyclerAdapter.addData(counter)
        }
    }
}