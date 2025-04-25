package com.budget.project

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform