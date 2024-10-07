package uz.frodo.kitoblaruzb_eng.screens.splash


import uz.frodo.kitoblaruzb_eng.screens.main.MainScreen
import uz.frodo.kitoblaruzb_eng.screens.welcome.WelcomeScreen
import uz.frodo.kitoblaruzb_eng.utils.navigation.AppNavigator
import javax.inject.Inject

class SplashDirections @Inject constructor(private val appNavigator: AppNavigator) : SplashContract.Direction {
    override suspend fun moveToWelcome() {
        appNavigator.replace(WelcomeScreen())
    }

    override suspend fun moveToMain() {
        appNavigator.navigateTo(MainScreen())
    }

}