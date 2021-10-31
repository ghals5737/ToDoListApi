package com.example.back.service

import com.example.back.dto.Schedule
import com.example.back.repository.ScheduleRepository
import org.springframework.stereotype.Service

@Service
class ScheduleServiceImpl(
    val scheduleRepository:ScheduleRepository
) : ScheduleService
{
    override fun createShcedule(schedule: Schedule): Schedule {
        return scheduleRepository.save(schedule);
    }

    override fun findScheduleByUserId(userId:String): List<Schedule> {
        return scheduleRepository.findByUserId(userId)
    }

    override fun updateScheduleById(schedule: Schedule): Schedule {
        if(scheduleRepository.existsScheduleById(schedule.id)){
            return scheduleRepository.save(schedule)
        }else{
            return Schedule()
        }
    }
}