package com.app.syncstudyapp.domain.api

import com.app.syncstudyapp.data.NetworkData
import retrofit2.Response
import retrofit2.http.GET

interface NetworkDataApi {

    @GET("test")
    suspend fun getNetworkData(): Response<NetworkData>
} // End of NetworkDataApi interface