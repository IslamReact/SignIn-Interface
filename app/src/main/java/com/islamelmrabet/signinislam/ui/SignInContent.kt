package com.islamelmrabet.signinislam.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.islamelmrabet.signinislam.ui.theme.SignInIslamTheme

@Composable
fun signInContent(content: @Composable () -> Unit) {
    SignInIslamTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}