package com.pidrop.app.ui.welcome

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pidrop.app.R
import com.pidrop.app.core.design.PidropBrand
import com.pidrop.app.core.design.PidropPage
import com.pidrop.app.core.design.PidropPrimaryButton
import com.pidrop.app.core.design.PidropSpacing

@Composable
fun WelcomeScreen(
    onGetStarted: () -> Unit,
    onAbout: () -> Unit,
    viewModel: WelcomeViewModel = hiltViewModel(),
) {
    val accountEntryImplemented by viewModel.accountEntryImplemented.collectAsStateWithLifecycle()
    PidropPage {
        PidropBrand()
        Spacer(Modifier.height(PidropSpacing.section))
        Text(
            text = stringResource(R.string.welcome_eyebrow),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(Modifier.height(PidropSpacing.md))
        Text(
            text = stringResource(R.string.welcome_title),
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.semantics { heading() },
        )
        Spacer(Modifier.height(PidropSpacing.lg))
        Text(
            text = stringResource(R.string.welcome_body),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(Modifier.height(PidropSpacing.xxl))
        PidropPrimaryButton(label = stringResource(R.string.get_started), onClick = onGetStarted)
        Spacer(Modifier.height(PidropSpacing.sm))
        TextButton(onClick = onAbout) {
            Text(stringResource(R.string.welcome_action), color = MaterialTheme.colorScheme.onSurface)
        }
        if (!accountEntryImplemented) {
            Spacer(Modifier.height(PidropSpacing.md))
            Text(
                text = stringResource(R.string.welcome_note),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}
