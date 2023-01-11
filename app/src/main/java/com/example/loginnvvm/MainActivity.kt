package com.example.loginnvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.loginnvvm.ui.Login.screen.LoginScreen
import com.example.loginnvvm.ui.Login.screen.LoginViewModel
import com.example.loginnvvm.ui.Login.screen.MenuLateral
import com.example.loginnvvm.ui.theme.LoginNVVMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginNVVMTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MenuLateral()
                    LoginScreen(LoginViewModel())
                }
            }
        }
    }
}



