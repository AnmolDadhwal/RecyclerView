package com.view.recyclerview

import android.app.Dialog
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.view.recyclerview.databinding.ActivityMainBinding
import com.view.recyclerview.databinding.CustomdialogBinding

class MainActivity : AppCompatActivity(), RecyclerInterface {
    var binding:ActivityMainBinding?=null
    var informationList= arrayListOf<Information>()
    var recyclerAdapter=RecyclerAdapter(informationList,this)
    lateinit var linearLayoutManager:LinearLayoutManager
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
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding?.recyclerView?.layoutManager = linearLayoutManager
        binding?.recyclerView?.adapter = recyclerAdapter
        binding?.btnAdd?.setOnClickListener {
            var dialogBinding= CustomdialogBinding.inflate(layoutInflater)
            var dialog= Dialog(this).apply{
                setContentView(dialogBinding.root)
                show()
            }
            dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            dialogBinding.btnSubmit.setOnClickListener {
                if (dialogBinding.etTitle.text?.toString()?.trim().isNullOrEmpty()){
                    dialogBinding.etTitle.error="Enter Title"
                }else if (dialogBinding.etDesc.text?.toString()?.trim().isNullOrEmpty()){
                    dialogBinding.etDesc.error="Enter Description"
                }else{
                    informationList.add(Information(dialogBinding.etTitle.text.toString(),dialogBinding.etDesc.text.toString()))
                    recyclerAdapter.notifyDataSetChanged()
                    dialog.dismiss()
                }
            }
            dialog.show()
        }
    }
    override fun remove(position: Int) {
        AlertDialog.Builder(this@MainActivity).apply {
            setTitle("DELETE")
            setMessage("Are you really want to delete the information")
            setCancelable(false)
            setPositiveButton("No") { _, _ ->
                setCancelable(true)
            }
            setNegativeButton("Yes") { _, _ ->
                informationList.removeAt(position)
                recyclerAdapter.notifyDataSetChanged()
            }
            show()
        }
    }
    override fun update(position: Int) {
        var dialogBinding= CustomdialogBinding.inflate(layoutInflater)
        var dialog= Dialog(this).apply{
            setContentView(dialogBinding.root)
            show()
        }
        val update="Update"
        dialogBinding.btnSubmit.setText(update)
        val upTitle:String=informationList[position].title.toString()
        dialogBinding.etTitle.setText(upTitle)
        val upDesc:String=informationList[position].description.toString()
        dialogBinding.etDesc.setText(upDesc)
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialogBinding.btnSubmit.setOnClickListener {
            if (dialogBinding.etTitle.text?.toString()?.trim().isNullOrEmpty()){
                dialogBinding.etTitle.error="Enter Title"
            }else if (dialogBinding.etDesc.text?.toString()?.trim().isNullOrEmpty()){
                dialogBinding.etDesc.error="Enter Description"
            }else{
                informationList.set(position,Information(dialogBinding.etTitle.text?.toString(),dialogBinding.etDesc.text?.toString()?.trim()))
                recyclerAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }
        }
        dialog.show()
    }
}