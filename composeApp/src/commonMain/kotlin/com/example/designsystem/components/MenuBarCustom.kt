package com.example.designsystem.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import designsystem.composeapp.generated.resources.Res
import designsystem.composeapp.generated.resources.menu_add
import designsystem.composeapp.generated.resources.menu_favorite
import designsystem.composeapp.generated.resources.menu_home
import designsystem.composeapp.generated.resources.menu_search
import designsystem.composeapp.generated.resources.menu_setting
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.vectorResource
import org.jetbrains.compose.ui.tooling.preview.Preview

// 1. STATE HOISTING - Navigation state is hoisted to parent component
data class NavigationState(
    val selectedTab: NavigationTab = NavigationTab.Home,
    val showLabels: Boolean = false,
    val style: NavigationStyle = NavigationStyle.Default
)

enum class NavigationTab {
    Home, Search, Add, Favorite, Account
}

enum class NavigationStyle {
    Default, // Light theme with glassmorphism
    Dark     // Dark theme with glassmorphism
}

// 2. MODIFIER & LAYOUT PATTERNS - Reusable modifier patterns
object NavigationModifiers {
    val glassmorphismLight = Modifier
        .background(
            color = Color.White.copy(alpha = 0.01f),
            shape = RoundedCornerShape(1000.dp)
        )
        .shadow(
            elevation = 4.dp,
            shape = RoundedCornerShape(1000.dp),
            ambientColor = Color.White.copy(alpha = 0.3f),
            spotColor = Color.White.copy(alpha = 0.1f)
        )
    
    val glassmorphismDark = Modifier
        .background(
            color = Color.Black.copy(alpha = 0.3f),
            shape = RoundedCornerShape(1000.dp)
        )
        .shadow(
            elevation = 4.dp,
            shape = RoundedCornerShape(1000.dp)
        )
    
    val iconContainer = Modifier
        .size(48.dp)
        .clip(CircleShape)
    
    val activeIconContainer = Modifier
        .size(48.dp)
        .clip(CircleShape)
        .background(
            brush = Brush.linearGradient(
                colors = listOf(
                    Color(0xFFAFA2EF),
                    Color(0xFF6F58E2),
                    Color(0xFFC34EFE)
                )
            )
        )
        .shadow(
            elevation = 40.dp,
            shape = CircleShape,
            ambientColor = Color(0xFF1C1C1E).copy(alpha = 0.12f)
        )
}

// 3. COMPONENT VARIANTS & CONFIGURATION - Multiple variants with different configurations
@Composable
fun NavigationBar(
    navigationState: NavigationState,
    onNavigationStateChange: (NavigationState) -> Unit,
    modifier: Modifier = Modifier
) {
    val baseModifier = when (navigationState.style) {
        NavigationStyle.Default -> NavigationModifiers.glassmorphismLight
        NavigationStyle.Dark -> NavigationModifiers.glassmorphismDark
    }
    
    Column(
        modifier = modifier
            .then(baseModifier)
            .padding(horizontal = 27.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Navigation Icons Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavigationTab.values().forEach { tab ->
                NavigationIcon(
                    tab = tab,
                    isSelected = navigationState.selectedTab == tab,
                    showLabel = navigationState.showLabels,
                    style = navigationState.style,
                    onClick = {
                        onNavigationStateChange(navigationState.copy(selectedTab = tab))
                    }
                )
            }
        }
        
        // Active Indicator Line
        ActiveIndicator(
            selectedTab = navigationState.selectedTab,
            style = navigationState.style
        )
    }
}

