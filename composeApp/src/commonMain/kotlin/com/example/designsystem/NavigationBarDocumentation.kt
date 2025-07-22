package com.example.designsystem

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.designsystem.components.NavigationBar
import com.example.designsystem.components.NavigationBarDemo
import com.example.designsystem.components.NavigationModifiers
import com.example.designsystem.components.NavigationState
import com.example.designsystem.components.NavigationStyle
import com.example.designsystem.components.NavigationTab
import com.example.designsystem.components.title
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * NAVIGATION BAR COMPONENT DOCUMENTATION
 * 
 * This component demonstrates three key Compose concepts:
 * 1. State Hoisting
 * 2. Modifier & Layout Patterns
 * 3. Component Variants & Configuration
 */

@Composable
@Preview
fun NavigationBarDocumentation() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Navigation Bar Component Concepts",
            style = MaterialTheme.typography.headlineLarge
        )
        
        // 1. State Hoisting Section
        StateHoistingSection()
        
        // 2. Modifier Patterns Section
        ModifierPatternsSection()
        
        // 3. Component Variants Section
        ComponentVariantsSection()
        
        // 4. Live Demo
        LiveDemoSection()
    }
}

@Composable
private fun StateHoistingSection() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "1. State Hoisting",
                style = MaterialTheme.typography.headlineMedium
            )
            
            Text(
                text = "State hoisting is a pattern where state is moved up to a common ancestor " +
                       "of all composables that need to read or write that state. This makes the " +
                       "state reusable and testable.",
                style = MaterialTheme.typography.bodyMedium
            )
            
            // Example of state hoisting
            var selectedTab by remember { mutableStateOf(NavigationTab.Home) }
            var showLabels by remember { mutableStateOf(false) }
            
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { selectedTab = NavigationTab.Home }
                ) {
                    Text("Home")
                }
                Button(
                    onClick = { selectedTab = NavigationTab.Search }
                ) {
                    Text("Search")
                }
                Button(
                    onClick = { showLabels = !showLabels }
                ) {
                    Text("Toggle Labels")
                }
            }
            
            Text(
                text = "Current State: ${selectedTab.title}, Labels: $showLabels",
                style = MaterialTheme.typography.bodySmall
            )
            
            // The state is hoisted and passed down to child components
            NavigationBar(
                navigationState = NavigationState(
                    selectedTab = selectedTab,
                    showLabels = showLabels
                ),
                onNavigationStateChange = { newState ->
                    selectedTab = newState.selectedTab
                    showLabels = newState.showLabels
                }
            )
        }
    }
}

