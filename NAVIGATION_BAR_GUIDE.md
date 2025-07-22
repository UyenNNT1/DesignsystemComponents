# Navigation Bar Component - Compose Concepts Guide

This guide explains the three key Compose concepts demonstrated in the Navigation Bar component based on the Figma design.

## 🎯 Overview

The Navigation Bar component demonstrates three fundamental Compose patterns:
1. **State Hoisting** - Managing state at the appropriate level
2. **Modifier & Layout Patterns** - Reusable styling and layout patterns
3. **Component Variants & Configuration** - Flexible component configurations

## 📋 Table of Contents

- [1. State Hoisting](#1-state-hoisting)
- [2. Modifier & Layout Patterns](#2-modifier--layout-patterns)
- [3. Component Variants & Configuration](#3-component-variants--configuration)
- [4. Usage Examples](#4-usage-examples)
- [5. Implementation Details](#5-implementation-details)

---

## 1. State Hoisting

### What is State Hoisting?

State hoisting is a pattern where state is moved up to a common ancestor of all composables that need to read or write that state. This makes the state reusable and testable.

### Why Use State Hoisting?

- **Single Source of Truth**: State is managed in one place
- **Reusability**: Components can be reused with different state
- **Testability**: State logic can be tested independently
- **Predictability**: State changes flow in one direction

### Implementation in Navigation Bar

```kotlin
// State is hoisted to the parent component
data class NavigationState(
    val selectedTab: NavigationTab = NavigationTab.Home,
    val showLabels: Boolean = false,
    val style: NavigationStyle = NavigationStyle.Default
)

// Parent component manages state
@Composable
fun NavigationBarDemo() {
    var navigationState by remember { mutableStateOf(NavigationState()) }
    
    NavigationBar(
        navigationState = navigationState,
        onNavigationStateChange = { navigationState = it }
    )
}
```

### State Flow Pattern

```
Parent Component (State Owner)
    ↓ (passes state down)
NavigationBar Component
    ↓ (emits changes up)
Parent Component (updates state)
```

---

## 2. Modifier & Layout Patterns

### What are Modifier Patterns?

Modifier patterns are reusable combinations of modifiers that encapsulate common styling and behavior. They help maintain consistency and reduce code duplication.

### Benefits of Modifier Patterns

- **Consistency**: Same styling across components
- **Reusability**: Patterns can be applied to any component
- **Maintainability**: Changes in one place affect all usages
- **Composability**: Patterns can be combined and extended

### Implementation in Navigation Bar

```kotlin
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
```

### Using Modifier Patterns

```kotlin
// Apply pattern directly
Box(
    modifier = NavigationModifiers.glassmorphismLight
) { ... }

// Combine patterns
Box(
    modifier = NavigationModifiers.glassmorphismLight
        .then(Modifier.size(100.dp))
) { ... }

// Extend patterns
val customModifier = NavigationModifiers.glassmorphismLight
    .then(Modifier.border(2.dp, Color.Blue))
```

---

## 3. Component Variants & Configuration

### What are Component Variants?

Component variants allow the same component to be used in different contexts with different configurations. They provide flexibility while maintaining consistency.

### Benefits of Component Variants

- **Flexibility**: Same component, different appearances
- **Consistency**: Shared logic and behavior
- **Reduced Duplication**: One component, multiple use cases
- **Maintainability**: Changes affect all variants

### Implementation in Navigation Bar

```kotlin
enum class NavigationStyle {
    Default, // Light theme with glassmorphism
    Dark     // Dark theme with glassmorphism
}

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
    
    // Component logic remains the same, appearance changes based on configuration
}
```

### Available Variants

| Variant | Style | Labels | Description |
|---------|-------|--------|-------------|
| Default | Light | No | Icon-only navigation with light glassmorphism |
| Default | Light | Yes | Navigation with labels and light glassmorphism |
| Dark | Dark | No | Icon-only navigation with dark glassmorphism |
| Dark | Dark | Yes | Navigation with labels and dark glassmorphism |

---

## 4. Usage Examples

### Basic Usage

```kotlin
@Composable
fun MyScreen() {
    var navigationState by remember { mutableStateOf(NavigationState()) }
    
    NavigationBar(
        navigationState = navigationState,
        onNavigationStateChange = { navigationState = it }
    )
}
```

### Custom Configuration

```kotlin
@Composable
fun CustomNavigation() {
    var navigationState by remember {
        mutableStateOf(
            NavigationState(
                selectedTab = NavigationTab.Search,
                showLabels = true,
                style = NavigationStyle.Dark
            )
        )
    }
    
    NavigationBar(
        navigationState = navigationState,
        onNavigationStateChange = { navigationState = it }
    )
}
```

### State Management with ViewModel

```kotlin
class NavigationViewModel : ViewModel() {
    private val _navigationState = MutableStateFlow(NavigationState())
    val navigationState = _navigationState.asStateFlow()
    
    fun updateNavigationState(newState: NavigationState) {
        _navigationState.value = newState
    }
}

@Composable
fun NavigationWithViewModel(viewModel: NavigationViewModel) {
    val navigationState by viewModel.navigationState.collectAsState()
    
    NavigationBar(
        navigationState = navigationState,
        onNavigationStateChange = { viewModel.updateNavigationState(it) }
    )
}
```

---

## 5. Implementation Details

### Component Structure

```
NavigationBar (Main Component)
├── NavigationIcon (Individual Icons)
│   ├── Icon Container
│   ├── Active Indicator
│   └── Label (optional)
└── ActiveIndicator (Bottom Line)
    ├── Background Line
    └── Animated Indicator
```

### Key Features

- **Animated Transitions**: Smooth animations when switching tabs
- **Glassmorphism Effects**: Modern glass-like appearance
- **Gradient Backgrounds**: Beautiful gradient effects for active states
- **Responsive Design**: Adapts to different screen sizes
- **Accessibility**: Proper content descriptions and touch targets

### Color Scheme

```kotlin
// Light Theme Colors
val LightBackground = Color.White.copy(alpha = 0.01f)
val LightIconInactive = Color(0xFF625B71)
val LightIconActive = Color(0xFFFEF7FF)

// Dark Theme Colors
val DarkBackground = Color.Black.copy(alpha = 0.3f)
val DarkIconInactive = Color(0xFFE8DEF8)
val DarkIconActive = Color(0xFFFEF7FF)

// Gradient Colors
val GradientColors = listOf(
    Color(0xFFAFA2EF),
    Color(0xFF6F58E2),
    Color(0xFFC34EFE)
)
```

### Animation Details

```kotlin
val animatedOffset by animateFloatAsState(
    targetValue = selectedTab.ordinal * 0.25f,
    animationSpec = tween(durationMillis = 300),
    label = "indicator_offset"
)
```

---

## 🚀 Getting Started

1. **Run the Demo**: Use the "Navigation Demo" button in the app
2. **View Documentation**: Use the "Navigation Docs" button for detailed explanations
3. **Experiment**: Try different configurations and see how the component adapts
4. **Integrate**: Use the component in your own projects

## 📚 Additional Resources

- [Compose State Hoisting Guide](https://developer.android.com/jetpack/compose/state#state-hoisting)
- [Compose Modifiers Documentation](https://developer.android.com/jetpack/compose/modifiers)
- [Material Design 3 Guidelines](https://m3.material.io/)

---

*This component demonstrates best practices for building reusable, maintainable, and flexible Compose components.* 