package com.example.kmm_app.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kmm_app.domain.model.Note
import com.example.kmm_app.domain.time.DateTimeUtil
import com.example.kmm_app.ui.theme.noteContent
import com.example.kmm_app.ui.theme.noteDate
import com.example.kmm_app.ui.theme.noteTitle

@Composable
fun NoteCard(
    note: Note,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Box(
            modifier = Modifier.height(200.dp).padding(horizontal = 10.dp, vertical = 15.dp)
        ) {
            Column {
                Text(
                    text = note.title,
                    style = noteTitle,
                    modifier = Modifier.fillMaxWidth()

                )

                Text(
                    text = DateTimeUtil.formatNoteDate(note.timestamp),
                    style = noteDate,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                )

                Text(
                    text = note.content,
                    style = noteContent,
                    maxLines = 5,
                    modifier = Modifier.fillMaxWidth().padding(top = 5.dp)
                )
            }
        }
    }
}
