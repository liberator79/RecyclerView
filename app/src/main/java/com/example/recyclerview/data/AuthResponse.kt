package com.example.recyclerview.data

data class AuthResponse(
    val success: Boolean,
    val token: String?,
    val message: String?
)
