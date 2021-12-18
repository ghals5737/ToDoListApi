package com.example.back.service

import com.example.back.dto.ScheduleReq
import com.example.back.dto.ScheduleRes
import org.bson.types.ObjectId

interface ScheduleService {
    fun createSchedule(scheduleReq: ScheduleReq): ScheduleRes
    fun findScheduleByUserId(userId:String):List<ScheduleRes>
    fun updateScheduleById(scheduleReq: ScheduleReq,id:ObjectId):ScheduleRes
    fun findByUserIdAndTitleLike(userId:String,title:String): List<ScheduleRes>
    fun deleteScheduleById(id:ObjectId):ScheduleRes
}