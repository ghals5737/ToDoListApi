package com.example.back.service

import com.example.back.dto.Schedule

interface ScheduleService {
    fun createShcedule(schedule: Schedule):Schedule
    fun findScheduleByUserId(userId:String):List<Schedule>
    fun updateScheduleById(schedule: Schedule):Schedule
}