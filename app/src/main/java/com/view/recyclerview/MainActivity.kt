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
    var todoDatabase: TodoDatabase?=null
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
        todoDatabase=TodoDatabase.getInstance(this)
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
                    todoDatabase?.todoInterface()?.insertTodo(Information(title = dialogBinding.etTitle.text.toString(), description = dialogBinding.etDesc.text.toString()))
                    getData()
                    recyclerAdapter.notifyDataSetChanged()
                    dialog.dismiss()
                }
            }
            dialog.show()
        }
        getData()
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
                todoDatabase?.todoInterface()?.deleteTodoEntity(informationList[position])
                recyclerAdapter.notifyDataSetChanged()
                getData()
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
                todoDatabase?.todoInterface()?.updateTodoEntity(Information(
                    id = informationList[position].id,
                    title = dialogBinding.etTitle.text.toString(),
                    description = dialogBinding.etDesc.text.toString()))
                recyclerAdapter.notifyDataSetChanged()
                getData()
                dialog.dismiss()
            }
        }
        dialog.show()
    }
    fun getData(){
        informationList.clear()
        informationList.addAll(todoDatabase?.todoInterface()?.getList()?: arrayListOf() )
        recyclerAdapter.notifyDataSetChanged()
    }
}