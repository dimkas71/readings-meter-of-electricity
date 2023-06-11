package ua.dimkas71.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.dimkas71.data.source.local.AppDatabase
import ua.dimkas71.data.source.local.LocalSummaryConsumingBySectorsDao
import ua.dimkas71.rest.CounterServiceApi

@Module
@InstallIn(SingletonComponent::class)
object ReadingsMetersModule {

    @Provides
    fun providesCounterServiceApi(context: Context): CounterServiceApi {
        return CounterServiceApi.service(context)
    }

    @Provides
    fun providesDatabaseSource(db: AppDatabase): LocalSummaryConsumingBySectorsDao = db.getLocalSummaryConsumingBySectorsDao()

    @Provides
    fun providesDataBase(context: Context): AppDatabase = AppDatabase.getDatabase(context)

}