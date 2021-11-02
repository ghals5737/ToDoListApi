package com.example.back.controller

import com.example.back.dto.Schedule
import com.example.back.service.ScheduleService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.bson.types.ObjectId
import org.springframework.web.bind.annotation.*

@Api(description = "일정관리")
@RestController
@RequestMapping("api/")
class ScheduleController (
    val scheduleService:ScheduleService
)
{
    @ApiOperation(value = "유저 일정확인", notes = "유저 일정 확인 GET API")
    @GetMapping(path = ["schedule/{userid}"])
    fun getSchedule(@PathVariable userid:String):List<Schedule>{
        return scheduleService.findScheduleByUserId(userid)
    }

    @ApiOperation(value = "유저 일정 생성", notes = "유저 일정 생성 POST API")
    @PostMapping(path = ["schedule"])
    fun insertSchedule(@RequestBody schedule: Schedule):Schedule{
        return scheduleService.createShcedule(schedule)
    }

    @PutMapping(path=["schedule/{id}"])
    fun updateSchedule(@RequestBody schedule: Schedule,@PathVariable id:String):Schedule{
        return scheduleService.updateScheduleById(schedule,ObjectId(id))
    }

    @DeleteMapping(path=["schedule/{id}"])
    fun deleteSchedule(@RequestBody schedule: Schedule,@PathVariable id:String):Schedule{
        return scheduleService.updateScheduleById(schedule,ObjectId(id))
    }
}