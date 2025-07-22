package com.example.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.designsystem.components.CardModifiers.cardBase
import com.example.designsystem.components.CardModifiers.glassmorphismEffect
import com.example.designsystem.components.CardModifiers.gradientOverlay
import org.jetbrains.compose.ui.tooling.preview.Preview

// ============================================================================
// 1. STATE HOISTING - Data classes and state management
// ============================================================================

/**
 * Card data model - represents the content of a card
 */
data class CardData(
    val title: String,
    val location: String? = null,
    val imageUrl: String? = null,
    val rating: Float? = null,
    val tag: String? = null,
    val isPro: Boolean = false
)

/**
 * Card state - manages the interactive state of the card
 */
data class CardState(
    val isSelected: Boolean = false,
    val isPressed: Boolean = false
)

/**
 * Card events - defines possible interactions with the card
 */
sealed class CardEvent {
    object Click : CardEvent()
    object LongPress : CardEvent()
    object SelectionChanged : CardEvent()
}

// ============================================================================
// 2. MODIFIER & LAYOUT PATTERNS - Reusable modifier extensions
// ============================================================================

/**
 * Card modifier patterns for consistent styling
 */
object CardModifiers {
    
    /**
     * Base card modifier with consistent styling
     */
    fun Modifier.cardBase(
        cardVariant: CardVariant,
        cardState: CardState
    ): Modifier = this
        .clip(RoundedCornerShape(16.dp))
        .then(
            when (cardVariant) {
                CardVariant.SOLID -> Modifier.background(Color(0xFFFEF7FF))
                CardVariant.IMAGE_BACKGROUND -> Modifier.background(Color.Transparent)
                CardVariant.GLASSMORPHISM -> Modifier.background(
                    Color.White.copy(alpha = 0.01f),
                    RoundedCornerShape(16.dp)
                )
            }
        )
        .then(
            if (cardState.isSelected) {
                Modifier.border(
                    width = 1.dp,
                    color = Color(0xFF8A38F5),
                    shape = RoundedCornerShape(16.dp)
                )
            } else {
                Modifier
            }
        )
    
    /**
     * Glassmorphism effect modifier
     */
    fun Modifier.glassmorphismEffect(): Modifier = this
        .background(
            Color.White.copy(alpha = 0.01f),
            RoundedCornerShape(8.dp)
        )
    
    /**
     * Gradient overlay modifier for image backgrounds
     */
    fun Modifier.gradientOverlay(): Modifier = this
        .background(
            Brush.verticalGradient(
                colors = listOf(
                    Color.Black.copy(alpha = 0f),
                    Color.Black.copy(alpha = 1f)
                ),
                startY = 0f,
                endY = Float.POSITIVE_INFINITY
            )
        )
}

// ============================================================================
// 3. COMPONENT VARIANTS & CONFIGURATION - Flexible component system
// ============================================================================

/**
 * Card size variants
 */
enum class CardSize {
    SMALL,    // 1:1 ratio
    MEDIUM,   // 1:2 ratio  
    LARGE     // 2:3 ratio
}

/**
 * Card style variants based on Figma design
 */
enum class CardVariant {
    SOLID,           // Card 1 - Solid background with image
    IMAGE_BACKGROUND, // Card 2,3,5,6 - Image background with overlay
    GLASSMORPHISM    // Card 4,7 - Glassmorphism effect
}

/**
 * Card configuration - combines all variant options
 */
data class CardConfiguration(
    val size: CardSize = CardSize.MEDIUM,
    val variant: CardVariant = CardVariant.SOLID,
    val showLocation: Boolean = true,
    val showRating: Boolean = false,
    val showTag: Boolean = false,
    val showProBadge: Boolean = false,
    val onClick: (() -> Unit)? = null,
    val onLongClick: (() -> Unit)? = null
)

/**
 * Main Card component with state hoisting and variant support
 */
@Composable
fun CardCustom(
    data: CardData,
    configuration: CardConfiguration,
    state: CardState,
    onStateChange: (CardState) -> Unit,
    modifier: Modifier = Modifier
) {
    val cardModifier = modifier
        .cardBase(configuration.variant, state)
        .then(
            if (configuration.onClick != null) {
                Modifier.clickable(
                    onClick = {
                        configuration.onClick?.invoke()
                        onStateChange(state.copy(isPressed = true))
                    }
                )
            } else {
                Modifier
            }
        )
    
    when (configuration.variant) {
        CardVariant.SOLID -> SolidCard(
            data = data,
            configuration = configuration,
            state = state,
            modifier = cardModifier
        )
        CardVariant.IMAGE_BACKGROUND -> ImageBackgroundCard(
            data = data,
            configuration = configuration,
            state = state,
            modifier = cardModifier
        )
        CardVariant.GLASSMORPHISM -> GlassmorphismCard(
            data = data,
            configuration = configuration,
            state = state,
            modifier = cardModifier
        )
    }
}

