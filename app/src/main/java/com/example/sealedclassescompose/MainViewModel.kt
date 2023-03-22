package com.example.sealedclassescompose

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: Repository = Repository()
): ViewModel(){

    private val TAG = "TAGOWE"
    private val _uiState = mutableStateOf(UiState())
    val uiState: State<UiState> = _uiState

    init {
        Log.d(TAG, "getData: Init!")
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            _uiState.value= UiState(isLoading = true)
            when(val data = repository.getData()){
                is Resource.Success -> {
                    _uiState.value = UiState(items = data.data ?: listOf())
                }
                is Resource.Error -> {
                    _uiState.value = UiState(error = UiState.Error.NetworkError)
                }
            }
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val error: Error? = null,
        val items: List<Person> = listOf()
    ){
        sealed class Error{
            object NetworkError: Error()
            object OtherError: Error()
            object InputError: Error()
        }
    }
}
