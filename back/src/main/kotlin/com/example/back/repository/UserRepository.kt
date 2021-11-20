package com.example.back.repository

import com.example.back.dto.Schedule
import com.example.back.dto.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: MongoRepository<User?, Long?> {
    fun existsByUserId(userId:String):Boolean
}