@Composable
private fun ModifierPatternsSection() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "2. Modifier & Layout Patterns",
                style = MaterialTheme.typography.headlineMedium
            )
            
            Text(
                text = "Modifier patterns help create reusable, composable UI elements. " +
                       "They encapsulate common styling and behavior.",
                style = MaterialTheme.typography.bodyMedium
            )
            
            // Example of modifier patterns
            Text(
                text = "Available Modifier Patterns:",
                style = MaterialTheme.typography.titleMedium
            )
            
            Text("• glassmorphismLight - Light theme glassmorphism effect")
            Text("• glassmorphismDark - Dark theme glassmorphism effect")
            Text("• iconContainer - Standard icon container")
            Text("• activeIconContainer - Active state with gradient background")
            
            // Visual examples
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Light glassmorphism
                Box(
                    modifier = NavigationModifiers.glassmorphismLight
                        .size(80.dp)
                ) {
                    Text(
                        text = "Light",
                        modifier = Modifier.padding(8.dp)
                    )
                }
                
                // Dark glassmorphism
                Box(
                    modifier = NavigationModifiers.glassmorphismDark
                        .size(80.dp)
                ) {
                    Text(
                        text = "Dark",
                        modifier = Modifier.padding(8.dp)
                    )
                }
                
                // Active icon container
                Box(
                    modifier = NavigationModifiers.activeIconContainer
                        .size(80.dp)
                ) {
                    Text(
                        text = "Active",
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun ComponentVariantsSection() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "3. Component Variants & Configuration",
                style = MaterialTheme.typography.headlineMedium
            )
            
            Text(
                text = "Component variants allow the same component to be used in different " +
                       "contexts with different configurations.",
                style = MaterialTheme.typography.bodyMedium
            )
            
            // Show different variants
            Text(
                text = "Available Variants:",
                style = MaterialTheme.typography.titleMedium
            )
            
            // Variant 1: Default style, no labels
            Text("Variant 1: Default Style, Icon Only")
            NavigationBar(
                navigationState = NavigationState(
                    selectedTab = NavigationTab.Home,
                    showLabels = false,
                    style = NavigationStyle.Default
                ),
                onNavigationStateChange = { },
                modifier = Modifier.fillMaxWidth()
            )
            
            // Variant 2: Default style, with labels
            Text("Variant 2: Default Style, With Labels")
            NavigationBar(
                navigationState = NavigationState(
                    selectedTab = NavigationTab.Search,
                    showLabels = true,
                    style = NavigationStyle.Default
                ),
                onNavigationStateChange = { },
                modifier = Modifier.fillMaxWidth()
            )
            
            // Variant 3: Dark style, no labels
            Text("Variant 3: Dark Style, Icon Only")
            NavigationBar(
                navigationState = NavigationState(
                    selectedTab = NavigationTab.Favorite,
                    showLabels = false,
                    style = NavigationStyle.Dark
                ),
                onNavigationStateChange = { },
                modifier = Modifier.fillMaxWidth()
            )
            
            // Variant 4: Dark style, with labels
            Text("Variant 4: Dark Style, With Labels")
            NavigationBar(
                navigationState = NavigationState(
                    selectedTab = NavigationTab.Account,
                    showLabels = true,
                    style = NavigationStyle.Dark
                ),
                onNavigationStateChange = { },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun LiveDemoSection() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "4. Live Interactive Demo",
                style = MaterialTheme.typography.headlineMedium
            )
            
            Text(
                text = "Try the interactive navigation bar below:",
                style = MaterialTheme.typography.bodyMedium
            )

            NavigationBarDemo()
        }
    }
}

/**
 * KEY CONCEPTS EXPLAINED:
 * 
 * 1. STATE HOISTING:
 *    - NavigationState is defined as a data class containing all navigation-related state
 *    - State is hoisted to the parent component (NavigationBarDemo)
 *    - Child components receive state as parameters and emit changes via callbacks
 *    - Benefits: Reusability, testability, single source of truth
 * 
 * 2. MODIFIER PATTERNS:
 *    - NavigationModifiers object contains reusable modifier patterns
 *    - Each pattern encapsulates specific styling (glassmorphism, shadows, etc.)
 *    - Patterns can be combined using .then() or chained together
 *    - Benefits: Consistency, reusability, maintainability
 * 
 * 3. COMPONENT VARIANTS:
 *    - NavigationBar supports multiple configurations through NavigationState
 *    - Different styles (Default/Dark) and label visibility options
 *    - Same component logic, different visual presentations
 *    - Benefits: Flexibility, consistency, reduced code duplication
 * 
 * USAGE EXAMPLES:
 * 
 * // Basic usage with state hoisting
 * var navigationState by remember { mutableStateOf(NavigationState()) }
 * NavigationBar(
 *     navigationState = navigationState,
 *     onNavigationStateChange = { navigationState = it }
 * )
 * 
 * // Custom styling with modifier patterns
 * Box(
 *     modifier = NavigationModifiers.glassmorphismLight
 *         .then(Modifier.size(100.dp))
 * ) { ... }
 * 
 * // Different variants
 * NavigationBar(
 *     navigationState = NavigationState(
 *         style = NavigationStyle.Dark,
 *         showLabels = true
 *     ),
 *     onNavigationStateChange = { ... }
 * )
 */ 