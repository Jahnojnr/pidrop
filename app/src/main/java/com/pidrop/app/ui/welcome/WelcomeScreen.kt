package com.pidrop.app.ui.welcome

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pidrop.app.R
import com.pidrop.app.core.design.PidropBrand
import com.pidrop.app.core.design.PidropCharcoal
import com.pidrop.app.core.design.PidropNeutral
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
        HorizontalDivider(color = MaterialTheme.colorScheme.outline)
        Spacer(Modifier.height(PidropSpacing.lg))
        Text(
            text = stringResource(R.string.welcome_for_title),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.semantics { heading() },
        )
        Spacer(Modifier.height(PidropSpacing.md))
        AudienceRow(
            icon = R.drawable.ic_package_outline,
            title = stringResource(R.string.welcome_customer_title),
            description = stringResource(R.string.welcome_customer_description),
        )
        Spacer(Modifier.height(PidropSpacing.md))
        AudienceRow(
            icon = R.drawable.ic_rider_outline,
            title = stringResource(R.string.welcome_rider_title),
            description = stringResource(R.string.welcome_rider_description),
        )
        Spacer(Modifier.height(PidropSpacing.xxl))
        PidropPrimaryButton(label = stringResource(R.string.get_started), onClick = onGetStarted)
        Spacer(Modifier.height(PidropSpacing.sm))
        TextButton(onClick = onAbout) {
            Text(stringResource(R.string.welcome_action), color = MaterialTheme.colorScheme.onSurface)
        }
        if (!accountEntryImplemented) {
            Spacer(Modifier.height(PidropSpacing.sm))
            Text(
                text = stringResource(R.string.welcome_note),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@Composable
private fun AudienceRow(@DrawableRes icon: Int, title: String, description: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        Box(
            modifier = Modifier.size(40.dp).background(PidropNeutral, RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center,
        ) {
            Icon(painter = painterResource(icon), contentDescription = null, tint = PidropCharcoal, modifier = Modifier.size(22.dp))
        }
        Spacer(Modifier.width(PidropSpacing.md))
        Column(modifier = Modifier.weight(1f).padding(vertical = PidropSpacing.xs)) {
            Text(text = title, style = MaterialTheme.typography.titleMedium)
            Text(text = description, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
