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
class SignUpViewModel@Inject constructor(var arepo : AuthRepository): ViewModel() {

    var signUpCheck = MutableLiveData<Boolean>()

    fun signUp(email : String, password : String){
        CoroutineScope(Dispatchers.Main).launch {
            if (email.isNullOrEmpty()){
                signUpCheck.value =false
                return@launch
            }
            if (password.isNullOrEmpty()){
                signUpCheck.value = false
                return@launch
            }
            arepo.signUp(email,password)
            delay(2000)
            signUpCheck.value = arepo.isValid()
        }
    }
    suspend fun isValid() : Boolean{
        return arepo.isValid()
    }
}