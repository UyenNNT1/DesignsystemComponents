package com.example.designsystem.components

import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RenderEffect
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import designsystem.composeapp.generated.resources.Res
import designsystem.composeapp.generated.resources.img_test
import designsystem.composeapp.generated.resources.menu_setting_selected
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun BaseCardWithBlurBackground(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.White.copy(alpha = 0.3f),
    elevation: Dp = 0.dp,
    cornerRadius: Dp = 16.dp,
    blur: Float = 10f,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .graphicsLayer {
                renderEffect = BlurEffect(blur, blur)
            }
            .background(backgroundColor, shape = RoundedCornerShape(cornerRadius))
            .clip(RoundedCornerShape(cornerRadius))
            .shadow(elevation = elevation, shape = RoundedCornerShape(cornerRadius), clip = false)
    ) {
        content()
    }
}

@Preview()
@Composable
fun PreviewBaseCardWithBlurBackground() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        BaseCardWithBlurBackground(
            backgroundColor = Color.White.copy(alpha = 0.3f),
            elevation = 8.dp,
            cornerRadius = 24.dp,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .align(Alignment.Center)
        ) {
            /*Image(
                painter = painterResource(Res.drawable.img_test),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )*/
        }
    }
}

@Preview()
@Composable
fun GlowBoxPreview() {

}


