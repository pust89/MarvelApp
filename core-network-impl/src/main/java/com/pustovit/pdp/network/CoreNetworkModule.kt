package com.pustovit.pdp.network

import androidx.multidex.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class CoreNetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi).withNullSerialization())
            .baseUrl("https://gateway.marvel.com/")
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        interceptor: Interceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            readTimeout(timeout = 60L, unit = TimeUnit.SECONDS)
            connectTimeout(timeout = 60L, unit = TimeUnit.SECONDS)
            writeTimeout(timeout = 60L, unit = TimeUnit.SECONDS)
            addInterceptor(interceptor)
            addInterceptor(httpLoggingInterceptor)
        }.build()
    }

    @Provides
    @Singleton
    fun provideQueryHelper(): QueryHelper {
        return QueryHelper()
    }

    @Provides
    @Singleton
    fun provideInterceptor(queryHelper: QueryHelper): Interceptor {
        return Interceptor { chain ->
            val originalRequest: Request = chain.request()

            val params = queryHelper.getTsApiKeyMd5()

            val url = originalRequest.url
            val urlWithApiKey = url.newBuilder()
                .addQueryParameter("ts", params.first)
                .addQueryParameter("apikey", params.second)
                .addQueryParameter("hash", params.third)
                .build()

            val request: Request = originalRequest.newBuilder()
                .url(urlWithApiKey)
                .build()
            chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }
}