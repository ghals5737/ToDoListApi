package com.example.back.dto

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "user")
data class User(
    @Id
    var id: ObjectId? = ObjectId.get(),
    var userId: String? =null,
    var regDt: Date?=null,
)
