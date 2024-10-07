package uz.frodo.kitoblaruzb_eng.screens.tabs.settings

import org.orbitmvi.orbit.ContainerHost

interface SettingsContract {

    interface ViewModel : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    data class UIState(
        val isDarkMode: Boolean = false,
    )

    sealed interface SideEffect {
        data class Message(val message: String) : SideEffect
    }

    interface Direction {
//        suspend fun moveToWelcome()
//        suspend fun moveToMain()
    }

    interface Intent {
        data class SwitchClick(val isDarkMode: Boolean) : Intent
    }
}

