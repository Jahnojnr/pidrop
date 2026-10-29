package com.pidrop.app.core.design

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape

val PidropOrange = Color(0xFFFF6B00)
val PidropCharcoal = Color(0xFF171717)
val PidropSecondaryText = Color(0xFF707070)
val PidropNeutral = Color(0xFFF5F5F5)
val PidropBorder = Color(0xFFE5E7EB)

object PidropSpacing {
    val xs = 4.dp
    val sm = 8.dp
    val md = 12.dp
    val lg = 16.dp
    val xl = 24.dp
    val xxl = 32.dp
    val section = 48.dp
}

private val PidropColors = lightColorScheme(
    primary = PidropOrange,
    onPrimary = PidropCharcoal,
    background = Color.White,
    onBackground = PidropCharcoal,
    surface = Color.White,
    onSurface = PidropCharcoal,
    surfaceVariant = PidropNeutral,
    onSurfaceVariant = PidropSecondaryText,
    outline = PidropBorder,
)

private val PidropTypography = Typography(
    headlineLarge = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Bold, fontSize = 32.sp, lineHeight = 40.sp),
    headlineMedium = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Bold, fontSize = 24.sp, lineHeight = 32.sp),
    titleLarge = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.SemiBold, fontSize = 20.sp, lineHeight = 28.sp),
    bodyLarge = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Normal, fontSize = 16.sp, lineHeight = 24.sp),
    bodyMedium = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Normal, fontSize = 14.sp, lineHeight = 20.sp),
    labelLarge = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.SemiBold, fontSize = 14.sp, lineHeight = 20.sp),
)

private val PidropShapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
)

@Composable
fun PidropTheme(content: @Composable () -> Unit) {
    // A dark palette has not been reviewed; use the approved light palette in both modes.
    MaterialTheme(colorScheme = PidropColors, typography = PidropTypography, shapes = PidropShapes, content = content)
}
