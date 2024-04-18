package com.app.syncstudyapp.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.app.syncstudyapp.component.Loader
import com.app.syncstudyapp.data.NetworkData
import com.app.syncstudyapp.util.ResourceState

private const val TAG = "HomeScreen_창영"

@Composable
fun HomeScreen(
    navController: NavController,
    networkResponseViewModel: HomeResponseViewModel = hiltViewModel()
) {
    val networkDataResponse by
    networkResponseViewModel.getNetworkDataResponseStateFlow.collectAsState()

    Surface {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("홈 스크린")

            when (networkDataResponse) {
                is ResourceState.Loading -> {
                    Log.d(TAG, "NetworkData : Loading")

                    Loader()
                }

                is ResourceState.Success -> {

                }

                is ResourceState.Error -> {
                    Log.d(TAG, "NetworkData : Error")


                }
            }
        }
    }


    // ServiceLocator패턴을 사용 OR Hilt 사용
    // val repo = NetworkDataRepository().
    // val networkResponseViewModel: HomeResponseViewModel

//    LaunchedEffect(key1 = getNetworkDataResponseSharedFlowState.value) {
//
//        when (getNetworkDataResponseSharedFlowState.value) {
//            is NetworkResult.Success -> {
//                val data = getNetworkDataResponseSharedFlowState.value!!.data
//                networkResponseViewModel.setNetworkData(data as NetworkData)
//                // HomeScreenContent(data)
//            }
//
//            is NetworkResult.Loading -> {
//
//            }
//
//            else -> {
//
//            }
//
//        }
//    }

    // HomeScreenContent(NetworkData())
} // End of HomeScreen()

@Composable
fun HomeScreenContent(data: NetworkData? = null) {
    Surface {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("홈 스크린")
            Text(data!!.content);
        }
    }
} // End of HomeScreenContent()