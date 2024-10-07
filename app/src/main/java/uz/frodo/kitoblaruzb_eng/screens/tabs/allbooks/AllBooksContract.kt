package uz.frodo.kitoblaruzb_eng.screens.tabs.allbooks

import org.orbitmvi.orbit.ContainerHost
import uz.frodo.kitoblaruzb_eng.model.Book

interface AllBooksContract {

    interface ViewModel : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    data class UIState(
        val isLoading: Boolean = false,
        val allBooks:List<Book> = listOf(Book(rating = 5f),Book(rating = 5f),Book(rating = 5f),Book(),Book(),Book()),
        val categoryBook:List<Book> = listOf(Book(),Book(),Book(),Book(),Book(),Book())
    )

    sealed interface SideEffect {
        data class Message(val message: String) : SideEffect
    }

    interface Direction {
        suspend fun moveToBookInfo(book: Book)
    }

    interface Intent {
        data class BookClick(val book: Book) : Intent
        data class CategoryClick(val category:String):Intent
    }
}