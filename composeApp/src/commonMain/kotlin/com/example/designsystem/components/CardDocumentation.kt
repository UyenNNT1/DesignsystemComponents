package com.example.designsystem.components

/**
 * CARD COMPONENT DOCUMENTATION
 * 
 * This component demonstrates three key Compose patterns:
 * 1. State Hoisting
 * 2. Modifier & Layout Patterns  
 * 3. Component Variants & Configuration
 * 
 * Based on Figma design: DS AI Video - Card component set
 */

// ============================================================================
// 1. STATE HOISTING PATTERN
// ============================================================================

/**
 * STATE HOISTING IMPLEMENTATION
 * 
 * State hoisting is a pattern where state is moved up the component hierarchy
 * to make components more reusable and testable.
 * 
 * Key Benefits:
 * - Single source of truth for state
 * - Better testability
 * - Reusable components
 * - Predictable state flow
 * 
 * Implementation Details:
 * 
 * 1. Data Classes for State Management:
 *    - CardData: Immutable data model representing card content
 *    - CardState: Mutable state for interactive behavior
 *    - CardEvent: Sealed class for handling user interactions
 * 
 * 2. State Flow:
 *    - Parent component manages CardState
 *    - Card component receives state as parameters
 *    - State changes are communicated via callbacks
 * 
 * 3. Example Usage:
 *    ```kotlin
 *    var cardState by remember { mutableStateOf(CardState()) }
 *    
 *    CardCustom(
 *        data = cardData,
 *        configuration = configuration,
 *        state = cardState,
 *        onStateChange = { newState -> cardState = newState }
 *    )
 *    ```
 */

// ============================================================================
// 2. MODIFIER & LAYOUT PATTERNS
// ============================================================================

/**
 * MODIFIER & LAYOUT PATTERNS IMPLEMENTATION
 * 
 * Modifier patterns provide reusable styling and behavior that can be
 * composed together to create complex UI components.
 * 
 * Key Benefits:
 * - Reusable styling logic
 * - Consistent design system
 * - Easy to maintain and update
 * - Type-safe modifier composition
 * 
 * Implementation Details:
 * 
 * 1. CardModifiers Object:
 *    - Centralized modifier extensions
 *    - Consistent styling across variants
 *    - Easy to maintain and update
 * 
 * 2. Modifier Extensions:
 *    - cardBase(): Base styling for all card variants
 *    - glassmorphismEffect(): Glassmorphism visual effect
 *    - gradientOverlay(): Gradient overlay for image backgrounds
 * 
 * 3. Modifier Composition:
 *    - Uses .then() for conditional modifiers
 *    - Chains multiple modifiers together
 *    - Maintains type safety
 * 
 * 4. Example Usage:
 *    ```kotlin
 *    modifier
 *        .cardBase(cardVariant, cardState)
 *        .glassmorphismEffect()
 *        .gradientOverlay()
 *    ```
 */

// ============================================================================
// 3. COMPONENT VARIANTS & CONFIGURATION
// ============================================================================

/**
 * COMPONENT VARIANTS & CONFIGURATION IMPLEMENTATION
 * 
 * Component variants provide a flexible way to create different versions
 * of the same component with different visual styles and behaviors.
 * 
 * Key Benefits:
 * - Single component, multiple variants
 * - Consistent API across variants
 * - Easy to add new variants
 * - Type-safe configuration
 * 
 * Implementation Details:
 * 
 * 1. Variant Enums:
 *    - CardSize: SMALL, MEDIUM, LARGE (aspect ratios)
 *    - CardVariant: SOLID, IMAGE_BACKGROUND, GLASSMORPHISM
 * 
 * 2. Configuration Data Class:
 *    - Combines all variant options
 *    - Provides default values
 *    - Type-safe configuration
 * 
 * 3. Variant-Specific Implementations:
 *    - SolidCard: Simple card with solid background
 *    - ImageBackgroundCard: Card with image background and overlay
 *    - GlassmorphismCard: Card with glassmorphism effects
 * 
 * 4. Feature Flags:
 *    - showLocation: Controls location text visibility
 *    - showRating: Controls rating display
 *    - showTag: Controls tag display
 *    - showProBadge: Controls pro badge display
 * 
 * 5. Example Usage:
 *    ```kotlin
 *    CardConfiguration(
 *        size = CardSize.MEDIUM,
 *        variant = CardVariant.GLASSMORPHISM,
 *        showRating = true,
 *        showTag = true,
 *        showProBadge = true
 *    )
 *    ```
 */

