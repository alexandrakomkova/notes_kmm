package com.example.kmm_app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.kmm_app.ui.theme.DarkColors
import com.example.kmm_app.ui.theme.LightColors
import com.example.kmm_app.ui.theme.Typography

@Composable
actual fun NoteTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if(darkTheme) DarkColors else LightColors,
        typography = Typography,
        content = content
    )
}