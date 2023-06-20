package ua.dimkas71.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import ua.dimkas71.data.repository.SummaryConsumingBySectorsRepository
import ua.dimkas71.data.repository.SummaryConsumingBySectorsRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepo(impl: SummaryConsumingBySectorsRepositoryImpl): SummaryConsumingBySectorsRepository
}