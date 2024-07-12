package com.view.recyclerview

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ToDoInterface {
    @Insert
    fun insertTodo(todoEntity:Information)
    @Query("SELECT * FROM Information")
    fun getList():List<Information>
    @Update
    fun updateTodoEntity(todoEntity: Information)
    @Delete
    fun deleteTodoEntity(todoEntity: Information)
}