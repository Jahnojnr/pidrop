package com.pidrop.app.ui.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pidrop.app.domain.FoundationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class WelcomeViewModel @Inject constructor(repository: FoundationRepository) : ViewModel() {
    // A build capability, not an assertion about live service availability.
    val accountEntryImplemented: StateFlow<Boolean> = repository.accountEntryImplemented()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), false)
}
