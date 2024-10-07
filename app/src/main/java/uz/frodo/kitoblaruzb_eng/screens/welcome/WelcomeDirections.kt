package uz.frodo.kitoblaruzb_eng.screens.welcome


import uz.frodo.kitoblaruzb_eng.screens.main.MainScreen
import uz.frodo.kitoblaruzb_eng.utils.navigation.AppNavigator
import javax.inject.Inject

class WelcomeDirections @Inject constructor(private val appNavigator: AppNavigator) : WelcomeContract.Direction {

    override suspend fun moveToMain() {
        appNavigator.replace(MainScreen())
    }

}