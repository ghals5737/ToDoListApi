package com.example.back.service

import com.example.back.dto.Schedule
import org.bson.types.ObjectId

interface ScheduleService {
    fun createShcedule(schedule: Schedule):Schedule
    fun findScheduleByUserId(userId:String):List<Schedule>
    fun updateScheduleById(schedule: Schedule,id:ObjectId):Schedule
}