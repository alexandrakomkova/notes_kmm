package com.example.kmm_app.domain.model

import kotlinx.datetime.LocalDateTime

data class Note(
    val id: Long?,
    val title: String,
    val content: String,
    val timestamp: LocalDateTime,
)