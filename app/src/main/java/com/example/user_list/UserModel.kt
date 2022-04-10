package com.example.user_list

import java.util.*

data class UserModel (
    var id: Int = getId(),
    var username: String = "",
    var city: String = "",
    var longitude: String = "",
    var latitude: String = ""
){
    companion object
    {
        fun getId():Int{
            val random = Random()
            return random.nextInt(100)
        }

    }
}