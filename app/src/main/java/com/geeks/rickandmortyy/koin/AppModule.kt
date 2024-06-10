package com.geeks.rickandmortyy.koin

import com.geeks.rickandmortyy.data.api.ApiService
import com.geeks.rickandmortyy.data.repository.Repository
import com.geeks.rickandmortyy.ui.characters.CharacterViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {

    single {
        OkHttpClient.Builder()
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }
    single {
        Retrofit.Builder().baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()

    }
    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
    single {
        get<Retrofit>().create(ApiService::class.java)
    }
    single {
        Repository(get())
    }

    viewModel {
        CharacterViewModel(get())
    }
}