package com.pidrop.app.core.design

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pidrop.app.R

/** The supplied horizontal PIDROP logo; no tagline is included in the lockup. */
@Composable
fun PidropBrand(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.pidrop_logo),
        contentDescription = stringResource(R.string.app_name),
        modifier = modifier.widthIn(max = 168.dp).aspectRatio(1400f / 292f),
    )
}
