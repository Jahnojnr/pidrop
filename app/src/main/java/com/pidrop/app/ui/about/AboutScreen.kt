package com.pidrop.app.ui.about

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.pidrop.app.R
import com.pidrop.app.core.design.PidropBrand
import com.pidrop.app.core.design.PidropSpacing

@Composable
fun AboutScreen(onBack: () -> Unit) {
    Scaffold(containerColor = MaterialTheme.colorScheme.background) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding), contentAlignment = Alignment.TopCenter) {
            Column(modifier = Modifier.widthIn(max = 560.dp).fillMaxWidth().verticalScroll(rememberScrollState()).padding(horizontal = PidropSpacing.xl, vertical = PidropSpacing.xxl)) {
                PidropBrand()
                Spacer(Modifier.height(PidropSpacing.xxl))
                TextButton(onClick = onBack) { Text(stringResource(R.string.back), color = MaterialTheme.colorScheme.onSurface) }
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
    }
}
