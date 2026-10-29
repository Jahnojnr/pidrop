package com.pidrop.app.ui.role

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
import com.pidrop.app.core.design.PidropRoleButton
import com.pidrop.app.core.design.PidropSpacing

@Composable
fun RoleSelectionScreen(onBack: () -> Unit, onCustomer: () -> Unit, onRider: () -> Unit) {
    PidropPage {
        PidropBrand()
        Spacer(Modifier.height(PidropSpacing.xl))
        PidropBackButton(onClick = onBack)
        Spacer(Modifier.height(PidropSpacing.xl))
        Text(
            text = stringResource(R.string.role_title),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.semantics { heading() },
        )
        Spacer(Modifier.height(PidropSpacing.sm))
        Text(
            text = stringResource(R.string.role_intro),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(Modifier.height(PidropSpacing.xxl))
        PidropRoleButton(
            label = stringResource(R.string.role_customer),
            description = stringResource(R.string.role_customer_description),
            icon = R.drawable.ic_package_outline,
            onClick = onCustomer,
        )
        Spacer(Modifier.height(PidropSpacing.md))
        PidropRoleButton(
            label = stringResource(R.string.role_rider),
            description = stringResource(R.string.role_rider_description),
            icon = R.drawable.ic_rider_outline,
            onClick = onRider,
        )
    }
}
