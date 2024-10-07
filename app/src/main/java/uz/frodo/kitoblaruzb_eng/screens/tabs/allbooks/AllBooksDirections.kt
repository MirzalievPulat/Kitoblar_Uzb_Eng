package uz.frodo.kitoblaruzb_eng.screens.tabs.allbooks


import uz.frodo.kitoblaruzb_eng.model.Book
import uz.frodo.kitoblaruzb_eng.screens.book_info.BookInfoScreen
import uz.frodo.kitoblaruzb_eng.screens.main.MainScreen
import uz.frodo.kitoblaruzb_eng.screens.welcome.WelcomeContract
import uz.frodo.kitoblaruzb_eng.utils.navigation.AppNavigator
import javax.inject.Inject

class AllBooksDirections @Inject constructor(private val appNavigator: AppNavigator) : AllBooksContract.Direction {

    override suspend fun moveToBookInfo(book: Book) {
        appNavigator.navigateTo(BookInfoScreen(book))
    }

}