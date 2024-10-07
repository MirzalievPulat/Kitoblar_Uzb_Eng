package uz.frodo.kitoblaruzb_eng.screens.book_info

import android.health.connect.datatypes.units.Percentage
import org.orbitmvi.orbit.ContainerHost
import uz.frodo.kitoblaruzb_eng.model.Book

interface BookInfoContract {

    interface ViewModel : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    data class UIState(
        val isLoading: Boolean = false,
        val isDownloaded:Boolean = false,
        val percentage: Int = 0,
        val book: Book = Book()
    )

    sealed interface SideEffect {
        data class Message(val message: String) : SideEffect
    }

    interface Direction {
        suspend fun moveToReadScreen(filePath:String)
    }

    interface Intent {
        data class SetBook(val book: Book): Intent
        object ReadBook:Intent
        object DownloadBook:Intent
    }
}

