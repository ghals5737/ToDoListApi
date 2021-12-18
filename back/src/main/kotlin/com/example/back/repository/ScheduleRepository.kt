package com.example.back.repository

import com.example.back.dto.Schedule
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ScheduleRepository:MongoRepository<Schedule?,Long?> {
    fun findByUserId(userId:String): List<Schedule>
    fun findByUserIdAndTitleLike(userId:String,title:String): List<Schedule>
    fun existsScheduleById(id: ObjectId?): Boolean
    fun findById(id: ObjectId): Schedule
}