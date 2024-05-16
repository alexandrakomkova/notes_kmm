package com.example.kmm_app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

val noteTitle = TextStyle(
    fontSize = 20.sp,
    textAlign = TextAlign.Left,
    fontWeight = FontWeight.Bold
)

val noteDate = TextStyle(
    fontSize = 12.sp,
    textAlign = TextAlign.Left,
)

val noteContent = TextStyle(
    fontSize = 16.sp,
    textAlign = TextAlign.Left,
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)