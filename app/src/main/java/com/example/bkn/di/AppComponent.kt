package com.example.bkn.di


import com.example.bkn.di.modules.DatabaseModule
import com.example.bkn.di.modules.DomainModule
import com.example.bkn.di.modules.RemoteModule
import com.example.bkn.viewmodel.HomeFragmentViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    //Внедряем все модули, нужные для этого компонента
    modules = [
        RemoteModule::class,
        DatabaseModule::class,
        DomainModule::class
    ]
)
interface AppComponent {
    //метод для того, чтобы появилась внедрять зависимости в HomeFragmentViewModel
    fun inject(homeFragmentViewModel: HomeFragmentViewModel)
}