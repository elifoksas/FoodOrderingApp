package com.elifoksas.foodorderingapp.di

import android.content.Context
import com.elifoksas.foodorderingapp.data.datasource.FavoritesDataSource
import com.elifoksas.foodorderingapp.data.datasource.FoodDataSource
import com.elifoksas.foodorderingapp.data.datasource.UserDataSource
import com.elifoksas.foodorderingapp.data.repository.AuthRepository
import com.elifoksas.foodorderingapp.data.repository.FavoritesRepository
import com.elifoksas.foodorderingapp.data.repository.FoodRepository
import com.elifoksas.foodorderingapp.retrofit.ApiUtils
import com.elifoksas.foodorderingapp.retrofit.FoodsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideFoodDataSource(context: Context,fdao:FoodsDao) : FoodDataSource {
        return FoodDataSource(provideContext(context),fdao)
    }
    @Provides
    @Singleton
    fun provideFoodRepository(fds:FoodDataSource) : FoodRepository {
        return FoodRepository(fds)
    }

    @Provides
    @Singleton
    fun provideFoodsDao() : FoodsDao {
        return ApiUtils.getFoodsDao()
    }

    @Provides
    @Singleton
    fun provideUserDataSource() : UserDataSource {
        return UserDataSource()
    }
    @Provides
    @Singleton
    fun provideAuthRepository(uds:UserDataSource) : AuthRepository {
        return AuthRepository(uds)
    }

    @Provides
    @Singleton
    fun provideFavoritesRepository(fds: FavoritesDataSource) : FavoritesRepository {
        return FavoritesRepository(fds)
    }

    @Provides
    @Singleton
    fun provideFavoritesDataSource() : FavoritesDataSource {
        return FavoritesDataSource()
    }
    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

}