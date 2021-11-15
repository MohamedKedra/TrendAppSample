package com.kedra.trendappsample.di

import com.kedra.trendappsample.remote.RepoService
import com.kedra.trendappsample.remote.TrendingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val baseURL = "https://private-anon-e9514c3a54-githubtrendingapi.apiary-mock.com/"

    @Singleton
    @Provides
    fun provideInstance(): Retrofit {

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(baseURL)
            .build()
    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): RepoService {

        return retrofit.create(RepoService::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {


    @Provides
    @Singleton
    fun provideTrendingRepository(repoService: RepoService): TrendingRepository {
        return TrendingRepository(repoService)
    }
}