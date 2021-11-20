package com.example.back.service

import com.example.back.dto.User
import com.example.back.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    val userRepository: UserRepository
):UserService {
    override fun createUser(user: User): User {
        return userRepository.save(user);
    }

    override fun existByUserId(userId: String): Boolean {
        return userRepository.existsByUserId(userId)
    }
}