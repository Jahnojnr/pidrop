package com.pidrop.app.domain

import kotlinx.coroutines.flow.Flow

/** Describes capabilities included in this build; does not query a backend. */
interface FoundationRepository {
    fun accountEntryImplemented(): Flow<Boolean>
}
