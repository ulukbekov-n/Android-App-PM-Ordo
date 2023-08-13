package com.example.pmordo.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pmordo.api.LoginResponse
import com.example.pmordo.api.MainApi
import com.example.pmordo.api.RetrofitInstance
import com.example.pmordo.api.TokenObtainPairRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response


class LoginViewModel : ViewModel() {

    private val apiConsumer: MainApi = RetrofitInstance.apiConsumer

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> get() = _loginSuccess

    fun login(email: String, password: String) {
        val request = TokenObtainPairRequest(email, password)
        viewModelScope.launch {
            try {
                val response: Response<LoginResponse> = withContext(Dispatchers.IO) {
                    apiConsumer.login(request)
                }
                if (response.isSuccessful) {
                    _loginSuccess.value = true
                } else {
                    // Login failed
                    _loginSuccess.value = false
                }
            } catch (e: Exception) {
                _loginSuccess.value = false
            }
        }
    }
}
