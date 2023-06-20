package ua.dimkas71.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import ua.dimkas71.data.repository.SummaryConsumingBySectorsRepository
import ua.dimkas71.data.repository.SummaryConsumingBySectorsRepositoryImpl
import ua.dimkas71.data.source.local.AppDatabase
import ua.dimkas71.data.source.local.LocalSummaryConsumingBySectorsDao
import ua.dimkas71.data.source.network.SummaryConsumingBySectorsRemoteDataSource
import ua.dimkas71.rest.CounterServiceApi
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ReadingsMetersModule {

    @Provides
    @Singleton
    fun providesCounterServiceApi(@ApplicationContext context: Context): CounterServiceApi {
        return CounterServiceApi.service(context)
    }

    @Provides
    @Singleton
    fun localSummaryConsumingBySectorsDao(db: AppDatabase): LocalSummaryConsumingBySectorsDao = db.getLocalSummaryConsumingBySectorsDao()

    @Provides
    @Singleton
    fun providesDataBase(@ApplicationContext context: Context): AppDatabase = AppDatabase.getDatabase(context)

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        api: CounterServiceApi
    ) = SummaryConsumingBySectorsRemoteDataSource(api)

}