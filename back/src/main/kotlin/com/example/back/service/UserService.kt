package com.example.back.service

import com.example.back.dto.User

interface UserService {
    fun createUser(user:User):User
    fun existByUserId(userId:String):Boolean
}