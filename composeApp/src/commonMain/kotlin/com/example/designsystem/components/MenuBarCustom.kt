package com.example.designsystem.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import designsystem.composeapp.generated.resources.Res
import designsystem.composeapp.generated.resources.menu_add
import designsystem.composeapp.generated.resources.menu_favorite
import designsystem.composeapp.generated.resources.menu_home
import designsystem.composeapp.generated.resources.menu_search
import designsystem.composeapp.generated.resources.menu_setting
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
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {

        Box(
            modifier = Modifier.fillMaxSize()
                .shadow(elevation = elevation, shape = shape, clip = false)
                .background(color = backgroundColor, shape = shape)
                .border(
                    width = borderWidth,
                    color = borderColor,
                    shape = shape
                )
        )
        Box(
            modifier = Modifier.fillMaxSize()
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth().weight(1f)
                ){
                    content()
                }
                Spacer(Modifier.height(16.dp))
                Box(
                    Modifier.background(Color.Red).fillMaxWidth().height(10.dp)
                )
            }
        }
    }
}

@Composable
fun MenuContent(
    modifier: Modifier = Modifier,
){
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        BaseMenuItem(
            isSelected = true,
            modifier = Modifier.weight(1f).aspectRatio(1f),
            icon = Res.drawable.menu_home,
            label = ActiveLabel.Text(
                text = "Home",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        )
        BaseMenuItem(
            isSelected = false,
            modifier = Modifier.weight(1f).aspectRatio(1f),
            icon = Res.drawable.menu_search,
            label = ActiveLabel.Text(
                text = "Home",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        )
        BaseMenuItem(
            isSelected = false,
            modifier = Modifier.weight(1f).aspectRatio(1f),
            icon = Res.drawable.menu_add,
            label = ActiveLabel.None,
            brush = Brush.horizontalGradient(
                colors = listOf(Color.Red, Color.Yellow, Color.Green)
            ),
            shape = CircleShape
        )
        BaseMenuItem(
            isSelected = false,
            modifier = Modifier.weight(1f).aspectRatio(1f),
            icon = Res.drawable.menu_favorite,
            label = ActiveLabel.Text(
                text = "Home",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        )
        BaseMenuItem(
            isSelected = true,
            modifier = Modifier.weight(1f).aspectRatio(1f),
            icon = Res.drawable.menu_setting,
            label = ActiveLabel.Dot(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color.Red, Color.Yellow)
                ),
                shape = RoundedCornerShape(4.dp)
            )
        )
    }
}

@Composable
fun BaseMenuItem(
    isSelected: Boolean = false,
    label: ActiveLabel = ActiveLabel.None,
    brush: Brush = SolidColor(Color.Transparent),
    shape: Shape = CircleShape,
    modifier: Modifier = Modifier,
    icon: DrawableResource
){
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(brush = brush, shape = shape),
    ){
        Column(
            modifier = Modifier.fillMaxSize().align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
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
            .fillMaxSize()
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
fun BaseMenuItemPreview() {
    CardMenuCustom (
        modifier = Modifier.fillMaxWidth().wrapContentHeight(),
        backgroundColor = Color.White,
        borderColor = Color.Gray,
        borderWidth = 1.dp,
        shape = RoundedCornerShape(24.dp)
    ){
        MenuContent(
            modifier = Modifier.fillMaxWidth()
        )
    }
}

