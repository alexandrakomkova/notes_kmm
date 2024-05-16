package com.example.kmm_app.presentation.note_list

import com.example.kmm_app.domain.model.Note
import com.example.kmm_app.domain.model.order.NoteOrder

sealed interface NoteListEvent {
    data object OnAddNewNoteClick: NoteListEvent
    data object DismissNote: NoteListEvent
    data class OnNoteTitleChanged(val value: String): NoteListEvent
    data class OnNoteContentChanged(val value: String): NoteListEvent
    data object SaveNote: NoteListEvent

    data class SelectNote(val note: Note): NoteListEvent
    data class EditNote(val note: Note): NoteListEvent
    data object DeleteNote : NoteListEvent

    data class Order(val noteOrder: NoteOrder): NoteListEvent
    data object ToggleOrderSection: NoteListEvent
}