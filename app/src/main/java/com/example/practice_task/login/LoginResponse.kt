package com.example.practice_task

data class LoginResponse(
    val Currentdate: String,
    val Data: List<Data>,
    val Data1: List<Any>,
    val Message: String,
    val Status: Int,
    val Success: Boolean,
    val Userid: Int
)