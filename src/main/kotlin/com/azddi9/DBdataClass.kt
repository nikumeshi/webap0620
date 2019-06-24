package com.azddi9

import com.google.gson.Gson

data class DBdataClass(val id:String, val name: String, val pw: String){
    fun toJson() = Gson().toJson(this)
}