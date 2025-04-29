package uz.polat.kitoblaruzb_eng.screens.read


import uz.polat.kitoblaruzb_eng.utils.navigation.AppNavigator
import javax.inject.Inject

class ReadScreenDirections @Inject constructor(private val appNavigator: AppNavigator) : ReadScreenContract.Direction {
    override suspend fun moveBack() {
        appNavigator.back()
    }
}