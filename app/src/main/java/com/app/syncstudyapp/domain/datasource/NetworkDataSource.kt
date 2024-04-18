package com.app.syncstudyapp.domain.datasource

import com.app.syncstudyapp.data.NetworkData
import retrofit2.Response

interface NetworkDataSource {

    suspend fun getNetworkData(): Response<NetworkData>
} // End of NetworkDataSource