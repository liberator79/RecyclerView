package com.example.recyclerview.data

data class SignInResponse(
    val token: String,
    val success : Boolean
)

data class SignInRequest(
    val email: String,
    val password: String
)

data class SignUpResponse(
    val token: String,
    val success : Boolean
)

data class SignUpRequest(
    val email: String,
    val password: String,
    val userName : String
)

data class User(
    val id: Int,
    val name: String,
    val email: String
)
