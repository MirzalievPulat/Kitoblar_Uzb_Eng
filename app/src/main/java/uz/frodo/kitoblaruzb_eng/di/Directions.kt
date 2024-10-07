package uz.frodo.kitoblaruzb_eng.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import uz.frodo.kitoblaruzb_eng.screens.book_info.BookInfoContract
import uz.frodo.kitoblaruzb_eng.screens.book_info.BookInfoDirections
import uz.frodo.kitoblaruzb_eng.screens.read.ReadScreenContract
import uz.frodo.kitoblaruzb_eng.screens.read.ReadScreenDirections
import uz.frodo.kitoblaruzb_eng.screens.splash.SplashContract
import uz.frodo.kitoblaruzb_eng.screens.splash.SplashDirections
import uz.frodo.kitoblaruzb_eng.screens.tabs.allbooks.AllBooksContract
import uz.frodo.kitoblaruzb_eng.screens.tabs.allbooks.AllBooksDirections
import uz.frodo.kitoblaruzb_eng.screens.welcome.WelcomeContract
import uz.frodo.kitoblaruzb_eng.screens.welcome.WelcomeDirections

@Module
@InstallIn(ViewModelComponent::class)
interface Directions {
    @[ViewModelScoped Binds]
    fun bindsSplashDirection(directions: SplashDirections):SplashContract.Direction

    @[ViewModelScoped Binds]
    fun bindsWelcomeDirection(directions: WelcomeDirections): WelcomeContract.Direction

    @[ViewModelScoped Binds]
    fun bindsAllBooksDirection(direction: AllBooksDirections):AllBooksContract.Direction

    @[ViewModelScoped Binds]
    fun bindsBookInfoDirection(direction: BookInfoDirections):BookInfoContract.Direction

    @[ViewModelScoped Binds]
    fun bindsReadScreenDirection(direction: ReadScreenDirections): ReadScreenContract.Direction





}