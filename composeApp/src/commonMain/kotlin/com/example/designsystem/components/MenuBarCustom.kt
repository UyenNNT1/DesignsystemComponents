package com.example.designsystem.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import designsystem.composeapp.generated.resources.Res
import designsystem.composeapp.generated.resources.menu_add
import designsystem.composeapp.generated.resources.menu_favorite
import designsystem.composeapp.generated.resources.menu_favorite_selected
import designsystem.composeapp.generated.resources.menu_home
import designsystem.composeapp.generated.resources.menu_home_selected
import designsystem.composeapp.generated.resources.menu_search
import designsystem.composeapp.generated.resources.menu_search_selected
import designsystem.composeapp.generated.resources.menu_setting
import designsystem.composeapp.generated.resources.menu_setting_selected
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CardMenuCustom(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Transparent,
    borderColor: Color = Color.Transparent,
    borderWidth: Dp = 1.dp,
    shape: Shape = RoundedCornerShape(8.dp),
    elevation: Dp = 4.dp,
    lineColor: Color = Color.Black,
    content: @Composable () -> Unit
) {
    BoxWithConstraints(
        modifier = modifier.fillMaxSize()
    ) {
        val lineWidth = maxWidth / 4.7f

        Box(
            modifier = Modifier
                .fillMaxSize()
                .shadow(elevation = elevation, shape = shape, clip = false)
                .background(color = backgroundColor, shape = shape)
                .border(
                    width = borderWidth,
                    color = borderColor,
                    shape = shape
                )
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopCenter)
        ) {
            content()
        }
        Box(
            modifier = Modifier
                .padding(8.dp)
                .width(lineWidth)
                .height(4.dp)
                .background(
                    color = lineColor,
                    shape = RoundedCornerShape(10.dp)
                )
                .align(Alignment.BottomCenter)
        )
    }
}


@Composable
fun MenuContentLayout1(
    modifier: Modifier = Modifier,
){
    var selectedIndex by remember { mutableStateOf(0) }

    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        BaseMenuItem(
            isSelected = selectedIndex == 0,
            modifier = Modifier.weight(1f).aspectRatio(1f),
            selectedIcon =  Res.drawable.menu_home_selected ,
            defaultIcon = Res.drawable.menu_home,
            label = ActiveLabel.Text(
                text = "Home",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.surface
            ),
            onClick = { selectedIndex = 0 }
        )
        BaseMenuItem(
            isSelected = selectedIndex == 1,
            modifier = Modifier.weight(1f).aspectRatio(1f),
            selectedIcon =  Res.drawable.menu_search_selected ,
            defaultIcon = Res.drawable.menu_search,
            label = ActiveLabel.Text(
                text = "Search",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.surface
            ),
            onClick = { selectedIndex = 1 }
        )
        BaseMenuItem(
            isSelected = selectedIndex == 2,
            modifier = Modifier.weight(1f).aspectRatio(1f),
            defaultIcon = Res.drawable.menu_add,
            label = ActiveLabel.None,
            brush = Brush.horizontalGradient(
                colors = listOf(Color(0xFFAFA2EF), Color(0xFF6F58E2), Color(0xFFC34EFE))
            ),
            shape = CircleShape,
            onClick = { selectedIndex = 2 }
        )
        BaseMenuItem(
            isSelected = selectedIndex == 3,
            modifier = Modifier.weight(1f).aspectRatio(1f),
            selectedIcon =  Res.drawable.menu_favorite_selected ,
            defaultIcon = Res.drawable.menu_favorite,
            label = ActiveLabel.Text(
                text = "Favorite",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.surface
            ),
            onClick = { selectedIndex = 3 }
        )
        BaseMenuItem(
            isSelected = selectedIndex == 4,
            modifier = Modifier.weight(1f).aspectRatio(1f),
            selectedIcon =  Res.drawable.menu_setting_selected ,
            defaultIcon = Res.drawable.menu_setting,
            label = ActiveLabel.Text(
                text = "Setting",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.surface
            ),
            onClick = { selectedIndex = 4 }
        )
    }
}

