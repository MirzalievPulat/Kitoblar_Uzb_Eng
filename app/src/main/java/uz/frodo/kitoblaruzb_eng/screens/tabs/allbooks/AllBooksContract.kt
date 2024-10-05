package uz.frodo.kitoblaruzb_eng.screens.tabs.allbooks

import org.orbitmvi.orbit.ContainerHost
import uz.frodo.kitoblaruzb_eng.model.Book

interface AllBooksContract {

    interface ViewModel : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    data class UIState(
        val isLoading: Boolean = false,
        val allBooks:List<Book> = emptyList(),
        val categoryBook:List<Book> = emptyList()
    )

    sealed interface SideEffect {
        data class Message(val message: String) : SideEffect
    }

    interface Direction {
        suspend fun moveToBookInfo()
    }

    interface Intent {
        object Start : Intent
        data class CategoryClick(val category:String):Intent
    }
}