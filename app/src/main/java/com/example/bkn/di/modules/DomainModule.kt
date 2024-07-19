package com.example.bkn.di.modules

import com.example.bkn.data.MainRepository
import com.example.bkn.data.TmdbApi
import com.example.bkn.domain.Interactor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Singleton
    @Provides
    fun provideInteractor(repository: MainRepository, tmdbApi: TmdbApi) =
        Interactor(response = repository, retrofitService = tmdbApi)

}