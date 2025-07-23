package com.example.designsystem

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun getScreenWidth(): Int