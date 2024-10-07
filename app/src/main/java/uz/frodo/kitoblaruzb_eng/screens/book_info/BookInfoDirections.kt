package uz.frodo.kitoblaruzb_eng.screens.book_info


import uz.frodo.kitoblaruzb_eng.screens.read.ReadScreen
import uz.frodo.kitoblaruzb_eng.utils.navigation.AppNavigator
import javax.inject.Inject

class BookInfoDirections @Inject constructor(private val appNavigator: AppNavigator) : BookInfoContract.Direction {
    override suspend fun moveToReadScreen(filePath:String) {
        appNavigator.navigateTo(ReadScreen(filePath))
    }
}