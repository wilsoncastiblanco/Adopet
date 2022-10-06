package com.example.adopet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.adopet.ui.AdoptBottomBarContent
import com.example.adopet.ui.AdoptContent
import com.example.adopet.ui.AdoptTopBar
import com.example.adopet.ui.theme.AdopetTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AdopetTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) { AdoptApp() }
            }
        }
    }
}

//Side Effect

@Composable
fun AdoptApp() {
    Scaffold(
        topBar = { AdoptTopBar() },
        bottomBar = { AdoptBottomBarContent() },
        content = { padding ->
            AdoptContent(Modifier.padding(padding))
        }
    )
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    AdopetTheme {
        AdoptApp()
    }
}