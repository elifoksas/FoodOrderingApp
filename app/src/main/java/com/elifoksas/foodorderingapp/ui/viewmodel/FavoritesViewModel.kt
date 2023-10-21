package com.elifoksas.foodorderingapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elifoksas.foodorderingapp.data.entity.Foods
import com.elifoksas.foodorderingapp.data.repository.FavoritesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class FavoritesViewModel @Inject constructor(var frepo : FavoritesRepository): ViewModel() {


    var favoriteList = MutableLiveData<List<Foods>>()
    fun getFavorites(){
        CoroutineScope(Dispatchers.Main).launch {
            frepo.getFavorites()
            delay(1000)
            favoriteList.value = frepo.getFavoritesList()
        }
    }
}