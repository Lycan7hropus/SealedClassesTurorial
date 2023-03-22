package com.example.sealedclassescompose

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.sealedclassescompose.ui.theme.SealedClassesComposeTheme

class MainActivity : ComponentActivity() {

    private val TAG = "TAGOWE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MainViewModel = MainViewModel()
            val uiState = viewModel.uiState.value

            Log.d(TAG, "getData: SetContent!")
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ){
                LazyColumn(modifier = Modifier.fillMaxSize()){
                    items(uiState.items.size){
                        Text(
                            text = uiState.items[it].name,
                            modifier = Modifier.padding(15.dp)
                        )

                    }
                }

                if(uiState.isLoading){
                    CircularProgressIndicator()
                }
                when(uiState.error){
                    is MainViewModel.UiState.Error.NetworkError->{
                        Text(text = stringResource(id = R.string.error_network), color = Color.Red)
                    }
                    is MainViewModel.UiState.Error.InputError->{
                        Text(text = stringResource(id = R.string.error_input_empty), color = Color.Yellow)
                    }
                    is MainViewModel.UiState.Error.OtherError->{
                        Text(text = stringResource(id = R.string.other_error), color = Color.Yellow)
                    }

                }
            }



        }
    }
}

