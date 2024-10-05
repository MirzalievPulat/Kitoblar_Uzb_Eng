package uz.frodo.kitoblaruzb_eng.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.frodo.kitoblaruzb_eng.utils.navigation.AppNavigator
import uz.frodo.kitoblaruzb_eng.utils.navigation.NavigationDispatcher
import uz.frodo.kitoblaruzb_eng.utils.navigation.NavigationHandler
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface Navigation {

    @[Singleton Binds]
    fun bindsAppNavigator(nav:NavigationDispatcher):AppNavigator

    @[Singleton Binds]
    fun bindsNavigationHandler(nav:NavigationDispatcher):NavigationHandler


}