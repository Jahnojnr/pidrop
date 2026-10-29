package com.pidrop.app.ui.placeholder

import androidx.annotation.StringRes
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

/** Honest destinations until role-specific account entry is implemented. */
@Composable
fun RolePlaceholderScreen(@StringRes title: Int, @StringRes description: Int, onBack: () -> Unit) {
    PidropPage {
        PidropBrand()
        Spacer(Modifier.height(PidropSpacing.xl))
        PidropBackButton(onClick = onBack)
        Spacer(Modifier.height(PidropSpacing.xl))
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.semantics { heading() },
        )
        Spacer(Modifier.height(PidropSpacing.lg))
        Text(
            text = stringResource(description),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(Modifier.height(PidropSpacing.md))
        Text(
            text = stringResource(R.string.placeholder_note),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}
