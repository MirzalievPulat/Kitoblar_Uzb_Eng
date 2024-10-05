package uz.frodo.kitoblaruzb_eng.screens.welcome

import org.orbitmvi.orbit.ContainerHost

interface WelcomeContract {

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
        suspend fun moveToMain()
    }

    interface Intent {
        object Next : Intent
    }
}