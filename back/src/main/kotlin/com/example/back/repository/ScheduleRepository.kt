package com.example.back.repository

import com.example.back.dto.Schedule
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ScheduleRepository:MongoRepository<Schedule?,Long?> {
}