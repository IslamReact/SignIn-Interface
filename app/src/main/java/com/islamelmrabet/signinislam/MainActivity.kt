package com.islamelmrabet.signinislam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.islamelmrabet.signinislam.ui.screens.content
import com.islamelmrabet.signinislam.ui.signInContent
import com.islamelmrabet.signinislam.ui.theme.SignInIslamTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            signInContent{
                content()
            }
        }
    }
}