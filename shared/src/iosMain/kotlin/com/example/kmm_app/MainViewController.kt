package com.example.kmm_app

import androidx.compose.material3.Text
import androidx.compose.ui.window.ComposeUIViewController
import com.example.kmm_app.di.AppModule

/*
fun MainViewController() = ComposeUIViewController {
//		val isDarkTheme =
//				UIScreen.mainScreen.traitCollection.userInterfaceStyle ==
//								UIUserInterfaceStyle.UIUserInterfaceStyleDark

    App(
        darkTheme = false,
        dynamicColor = false,
        appModule = AppModule()
    )
}*/

fun MainViewController() = ComposeUIViewController {
    App(
    darkTheme = false,
    dynamicColor = false,
    appModule = AppModule()
)


}