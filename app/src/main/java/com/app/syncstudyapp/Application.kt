package com.app.syncstudyapp

import android.app.Application
import com.app.syncstudyapp.util.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.hilt.android.HiltAndroidApp
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@HiltAndroidApp
class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        initRetrofit(AppInterceptor())
    } // End of onCreate()

    private fun initMoshi() {

    } // End of initMoshi()

    private fun initRetrofit(interceptor: AppInterceptor) {
        val logging = HttpLoggingInterceptor()
        val rxAdapter = RxJava3CallAdapterFactory.create()

        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS).writeTimeout(15, TimeUnit.SECONDS)
            .addNetworkInterceptor(logging)
            .addInterceptor(logging.setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(1, TimeUnit.MINUTES)
            .build()

        val gson: Gson = GsonBuilder()
            .setDateFormat("YYYY-MM-dd HH:mm:ss").create()

        retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(rxAdapter)
            .client(okHttpClient)
            .build()

    } // End of initRetrofit()

    inner class AppInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
            val newRequest = request().newBuilder().build()
            proceed(newRequest)
        }
    } // End of AppInterceptor inner class


    companion object {
        lateinit var retrofit: Retrofit
        lateinit var headerRetrofit: Retrofit
        const val TAG = "Application_창영"
    }
} // End of Application class