package com.pidrop.app.data

import com.pidrop.app.core.config.AppConfig
import com.pidrop.app.domain.FoundationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Singleton
class LocalFoundationRepository @Inject constructor() : FoundationRepository {
    override fun accountEntryImplemented(): Flow<Boolean> = flowOf(AppConfig.ACCOUNT_ENTRY_IMPLEMENTED)
}

@Module
@InstallIn(SingletonComponent::class)
abstract class FoundationModule {
    @Binds
    abstract fun bindFoundationRepository(implementation: LocalFoundationRepository): FoundationRepository
}
