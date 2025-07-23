package com.example.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

import com.example.designsystem.components.*
import com.example.designsystem.material3.AppTheme

@Composable
@Preview
fun App() {
    AppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(top = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            CardMenuCustom (
                modifier = Modifier.fillMaxWidth().height(100.dp).padding(8.dp),
                backgroundColor = MaterialTheme.colorScheme.onBackground,
                borderColor = Color.Gray,
                borderWidth = 1.dp,
                shape = RoundedCornerShape(36.dp),
                lineColor = MaterialTheme.colorScheme.surface,
                elevation = 4.dp
            ){
                MenuContentLayout1(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            CardMenuCustom (
                modifier = Modifier.fillMaxWidth().height(100.dp).padding(8.dp),
                backgroundColor = Color(0xFF1F1D22),
                borderColor = Color.White,
                borderWidth = 1.dp,
                shape = RoundedCornerShape(36.dp),
                lineColor = Color.White,
                elevation = 4.dp
            ){
                MenuContentLayout2(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            CardMenuCustom (
                modifier = Modifier.fillMaxWidth().height(100.dp).padding(8.dp),
                backgroundColor = MaterialTheme.colorScheme.onBackground,
                borderColor = Color.Gray,
                borderWidth = 1.dp,
                shape = RoundedCornerShape(36.dp),
                lineColor = MaterialTheme.colorScheme.surface,
                elevation = 4.dp
            ){
                MenuContentLayout3(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            CardMenuCustom (
                modifier = Modifier.fillMaxWidth().height(100.dp).padding(8.dp),
                backgroundColor = Color.White,
                borderColor = Color.Gray,
                borderWidth = 2.dp,
                shape = RoundedCornerShape(36.dp),
                lineColor = Color.Black,
                elevation = 4.dp
            ){
                MenuContentLayout3(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 8.dp)
                )
            }
        }

    }
}



