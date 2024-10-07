package uz.frodo.kitoblaruzb_eng.screens.welcome

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.frodo.kitoblaruzb_eng.repository.shared.LocalStorage
import javax.inject.Inject

@HiltViewModel
class WelcomeVM @Inject constructor(
    private val directions: WelcomeContract.Direction,
    private val localStorage: LocalStorage
) : ViewModel(), WelcomeContract.ViewModel {

    override val container = container<WelcomeContract.UIState,
            WelcomeContract.SideEffect>(WelcomeContract.UIState().copy(isDarkTheme = localStorage.isDarkMode))


    override fun onEventDispatcher(intent: WelcomeContract.Intent) = intent {
        when(intent){
            WelcomeContract.Intent.Next->{
                directions.moveToMain()
            }
        }
    }


}
