package uz.frodo.kitoblaruzb_eng.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import uz.frodo.kitoblaruzb_eng.repository.Repository
import javax.inject.Inject

@HiltViewModel
class SplashVM @Inject constructor(
    private val directions: SplashContract.Direction,
    private val repository: Repository
) : ViewModel(), SplashContract.ViewModel {

    init {
        viewModelScope.launch {
            delay(2000)
            directions.moveToWelcome()
        }
    }

    override val container = container<SplashContract.UIState, SplashContract.SideEffect>(SplashContract.UIState())

    override fun onEventDispatcher(intent: SplashContract.Intent) = intent {

    }


}
