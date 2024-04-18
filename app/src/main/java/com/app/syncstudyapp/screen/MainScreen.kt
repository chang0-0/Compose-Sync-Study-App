package com.app.syncstudyapp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.syncstudyapp.navigation.Screen
import com.app.syncstudyapp.ui.theme.SyncStudyAppTheme

@Composable
fun MainScreen(navController: NavController) {/*
        실행하기 전 화면 -> 데이터 호출
     */


    Surface(Modifier.fillMaxSize()) {

        Column(
            Modifier.fillMaxSize().background(Color(0xFFeb7a34)), // 전체 사이즈
        ) {
            CenterAlignedAppBar()
            Box(
                Modifier.fillMaxWidth().background(Color(0xFFeb1334)),
                contentAlignment = Alignment.Center
            ) {
                Text("Main Screen", fontSize = 30.sp)
            }


            Box(
                Modifier.fillMaxSize().background(Color(0xFF4287f5)),
            ) {
                Text(
                    text = "sdf",
                    fontSize = 40.sp,
                    modifier = Modifier.align(Alignment.Center).padding(140.dp)
                )
                Column(
                    modifier = Modifier.align(Alignment.Center)
                        .background(
                            Color(0xFF8cab9c)
                        ),
                    Arrangement.Center,
                    // Alignment.CenterHorizontally // 컬럼 내부 전체 중앙으로 나는 텍스트만 정렬하고 싶으니까 주석

                ) {
                    Text("테스트 입니다.", modifier = Modifier.align(Alignment.CenterHorizontally))
                    Spacer(modifier = Modifier.height(40.dp))
                    Button(onClick = { navController.navigate(Screen.Home.route) }) {
                        Text(text = "Go to Home Screen")
                    }
                }


            }
        }
    }

} // End of MainScreen()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterAlignedAppBar() {

    TopAppBar(title = {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("My App")
        }
    },

        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(Icons.Filled.Menu, contentDescription = "Navigation Icon")
            }
        },

        actions = {
            IconButton(onClick = {}) {
                Icon(Icons.Filled.Favorite, contentDescription = "")
            }
        })

} // End of RecyclerViewItem()

@Preview
@Composable
fun MainScreenPreview() {
    SyncStudyAppTheme {
        MainScreen(rememberNavController())
    }
} // End of MainScreenPreview()