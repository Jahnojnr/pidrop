package com.pidrop.app.ui.about

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import com.pidrop.app.R
import com.pidrop.app.core.design.PidropBackButton
import com.pidrop.app.core.design.PidropBrand
import com.pidrop.app.core.design.PidropPage
import com.pidrop.app.core.design.PidropSpacing

@Composable
fun AboutScreen(onBack: () -> Unit) {
    PidropPage {
        PidropBrand()
        Spacer(Modifier.height(PidropSpacing.xl))
        PidropBackButton(onClick = onBack)
        Spacer(Modifier.height(PidropSpacing.xl))
        Text(stringResource(R.string.about_title), style = MaterialTheme.typography.headlineMedium, modifier = Modifier.semantics { heading() })
        Spacer(Modifier.height(PidropSpacing.xl))
        Text(stringResource(R.string.about_intro), style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(PidropSpacing.md))
        Text(stringResource(R.string.about_body), style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(Modifier.height(PidropSpacing.xl))
        Text(stringResource(R.string.about_note), style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}
