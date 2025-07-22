package com.example.designsystem

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import designsystem.composeapp.generated.resources.Res
import designsystem.composeapp.generated.resources.compose_multiplatform
import com.example.designsystem.components.*

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showCardDemo by remember { mutableStateOf(false) }
        var showConfigurationDemo by remember { mutableStateOf(false) }
        var showNavigationDemo by remember { mutableStateOf(false) }
        var showNavigationDocs by remember { mutableStateOf(false) }
        
        Column(
            modifier = Modifier
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Design System - Components",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )
            
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(8.dp)
            ) {
                Button(
                    onClick = { showCardDemo = !showCardDemo }
                ) {
                    Text("Card Demo")
                }
                
                Button(
                    onClick = { showConfigurationDemo = !showConfigurationDemo }
                ) {
                    Text("Config Demo")
                }
                
                Button(
                    onClick = { showNavigationDemo = !showNavigationDemo }
                ) {
                    Text("Navigation Demo")
                }
                
                Button(
                    onClick = { showNavigationDocs = !showNavigationDocs }
                ) {
                    Text("Navigation Docs")
                }
            }
            
            AnimatedVisibility(showCardDemo) {
                CardDemo()
            }
            
            AnimatedVisibility(showConfigurationDemo) {
                CardConfigurationDemo()
            }
            
            AnimatedVisibility(showNavigationDemo) {
                NavigationBarDemo()
            }
            
            AnimatedVisibility(showNavigationDocs) {
                NavigationBarDocumentation()
            }
            
            // Show original content if no demo is active
            if (!showCardDemo && !showConfigurationDemo && !showNavigationDemo && !showNavigationDocs) {
                AnimatedVisibility(true) {
                    val greeting = remember { Greeting().greet() }
                    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(painterResource(Res.drawable.compose_multiplatform), null)
                        Text("Compose: $greeting")
                    }
                }
            }
        }
    }
}



