package com.example.back.dto

import lombok.Data
import lombok.NoArgsConstructor
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "schedule")
@Data
@NoArgsConstructor
data class Schedule(
    @Id
    var id: ObjectId? = ObjectId.get(),
    var userId: String? =null,
    var title: String? =null,
    var des: String? =null,
    var regDt: Date?=null,
    var regUser: String? =null
)

