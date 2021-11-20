package com.example.back.service

import com.example.back.dto.Schedule
import com.example.back.dto.ScheduleReq
import com.example.back.dto.ScheduleRes
import org.bson.types.ObjectId

interface ScheduleService {
    fun createShcedule(scheduleReq: ScheduleReq): ScheduleRes
    fun findScheduleByUserId(userId:String):List<ScheduleRes>
    fun updateScheduleById(schedule: Schedule,id:ObjectId):Schedule
    fun findByUserIdAndTitleLike(userId:String,title:String): List<ScheduleRes>
}