@Composable
fun MenuContentLayout2(
    modifier: Modifier = Modifier,
){
    var selectedIndex by remember { mutableStateOf(0) }

    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        BaseMenuItem(
            isSelected = selectedIndex == 0,
            modifier = Modifier.weight(1f).aspectRatio(1f),
            selectedIcon =  Res.drawable.menu_home_selected ,
            defaultIcon = Res.drawable.menu_home,
            label = ActiveLabel.Dot(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xFFAFA2EF), Color(0xFF6F58E2), Color(0xFFC34EFE))
                ),
                shape = RoundedCornerShape(4.dp)
            ),
            onClick = { selectedIndex = 0 }
        )
        BaseMenuItem(
            isSelected = selectedIndex == 1,
            modifier = Modifier.weight(1f).aspectRatio(1f),
            selectedIcon =  Res.drawable.menu_search_selected ,
            defaultIcon = Res.drawable.menu_search,
            label = ActiveLabel.Dot(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xFFAFA2EF), Color(0xFF6F58E2), Color(0xFFC34EFE))
                ),
                shape = RoundedCornerShape(4.dp)
            ),
            onClick = { selectedIndex = 1 }
        )
        BaseMenuItem(
            isSelected = selectedIndex == 2,
            modifier = Modifier.weight(1f).aspectRatio(1f),
            defaultIcon = Res.drawable.menu_add,
            label = ActiveLabel.None,
            brush = Brush.horizontalGradient(
                colors = listOf(Color(0xFFAFA2EF), Color(0xFF6F58E2), Color(0xFFC34EFE))
            ),
            shape = CircleShape,
            onClick = { selectedIndex = 2 }
        )
        BaseMenuItem(
            isSelected = selectedIndex == 3,
            modifier = Modifier.weight(1f).aspectRatio(1f),
            selectedIcon =  Res.drawable.menu_favorite_selected ,
            defaultIcon = Res.drawable.menu_favorite,
            label =ActiveLabel.Dot(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xFFAFA2EF), Color(0xFF6F58E2), Color(0xFFC34EFE))
                ),
                shape = RoundedCornerShape(4.dp)
            ),
            onClick = { selectedIndex = 3 }
        )
        BaseMenuItem(
            isSelected = selectedIndex == 4,
            modifier = Modifier.weight(1f).aspectRatio(1f),
            selectedIcon =  Res.drawable.menu_setting_selected ,
            defaultIcon = Res.drawable.menu_setting,
            label = ActiveLabel.Dot(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xFFAFA2EF), Color(0xFF6F58E2), Color(0xFFC34EFE))
                ),
                shape = RoundedCornerShape(4.dp)
            ),
            onClick = { selectedIndex = 4 }
        )
    }
}

@Composable
fun BaseMenuItem(
    defaultIcon: DrawableResource,
    selectedIcon: DrawableResource = defaultIcon,
    isSelected: Boolean = false,
    label: ActiveLabel = ActiveLabel.None,
    brush: Brush = SolidColor(Color.Transparent),
    shape: Shape = CircleShape,
    selectedColor: Color = Color.Unspecified,
    unSelectedColor: Color = Color.Unspecified,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
){
    val colorFilter = when {
        isSelected && selectedColor != Color.Unspecified -> ColorFilter.tint(selectedColor)
        !isSelected && unSelectedColor != Color.Unspecified -> ColorFilter.tint(unSelectedColor)
        else -> null
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(brush = brush, shape = shape)
            .clickable { onClick() },
    ){
        Column(
            modifier = Modifier.fillMaxSize().align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(if (isSelected) selectedIcon else defaultIcon),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                colorFilter = colorFilter
            )

            Spacer(modifier = Modifier.height(4.dp))

            if (isSelected){
                when (label) {
                    is ActiveLabel.None -> Unit
                    is ActiveLabel.Text -> TextActiveLabel(
                        text = label.text,
                        color = label.color,
                        style = label.style
                    )
                    is ActiveLabel.Dot -> DotActiveLabel(
                        brush = label.brush,
                        shape = label.shape
                    )
                }
            }
        }
    }
}
sealed class ActiveLabel {
    object None : ActiveLabel()
    data class Text(val text: String, val style: TextStyle, val color: Color) : ActiveLabel()
    data class Dot(val brush: Brush, val shape: Shape = CircleShape) : ActiveLabel()
}

@Composable
fun BaseActiveLabel(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        content = content
    )
}

@Composable
fun TextActiveLabel(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.labelMedium,
    color: Color = MaterialTheme.colorScheme.onSurface
){
    BaseActiveLabel(
        modifier = modifier
            .fillMaxWidth()
    ){
        Text(
            text = text,
            modifier = Modifier,
            style = style,
            color = color
        )
    }
}

@Composable
fun DotActiveLabel(
    modifier: Modifier = Modifier,
    brush: Brush = SolidColor(Color.Blue),
    shape: Shape = CircleShape
) {
    BaseActiveLabel(
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .background(brush = brush, shape = shape)
        )
    }
}

@Composable
@Preview
fun BaseActiveLabelPreview() {
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextActiveLabel(
            text = "Home",
            modifier = Modifier,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        DotActiveLabel(
            modifier = Modifier,
            brush = Brush.horizontalGradient(
                colors = listOf(Color.Red, Color.Yellow)
            ),
            shape = RoundedCornerShape(4.dp)
        )
    }
}

@Composable
@Preview
fun MenuLayout2Preview() {
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
}

@Composable
@Preview
fun MenuLayout1Preview() {
    CardMenuCustom (
        modifier = Modifier.fillMaxWidth().height(100.dp).padding(8.dp),
        backgroundColor = MaterialTheme.colorScheme.onBackground,
        borderColor = Color.Gray,
        borderWidth = 1.dp,
        shape = RoundedCornerShape(36.dp),
        lineColor = MaterialTheme.colorScheme.surface,
        elevation = 4.dp
    ){
        MenuContentLayout2(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 8.dp)
        )
    }
}

