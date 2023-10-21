package com.elifoksas.foodorderingapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elifoksas.foodorderingapp.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(var arepo : AuthRepository): ViewModel(){

    var loginCheck = MutableLiveData<Boolean>()



    fun login(email : String, password : String){
        CoroutineScope(Dispatchers.Main).launch {
            Log.d("Girdi2","girdi2")
            if (email.isNullOrEmpty()){
                loginCheck.value =false
                return@launch
            }
            if (password.isNullOrEmpty()){
                loginCheck.value = false
                return@launch
            }
            arepo.login(email,password)
            delay(2000)
            loginCheck.value = arepo.isValidLogin()
            Log.d("Login",arepo.isValidLogin().toString())

        }
    }



}