package com.example.kmm_app.presentation.add_edit_note

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kmm_app.domain.model.Note
import com.example.kmm_app.presentation.components.NoteTextField
import com.example.kmm_app.presentation.components.SimpleBottomSheet
import com.example.kmm_app.presentation.note_list.NoteListEvent
import com.example.kmm_app.presentation.note_list.NoteListState

@Composable
fun AddNoteSheet(
    newNote: Note?,
    state: NoteListState,
    isOpen: Boolean,
    onEvent: (NoteListEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    SimpleBottomSheet(
        visible = isOpen,
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopStart
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(80.dp))

                NoteTextField(
                    value = newNote?.title ?: "",
                    placeholder = "Enter title",
                    error = state.noteTitleError,
                    singleLine = true,
                    maxLines = 1,
                    minLines = 1,
                    onValueChanged = {
                        onEvent(NoteListEvent.OnNoteTitleChanged(it))
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(16.dp))
                NoteTextField(
                    value = newNote?.content ?: "",
                    placeholder = "Enter content",
                    error = state.noteContentError,
                    singleLine = false,
                    maxLines = 5,
                    minLines = 5,
                    onValueChanged = {
                        onEvent(NoteListEvent.OnNoteContentChanged(it))
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {
                        onEvent(NoteListEvent.SaveNote)
                    }
                ) {
                    Text(text = "Save")
                }
            }
            IconButton(
                onClick = {
                    onEvent(NoteListEvent.DismissNote)
                }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = "Close"
                )
            }
        }
    }
}