@Composable
private fun NavigationIcon(
    tab: NavigationTab,
    isSelected: Boolean,
    showLabel: Boolean,
    style: NavigationStyle,
    onClick: () -> Unit
) {
    val iconColor = when {
        isSelected -> when (style) {
            NavigationStyle.Default -> Color(0xFFFEF7FF)
            NavigationStyle.Dark -> Color(0xFFFEF7FF)
        }
        else -> when (style) {
            NavigationStyle.Default -> Color(0xFF625B71)
            NavigationStyle.Dark -> Color(0xFFE8DEF8)
        }
    }
    
    val containerModifier = if (isSelected) {
        NavigationModifiers.activeIconContainer
    } else {
        NavigationModifiers.iconContainer
    }
    
    if (showLabel) {
        // Variant with labels
        Column(
            modifier = containerModifier
                .clickable { onClick() }
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                imageVector = vectorResource(tab.icon),
                contentDescription = tab.title,
                tint = iconColor,
                modifier = Modifier.size(24.dp)
            )
            
            if (isSelected) {
                Text(
                    text = tab.title,
                    color = Color(0xFFFEF7FF),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.1.sp
                )
            }
        }
    } else {
        // Icon-only variant
        Box(
            modifier = containerModifier
                .clickable { onClick() }
                .padding(12.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = vectorResource(tab.icon),
                contentDescription = tab.title,
                tint = iconColor,
                modifier = Modifier.size(24.dp)
            )
            
            // Active dot indicator
            if (isSelected) {
                Box(
                    modifier = Modifier
                        .size(4.dp)
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFFAFA2EF),
                                    Color(0xFF6F58E2),
                                    Color(0xFFC34EFE)
                                )
                            ),
                            shape = CircleShape
                        )
                        .align(Alignment.BottomCenter)
                        .offset(y = 8.dp)
                )
            }
        }
    }
}

@Composable
private fun ActiveIndicator(
    selectedTab: NavigationTab,
    style: NavigationStyle
) {
    val lineColor = when (style) {
        NavigationStyle.Default -> Color(0xFF49454F)
        NavigationStyle.Dark -> Color(0xFF49454F)
    }
    
    val animatedOffset by animateFloatAsState(
        targetValue = selectedTab.ordinal * 0.25f,
        animationSpec = tween(durationMillis = 300),
        label = "indicator_offset"
    )
    
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(4.dp)
    ) {
        // Background line
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .background(
                    color = lineColor,
                    shape = RoundedCornerShape(4.dp)
                )
        )
        
        // Animated active indicator
        Box(
            modifier = Modifier
                .width(80.dp)
                .height(4.dp)
                .background(
                    color = Color(0xFFFEF7FF),
                    shape = RoundedCornerShape(4.dp)
                )
                .offset(
                    x = (animatedOffset * 374.dp - 40.dp).coerceIn(0.dp, 294.dp)
                )
        )
    }
}

// Extension properties for tab configuration
val NavigationTab.icon: DrawableResource
    get() = when (this) {
        NavigationTab.Home -> Res.drawable.menu_home
        NavigationTab.Search -> Res.drawable.menu_search
        NavigationTab.Add -> Res.drawable.menu_add
        NavigationTab.Favorite -> Res.drawable.menu_favorite
        NavigationTab.Account -> Res.drawable.menu_setting
    }

val NavigationTab.title: String
    get() = when (this) {
        NavigationTab.Home -> "Home"
        NavigationTab.Search -> "Search"
        NavigationTab.Add -> "Add"
        NavigationTab.Favorite -> "Favorite"
        NavigationTab.Account -> "Account"
    }

// Demo component showing different variants
@Composable
@Preview
fun NavigationBarDemo() {
    var navigationState by remember {
        mutableStateOf(NavigationState())
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Navigation Bar Variants",
            style = MaterialTheme.typography.headlineMedium
        )
        
        // Controls
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {
                    navigationState = navigationState.copy(
                        style = if (navigationState.style == NavigationStyle.Default) 
                            NavigationStyle.Dark else NavigationStyle.Default
                    )
                }
            ) {
                Text("Toggle Style")
            }
            
            Button(
                onClick = {
                    navigationState = navigationState.copy(
                        showLabels = !navigationState.showLabels
                    )
                }
            ) {
                Text("Toggle Labels")
            }
        }
        
        // Navigation Bar
        NavigationBar(
            navigationState = navigationState,
            onNavigationStateChange = { navigationState = it },
            modifier = Modifier.fillMaxWidth()
        )
        
        // Current state display
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Current State:",
                    style = MaterialTheme.typography.titleMedium
                )
                Text("Selected Tab: ${navigationState.selectedTab.title}")
                Text("Style: ${navigationState.style}")
                Text("Show Labels: ${navigationState.showLabels}")
            }
        }
    }
}

