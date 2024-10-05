package uz.frodo.kitoblaruzb_eng.screens.book_info

import org.orbitmvi.orbit.ContainerHost

interface BookInfoContract {

    interface ViewModel : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    data class UIState(
        val isLoading: Boolean = false,
    )

    sealed interface SideEffect {
        data class Message(val message: String) : SideEffect
    }

    interface Direction {
        suspend fun moveToReadScreen()
    }

    interface Intent {
        object Start : Intent
    }
}