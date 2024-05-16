package com.example.kmm_app.domain

import com.example.kmm_app.domain.model.Note

object NoteValidation {

    fun validateNote(note: Note): ValidationResult {
        var result = ValidationResult()

        if(note.title.isBlank()) {
            result = result.copy(noteTitleError = "Title can't be empty.")
        }

        if(note.content.isBlank()) {
            result = result.copy(noteContentError = "Content can't be empty.")
        }

        return result
    }

    data class ValidationResult(
        val noteTitleError: String? = null,
        val noteContentError: String? = null
    )
}