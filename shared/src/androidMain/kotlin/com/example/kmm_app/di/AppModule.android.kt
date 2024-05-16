package com.example.kmm_app.di

import android.content.Context
import com.example.kmm_app.data.DatabaseDriverFactory
import com.example.kmm_app.data.SqlDelightNoteDataSource
import com.example.kmm_app.database.NoteDatabase
import com.example.kmm_app.domain.NoteDataSource

actual class AppModule(
    private val context: Context
) {

    actual val noteDataSource: NoteDataSource by lazy {
        SqlDelightNoteDataSource(
            db = NoteDatabase(
                driver = DatabaseDriverFactory(context).create(),
            ),
        )
    }
}