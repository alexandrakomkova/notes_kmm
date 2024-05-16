package com.example.kmm_app.di

import com.example.kmm_app.data.DatabaseDriverFactory
import com.example.kmm_app.data.SqlDelightNoteDataSource
import com.example.kmm_app.database.NoteDatabase
import com.example.kmm_app.domain.NoteDataSource

actual class AppModule {

    actual val noteDataSource: NoteDataSource by lazy {
        SqlDelightNoteDataSource(
            db = NoteDatabase(
                driver = DatabaseDriverFactory().create()
            )
        )
    }
}