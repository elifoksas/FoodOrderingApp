package com.elifoksas.foodorderingapp.data.datasource

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserDataSource {
    companion object{
        var isValid : Boolean = false

    }
    suspend fun login(email : String, password : String) = withContext(Dispatchers.IO){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    UserDataSource.isValid = true

                    // Sign in success, update UI with the signed-in user's information
                    Log.d("SignUp", "createUserWithEmail:success")

                } else {
                    isValid = false

                    // If sign in fails, display a message to the user.
                    Log.w("SignUp", "createUserWithEmail:failure", task.exception)
                }
            }
    }
    suspend fun isValid() : Boolean = withContext(Dispatchers.IO){
       val user = FirebaseAuth.getInstance().currentUser
        return@withContext user != null
    }
    suspend fun isValidLogin() : Boolean = withContext(Dispatchers.IO){
        return@withContext isValid
    }
    suspend fun logOut() = withContext(Dispatchers.IO){
        FirebaseAuth.getInstance().signOut()
    }
    suspend fun signUp(email : String, password : String) = withContext(Dispatchers.IO){

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("SignUp", "createUserWithEmail:success")
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("SignUp", "createUserWithEmail:failure", task.exception)
                }
            }

    }
}