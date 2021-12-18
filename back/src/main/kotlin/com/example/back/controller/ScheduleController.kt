package com.example.back.controller

import com.example.back.dto.Schedule
import com.example.back.dto.ScheduleReq
import com.example.back.dto.ScheduleRes
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
    @GetMapping(path = ["schedule"])
    fun getSchedule(@RequestAttribute email:String ):List<ScheduleRes>{
        println(email)
        return scheduleService.findScheduleByUserId(email)
    }

    @ApiOperation(value = "유저 일정확인(제목)", notes = "유저 일정 확인 GET API")
    @GetMapping(path = ["schedule/{title}"])
    fun getSchedule(@RequestAttribute email:String,@PathVariable title:String):List<ScheduleRes>{
        return scheduleService.findByUserIdAndTitleLike(email,title)
    }

    @ApiOperation(value = "유저 일정 생성", notes = "유저 일정 생성 POST API")
    @PostMapping(path = ["schedule"])
    fun insertSchedule(@RequestBody schedule: ScheduleReq,@RequestAttribute email:String): ScheduleRes {
        schedule.userId=email
        return scheduleService.createSchedule(schedule)
    }

    @PutMapping(path=["schedule/{id}"])
    fun updateSchedule(@RequestBody schedule: ScheduleReq,@PathVariable id:String):ScheduleRes{
        return scheduleService.updateScheduleById(schedule,ObjectId(id))
    }

    @DeleteMapping(path=["schedule/{id}"])
    fun deleteSchedule(@PathVariable id:String):ScheduleRes{
        return scheduleService.deleteScheduleById(ObjectId(id))
    }
}