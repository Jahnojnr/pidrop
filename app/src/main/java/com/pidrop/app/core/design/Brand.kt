package com.pidrop.app.core.design

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pidrop.app.R

/** Provisional horizontal lockup, with a decorative mark and no tagline. */
@Composable
fun PidropBrand(modifier: Modifier = Modifier) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(PidropSpacing.sm)) {
        Image(painter = painterResource(R.drawable.pidrop_mark), contentDescription = null, modifier = Modifier.size(40.dp))
        Text(text = stringResource(R.string.app_name), style = MaterialTheme.typography.titleLarge, color = PidropCharcoal)
    }
}
