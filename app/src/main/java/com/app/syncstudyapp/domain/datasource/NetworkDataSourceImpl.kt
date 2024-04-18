package com.app.syncstudyapp.domain.datasource

import com.app.syncstudyapp.data.NetworkData
import com.app.syncstudyapp.domain.api.NetworkDataApi
import retrofit2.Response
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(
    private val networkDataApi: NetworkDataApi
) : NetworkDataSource {

    override suspend fun getNetworkData(): Response<NetworkData> {
        return networkDataApi.getNetworkData()
    }

} // End of NetworkDataSourceImpl class