// ============================================================================
// FIGMA DESIGN MAPPING
// ============================================================================

/**
 * FIGMA DESIGN MAPPING
 * 
 * The component variants are based on the Figma design:
 * 
 * Card 1 (Solid Variant):
 * - White background with image
 * - Title and location text
 * - Clean, simple design
 * 
 * Card 2, 3, 5, 6 (Image Background Variant):
 * - Image background with gradient overlay
 * - White text on dark overlay
 * - Optional rating display
 * 
 * Card 4, 7 (Glassmorphism Variant):
 * - Image background with glassmorphism effects
 * - Glassmorphism tags and badges
 * - Sophisticated visual effects
 * 
 * Size Variants:
 * - 1:1 ratio (SMALL)
 * - 1:2 ratio (MEDIUM) 
 * - 2:3 ratio (LARGE)
 */

// ============================================================================
// USAGE EXAMPLES
// ============================================================================

/**
 * BASIC USAGE EXAMPLE
 * 
 * ```kotlin
 * val cardData = CardData(
 *     title = "Ha Long Bay",
 *     location = "Quang Ninh, Vietnam",
 *     rating = 4.8f,
 *     tag = "Popular",
 *     isPro = true
 * )
 * 
 * var cardState by remember { mutableStateOf(CardState()) }
 * 
 * CardCustom(
 *     data = cardData,
 *     configuration = CardConfiguration(
 *         size = CardSize.MEDIUM,
 *         variant = CardVariant.GLASSMORPHISM,
 *         showRating = true,
 *         showTag = true,
 *         showProBadge = true,
 *         onClick = { /* Handle click */ }
 *     ),
 *     state = cardState,
 *     onStateChange = { newState -> cardState = newState }
 * )
 * ```
 */

/**
 * STATE MANAGEMENT EXAMPLE
 * 
 * ```kotlin
 * @Composable
 * fun CardList() {
 *     var selectedCardId by remember { mutableStateOf<String?>(null) }
 *     
 *     LazyColumn {
 *         items(cards) { cardData ->
 *             CardCustom(
 *                 data = cardData,
 *                 configuration = CardConfiguration(
 *                     variant = CardVariant.SOLID,
 *                     onClick = { /* Handle selection */ }
 *                 ),
 *                 state = CardState(
 *                     isSelected = selectedCardId == cardData.title
 *                 ),
 *                 onStateChange = { newState ->
 *                     if (newState.isPressed) {
 *                         selectedCardId = cardData.title
 *                     }
 *                 }
 *             )
 *         }
 *     }
 * }
 * ```
 */

/**
 * CUSTOM MODIFIER EXAMPLE
 * 
 * ```kotlin
 * // Extending the modifier system
 * fun Modifier.customCardEffect(): Modifier = this
 *     .background(
 *         Color.White.copy(alpha = 0.1f),
 *         RoundedCornerShape(12.dp)
 *     )
 *     .border(
 *         width = 2.dp,
 *         color = Color.White.copy(alpha = 0.2f),
 *         shape = RoundedCornerShape(12.dp)
 *     )
 * 
 * // Using custom modifier
 * CardCustom(
 *     data = cardData,
 *     configuration = configuration,
 *     state = cardState,
 *     onStateChange = onStateChange,
 *     modifier = Modifier.customCardEffect()
 * )
 * ```
 */

// ============================================================================
// BEST PRACTICES
// ============================================================================

/**
 * BEST PRACTICES IMPLEMENTED
 * 
 * 1. State Hoisting:
 *    - Keep state as close to where it's used as possible
 *    - Use immutable data classes for data
 *    - Use mutable state only for UI state
 *    - Communicate state changes via callbacks
 * 
 * 2. Modifier Patterns:
 *    - Create reusable modifier extensions
 *    - Use object to group related modifiers
 *    - Provide meaningful default values
 *    - Use .then() for conditional modifiers
 * 
 * 3. Component Variants:
 *    - Use enums for variant types
 *    - Provide configuration data class
 *    - Use feature flags for optional elements
 *    - Maintain consistent API across variants
 * 
 * 4. General:
 *    - Use meaningful names for components and functions
 *    - Provide comprehensive documentation
 *    - Include preview functions for testing
 *    - Follow Compose conventions and patterns
 */ 