package com.view.recyclerview

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Information(
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    var title:String?="",
    var description:String?="",
){
    override fun toString(): String {
        return "$title\n $description"
    }
}

