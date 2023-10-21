package com.elifoksas.foodorderingapp.ui.viewmodel

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
class UserViewModel @Inject constructor(var arepo : AuthRepository): ViewModel() {
    var logOutCheck = MutableLiveData<Boolean>()

    fun logOut(){
        CoroutineScope(Dispatchers.Main).launch {
            arepo.logOut()
            delay(2000)
            logOutCheck.value = arepo.isValid()
        }
    }
}