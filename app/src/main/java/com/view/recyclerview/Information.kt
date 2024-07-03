package com.view.recyclerview

data class Information(
    var title:String?="",
    var description:String?="",
){
    override fun toString(): String {
        return "$title\n $description"
    }
}

