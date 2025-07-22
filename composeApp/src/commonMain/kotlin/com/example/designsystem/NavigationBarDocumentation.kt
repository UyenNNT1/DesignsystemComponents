package com.example.designsystem

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * NAVIGATION BAR COMPONENT DOCUMENTATION
 * 
 * This component demonstrates three key Compose concepts:
 * 1. State Hoisting
 * 2. Modifier & Layout Patterns
 * 3. Component Variants & Configuration
 */

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