package com.example.kmm_app.di

import com.example.kmm_app.domain.NoteDataSource

expect class AppModule {
    val noteDataSource: NoteDataSource
}