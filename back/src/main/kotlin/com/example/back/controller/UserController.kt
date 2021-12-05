package com.example.back.controller

import com.example.back.dto.Schedule
import com.example.back.service.UserService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Api(description = "유저관리")
@RestController
@RequestMapping("api/")
class UserController(
    val userService: UserService
) {
    @ApiOperation(value = "유저 회원가입", notes = "유저 회원가입 GET API")
    @GetMapping(path = ["user"])
    fun getSchedule():String{
        return "SUCCESS"
    }
}