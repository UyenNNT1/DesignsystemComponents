package com.example.designsystem.material3

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import designsystem.composeapp.generated.resources.Res
import designsystem.composeapp.generated.resources.manrope
import designsystem.composeapp.generated.resources.manrope_bold
import designsystem.composeapp.generated.resources.manrope_medium
import designsystem.composeapp.generated.resources.manrope_semibold
import org.jetbrains.compose.resources.Font

val bodyFontFamily: FontFamily
    @Composable
    get() = FontFamily(
        Font(Res.font.manrope)
    )

val labelFontFamily: FontFamily
    @Composable
    get() = FontFamily(
        Font(Res.font.manrope)
    )

val titleFontFamily: FontFamily
    @Composable
    get() = FontFamily(
        Font(Res.font.manrope_medium)
    )

val displayFontFamily: FontFamily
    @Composable
    get() = FontFamily(
        Font(Res.font.manrope_bold)
    )

val headlineFontFamily: FontFamily
    @Composable
    get() = FontFamily(
        Font(Res.font.manrope_semibold)
    )

val baseline = Typography()

val AppTypography: Typography
    @Composable
    get() = Typography(
        displayLarge = baseline.displayLarge.copy(fontFamily = displayFontFamily),
        displayMedium = baseline.displayMedium.copy(fontFamily = displayFontFamily),
        displaySmall = baseline.displaySmall.copy(fontFamily = displayFontFamily),
        headlineLarge = baseline.headlineLarge.copy(fontFamily = headlineFontFamily),
        headlineMedium = baseline.headlineMedium.copy(fontFamily = headlineFontFamily),
        headlineSmall = baseline.headlineSmall.copy(fontFamily = headlineFontFamily),
        titleLarge = baseline.titleLarge.copy(fontFamily = titleFontFamily),
        titleMedium = baseline.titleMedium.copy(fontFamily = titleFontFamily),
        titleSmall = baseline.titleSmall.copy(fontFamily = titleFontFamily),
        bodyLarge = baseline.bodyLarge.copy(fontFamily = bodyFontFamily),
        bodyMedium = baseline.bodyMedium.copy(fontFamily = bodyFontFamily),
        bodySmall = baseline.bodySmall.copy(fontFamily = bodyFontFamily),
        labelLarge = baseline.labelLarge.copy(fontFamily = labelFontFamily),
        labelMedium = baseline.labelMedium.copy(fontFamily = labelFontFamily),
        labelSmall = baseline.labelSmall.copy(fontFamily = labelFontFamily),
    )

