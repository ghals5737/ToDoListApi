package com.example.back.controller

import com.example.back.dto.Schedule
import com.example.back.service.ScheduleService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/")
class ScheduleController (
    val scheduleService:ScheduleService
)
{
    @PostMapping(path = ["schedule"])
    fun insertSchedule(@RequestBody schedule: Schedule):Schedule{
        return scheduleService.createShcedule(schedule)
    }
}