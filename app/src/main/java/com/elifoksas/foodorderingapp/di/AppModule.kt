package com.elifoksas.foodorderingapp.di

import com.elifoksas.foodorderingapp.data.datasource.FoodDataSource
import com.elifoksas.foodorderingapp.data.repository.FoodRepository
import com.elifoksas.foodorderingapp.retrofit.ApiUtils
import com.elifoksas.foodorderingapp.retrofit.FoodsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideFoodDataSource(fdao:FoodsDao) : FoodDataSource {
        return FoodDataSource(fdao)
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
}