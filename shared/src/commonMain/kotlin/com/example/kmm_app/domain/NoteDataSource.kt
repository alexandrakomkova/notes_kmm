package com.example.kmm_app.domain

import com.example.kmm_app.domain.model.Note
import com.example.kmm_app.domain.model.order.NoteOrder
import com.example.kmm_app.domain.model.order.OrderType
import kotlinx.coroutines.flow.Flow

interface NoteDataSource {
    fun getAllNotes(
        noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)
    ): Flow<List<Note>>
    suspend fun getNoteById(id: Long): Note?
    suspend fun insertNote(note: Note)
    suspend fun deleteNote(id: Long)
}