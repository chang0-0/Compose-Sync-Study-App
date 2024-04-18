package com.app.syncstudyapp.di

import com.app.syncstudyapp.domain.Repository.NetworkDataRepository
import com.app.syncstudyapp.domain.api.NetworkDataApi
import com.app.syncstudyapp.domain.datasource.NetworkDataSource
import com.app.syncstudyapp.domain.datasource.NetworkDataSourceImpl
import com.app.syncstudyapp.util.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val httpClient = OkHttpClient().newBuilder().apply {
            addInterceptor(httpLoggingInterceptor)
        }

        httpClient.apply {
            readTimeout(60, TimeUnit.SECONDS)
        }

        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()

        val rxAdapter = RxJava3CallAdapterFactory.create()

        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(rxAdapter)
            .build()
    } // End of providesRetrofit()

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): NetworkDataApi {
        return retrofit.create(NetworkDataApi::class.java)
    } // End of providesApiService()

    @Provides
    @Singleton
    fun providesNetworkDataSource(networkDataApi: NetworkDataApi): NetworkDataSource {
        return NetworkDataSourceImpl(networkDataApi)
    } // End of providesNetworkDataSource

    @Provides
    @Singleton
    fun providesNetworkDataRepository(networkDataSource: NetworkDataSource): NetworkDataRepository {
        return NetworkDataRepository(networkDataSource)
    } // End of providesNetworkDataRepository()

} // End of AppModule class
