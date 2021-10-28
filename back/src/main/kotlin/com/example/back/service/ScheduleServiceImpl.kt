package com.example.back.service

import com.example.back.dto.Schedule
import com.example.back.repository.ScheduleRepository
import org.springframework.stereotype.Service

@Service
class ScheduleServiceImpl(
    val shecduleRepository:ScheduleRepository
) : ScheduleService
{
    override fun createShcedule(schedule: Schedule): Schedule {
        return shecduleRepository.save(schedule);
    }
}