// ============================================================================
// CARD VARIANT IMPLEMENTATIONS
// ============================================================================

@Composable
private fun SolidCard(
    data: CardData,
    configuration: CardConfiguration,
    state: CardState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Image placeholder
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFE8E0EC))
        )
        
        // Card info
        CardInfo(
            data = data,
            configuration = configuration,
            textColor = Color(0xFF1D1B20)
        )
    }
}

@Composable
private fun ImageBackgroundCard(
    data: CardData,
    configuration: CardConfiguration,
    state: CardState,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(
                when (configuration.size) {
                    CardSize.SMALL -> 1f
                    CardSize.MEDIUM -> 0.636f  // 1:1.57 ratio
                    CardSize.LARGE -> 0.692f   // 2:3 ratio
                }
            )
    ) {
        // Background image placeholder
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE8E0EC))
                .gradientOverlay()
        )
        
        // Content overlay
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(11.dp, 14.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top section (tags, pro badge)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                if (configuration.showTag && data.tag != null) {
                    TagComponent(text = data.tag)
                }
                if (configuration.showProBadge && data.isPro) {
                    ProBadgeComponent()
                }
            }
            
            // Bottom section (title, location, rating)
            CardInfo(
                data = data,
                configuration = configuration,
                textColor = Color.White
            )
        }
    }
}

@Composable
private fun GlassmorphismCard(
    data: CardData,
    configuration: CardConfiguration,
    state: CardState,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(
                when (configuration.size) {
                    CardSize.SMALL -> 1f
                    CardSize.MEDIUM -> 0.636f
                    CardSize.LARGE -> 0.692f
                }
            )
    ) {
        // Background image
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE8E0EC))
        )
        
        // Glassmorphism content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(11.dp, 14.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                if (configuration.showTag && data.tag != null) {
                    TagComponent(text = data.tag)
                }
                if (configuration.showProBadge && data.isPro) {
                    ProBadgeComponent()
                }
            }
            
            // Bottom section with glassmorphism effect
            CardInfo(
                data = data,
                configuration = configuration,
                textColor = Color(0xFF1D1B20),
                modifier = Modifier.glassmorphismEffect().padding(6.dp, 10.dp)
            )
        }
    }
}

// ============================================================================
// SUB-COMPONENTS
// ============================================================================

@Composable
private fun CardInfo(
    data: CardData,
    configuration: CardConfiguration,
    textColor: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        // Title and rating row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = data.title,
                color = textColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            
            if (configuration.showRating && data.rating != null) {
                RatingComponent(rating = data.rating)
            }
        }
        
        // Location
        if (configuration.showLocation && data.location != null) {
            Text(
                text = data.location,
                color = Color(0xFF625B71),
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun TagComponent(text: String) {
    Box(
        modifier = Modifier
            .glassmorphismEffect()
            .padding(6.dp, 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 10.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
private fun ProBadgeComponent() {
    Row(
        modifier = Modifier
            .glassmorphismEffect()
            .padding(6.dp, 10.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Crown icon placeholder
        Box(
            modifier = Modifier
                .size(16.dp)
                .background(Color.White, RoundedCornerShape(2.dp))
        )
        
        Text(
            text = "Pro",
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
private fun RatingComponent(rating: Float) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Star icon placeholder
        Box(
            modifier = Modifier
                .size(16.dp)
                .background(Color(0xFFFFBE41), RoundedCornerShape(2.dp))
        )
        
        Text(
            text = rating.toString(),
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

// ============================================================================
// PREVIEWS
// ============================================================================

@Preview()
@Composable
fun CardCustomPreview() {
    val sampleData = CardData(
        title = "Ha Long Bay",
        location = "Quang Ninh, Vietnam",
        rating = 4.8f,
        tag = "Tag Marker",
        isPro = true
    )
    
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Solid variant
        CardCustom(
            data = sampleData,
            configuration = CardConfiguration(
                size = CardSize.MEDIUM,
                variant = CardVariant.SOLID
            ),
            state = CardState(),
            onStateChange = {}
        )
        
        // Image background variant
        CardCustom(
            data = sampleData,
            configuration = CardConfiguration(
                size = CardSize.MEDIUM,
                variant = CardVariant.IMAGE_BACKGROUND,
                showRating = true
            ),
            state = CardState(),
            onStateChange = {}
        )
        
        // Glassmorphism variant
        CardCustom(
            data = sampleData,
            configuration = CardConfiguration(
                size = CardSize.MEDIUM,
                variant = CardVariant.GLASSMORPHISM,
                showTag = true,
                showProBadge = true
            ),
            state = CardState(),
            onStateChange = {}
        )
    }
}