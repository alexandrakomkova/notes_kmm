package com.example.kmm_app

import androidx.compose.runtime.Composable

@Composable
expect fun NoteTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
)