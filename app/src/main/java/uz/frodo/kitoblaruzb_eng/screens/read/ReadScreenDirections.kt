package uz.frodo.kitoblaruzb_eng.screens.read


import uz.frodo.kitoblaruzb_eng.screens.main.MainScreen
import uz.frodo.kitoblaruzb_eng.screens.splash.SplashContract
import uz.frodo.kitoblaruzb_eng.screens.welcome.WelcomeScreen
import uz.frodo.kitoblaruzb_eng.utils.navigation.AppNavigator
import javax.inject.Inject

class ReadScreenDirections @Inject constructor(private val appNavigator: AppNavigator) : ReadScreenContract.Direction {
    override suspend fun moveBack() {
        appNavigator.back()
    }
}