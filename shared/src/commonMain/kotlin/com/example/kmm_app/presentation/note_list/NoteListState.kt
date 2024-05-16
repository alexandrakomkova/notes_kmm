package com.example.kmm_app.presentation.note_list

import com.example.kmm_app.domain.model.Note
import com.example.kmm_app.domain.model.order.NoteOrder
import com.example.kmm_app.domain.model.order.OrderType

data class NoteListState(
    val notes: List<Note> = emptyList(),
    val selectedNote: Note? = null,
    val isAddNoteSheetOpen: Boolean = false,
    val isSelectedNoteSheetOpen: Boolean = false,
    val noteTitleError: String? = null,
    val noteContentError: String? = null,

    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Ascending),
    val isOrderSectionVisible: Boolean = false
)