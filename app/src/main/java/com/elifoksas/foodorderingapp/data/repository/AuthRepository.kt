package com.elifoksas.foodorderingapp.data.repository

import com.elifoksas.foodorderingapp.data.datasource.UserDataSource

class AuthRepository(var uds: UserDataSource) {
    suspend fun login(email : String, password : String) = uds.login(email,password)
    suspend fun signUp(email : String, password : String) = uds.signUp(email,password)
    suspend fun isValid() : Boolean = uds.isValid()
    suspend fun isValidLogin() : Boolean = uds.isValidLogin()
    suspend fun logOut() = uds.logOut()




}