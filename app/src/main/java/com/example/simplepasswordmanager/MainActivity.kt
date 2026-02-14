package com.example.simplepasswordmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.simplepasswordmanager.ui.PasswordListScreen
import com.example.simplepasswordmanager.ui.theme.SimplePasswordManagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimplePasswordManagerTheme {
                PasswordListScreen()
            }
        }
    }
}
