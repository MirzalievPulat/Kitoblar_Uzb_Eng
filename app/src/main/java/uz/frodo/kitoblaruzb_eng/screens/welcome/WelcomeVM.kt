package uz.frodo.kitoblaruzb_eng.screens.welcome

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class WelcomeVM @Inject constructor(
    private val directions: WelcomeContract.Direction,
) : ViewModel(), WelcomeContract.ViewModel {

    init {

    }

    override val container = container<WelcomeContract.UIState, WelcomeContract.SideEffect>(WelcomeContract.UIState())

    override fun onEventDispatcher(intent: WelcomeContract.Intent) = intent {
        when(intent){
            WelcomeContract.Intent.Next->{
                directions.moveToMain()
            }
        }
    }


}
