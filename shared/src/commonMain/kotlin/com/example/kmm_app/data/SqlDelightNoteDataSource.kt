package com.example.kmm_app.data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.example.kmm_app.database.NoteDatabase
import com.example.kmm_app.domain.NoteDataSource
import com.example.kmm_app.domain.model.Note
import com.example.kmm_app.domain.model.order.NoteOrder
import com.example.kmm_app.domain.time.DateTimeUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.supervisorScope

class SqlDelightNoteDataSource(
    db: NoteDatabase
): NoteDataSource {

    private val queries = db.noteQueries

    override fun getAllNotes(
        noteOrder: NoteOrder
    ): Flow<List<Note>> {

        return queries
            .getAllNotes()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { noteEntities ->
                supervisorScope {
                    noteEntities
                        .map {
                            async { it.toNote() }
                        }
                        .map { it.await() }

                }
            }
    }

    override suspend fun getNoteById(id: Long): Note? {
        return queries
            .getNoteById(id)
            .executeAsOneOrNull()
            ?.toNote()
    }

    override suspend fun insertNote(note: Note) {
        queries.insertNote(
            id = note.id,
            title = note.title,
            content = note.content,
            timestamp = DateTimeUtil.toEpochMillis(DateTimeUtil.now())
        )
    }

    override suspend fun deleteNote(id: Long) {
        queries.deleteNoteById(id)
    }
}

