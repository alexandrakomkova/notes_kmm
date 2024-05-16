package com.example.kmm_app

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.kmm_app.di.AppModule
import com.example.kmm_app.domain.NoteDataSource
import com.example.kmm_app.presentation.note_list.NoteListScreen
import com.example.kmm_app.presentation.note_list.NoteListViewModel
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    appModule: AppModule
) {
   NoteTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor
    ) {
        Napier.base(DebugAntilog())
       Napier.d(message = "hello", tag = "android app module")

        val viewModel = getViewModel(
            key = "note-list-screen",
            factory = viewModelFactory {
                NoteListViewModel(appModule.noteDataSource)
                //NoteListViewModel()
            }
        )
        val state by viewModel.state.collectAsState()
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NoteListScreen(
                state = state,
                newNote = viewModel.newNote,
                onEvent = viewModel::onEvent
            )

        }
    }
}