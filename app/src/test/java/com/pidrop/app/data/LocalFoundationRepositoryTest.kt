package com.pidrop.app.data

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertFalse
import org.junit.Test

class LocalFoundationRepositoryTest {
    @Test
    fun accountEntryIsNotClaimedAsImplemented() = runBlocking {
        assertFalse(LocalFoundationRepository().accountEntryImplemented().first())
    }
}
