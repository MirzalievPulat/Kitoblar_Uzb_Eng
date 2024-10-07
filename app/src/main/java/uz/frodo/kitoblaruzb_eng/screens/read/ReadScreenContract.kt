package uz.frodo.kitoblaruzb_eng.screens.read

import org.orbitmvi.orbit.ContainerHost

interface ReadScreenContract {

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
        suspend fun moveBack()
    }

    interface Intent {
        object BackClick : Intent
    }
}

