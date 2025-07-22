package com.example.designsystem

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.designsystem.components.CardConfiguration
import com.example.designsystem.components.CardCustom
import com.example.designsystem.components.CardData
import com.example.designsystem.components.CardSize
import com.example.designsystem.components.CardState
import com.example.designsystem.components.CardVariant
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * Demo component showcasing the Card component with state hoisting
 */
@Composable
fun CardDemo() {
    var selectedCardId by remember { mutableStateOf<String?>(null) }
    
    val sampleCards = remember {
        listOf(
            CardData(
                title = "Ha Long Bay",
                location = "Quang Ninh, Vietnam",
                rating = 4.8f,
                tag = "Popular",
                isPro = true
            ),
            CardData(
                title = "Sapa Mountains",
                location = "Lao Cai, Vietnam",
                rating = 4.6f,
                tag = "Adventure"
            ),
            CardData(
                title = "Hoi An Ancient Town",
                location = "Quang Nam, Vietnam",
                rating = 4.9f,
                isPro = true
            ),
            CardData(
                title = "Mekong Delta",
                location = "Southern Vietnam",
                rating = 4.5f
            )
        )
    }
    
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Card Component Demo",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
        )
        
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Section 1: Solid Cards
            item {
                Text(
                    text = "Solid Variant",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            
            items(sampleCards.take(2)) { cardData ->
                CardCustom(
                    data = cardData,
                    configuration = CardConfiguration(
                        size = CardSize.MEDIUM,
                        variant = CardVariant.SOLID,
                        onClick = { /* Handle click */ }
                    ),
                    state = CardState(
                        isSelected = selectedCardId == cardData.title
                    ),
                    onStateChange = { newState ->
                        if (newState.isPressed) {
                            selectedCardId = cardData.title
                        }
                    }
                )
            }
            
            // Section 2: Image Background Cards
            item {
                Text(
                    text = "Image Background Variant",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                )
            }
            
            items(sampleCards.take(2)) { cardData ->
                CardCustom(
                    data = cardData,
                    configuration = CardConfiguration(
                        size = CardSize.LARGE,
                        variant = CardVariant.IMAGE_BACKGROUND,
                        showRating = true,
                        onClick = { /* Handle click */ }
                    ),
                    state = CardState(
                        isSelected = selectedCardId == cardData.title
                    ),
                    onStateChange = { newState ->
                        if (newState.isPressed) {
                            selectedCardId = cardData.title
                        }
                    }
                )
            }
            
            // Section 3: Glassmorphism Cards
            item {
                Text(
                    text = "Glassmorphism Variant",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                )
            }
            
            items(sampleCards) { cardData ->
                CardCustom(
                    data = cardData,
                    configuration = CardConfiguration(
                        size = CardSize.SMALL,
                        variant = CardVariant.GLASSMORPHISM,
                        showTag = cardData.tag != null,
                        showProBadge = cardData.isPro,
                        showRating = true,
                        onClick = { /* Handle click */ }
                    ),
                    state = CardState(
                        isSelected = selectedCardId == cardData.title
                    ),
                    onStateChange = { newState ->
                        if (newState.isPressed) {
                            selectedCardId = cardData.title
                        }
                    }
                )
            }
        }
    }
}

/**
 * Advanced demo showing different card configurations
 */
@Composable
fun CardConfigurationDemo() {
    var currentVariant by remember { mutableStateOf(CardVariant.SOLID) }
    var currentSize by remember { mutableStateOf(CardSize.MEDIUM) }
    var showRating by remember { mutableStateOf(false) }
    var showTag by remember { mutableStateOf(false) }
    var showProBadge by remember { mutableStateOf(false) }
    
    val sampleData = remember {
        CardData(
            title = "Ha Long Bay",
            location = "Quang Ninh, Vietnam",
            rating = 4.8f,
            tag = "Popular",
            isPro = true
        )
    }
    
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Card Configuration Demo",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
        )
        
        // Configuration controls
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Variant selector
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CardVariant.values().forEach { variant ->
                    Button(
                        onClick = { currentVariant = variant },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (currentVariant == variant) 
                                MaterialTheme.colorScheme.primary 
                            else 
                                MaterialTheme.colorScheme.secondary
                        )
                    ) {
                        Text(variant.name)
                    }
                }
            }
            
            // Size selector
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CardSize.values().forEach { size ->
                    Button(
                        onClick = { currentSize = size },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (currentSize == size) 
                                MaterialTheme.colorScheme.primary 
                            else 
                                MaterialTheme.colorScheme.secondary
                        )
                    ) {
                        Text(size.name)
                    }
                }
            }
            
            // Feature toggles
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = showRating,
                        onCheckedChange = { showRating = it }
                    )
                    Text("Rating")
                }
                
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = showTag,
                        onCheckedChange = { showTag = it }
                    )
                    Text("Tag")
                }
                
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = showProBadge,
                        onCheckedChange = { showProBadge = it }
                    )
                    Text("Pro Badge")
                }
            }
        }
        
        // Card preview
        CardCustom(
            data = sampleData,
            configuration = CardConfiguration(
                size = currentSize,
                variant = currentVariant,
                showRating = showRating,
                showTag = showTag,
                showProBadge = showProBadge,
                onClick = { /* Handle click */ }
            ),
            state = CardState(),
            onStateChange = {}
        )
    }
}

@Preview()
@Composable
fun CardDemoPreview() {
    MaterialTheme {
        CardDemo()
    }
}

@Preview()
@Composable
fun CardConfigurationDemoPreview() {
    MaterialTheme {
        CardConfigurationDemo()
    }
} 