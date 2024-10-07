package uz.frodo.kitoblaruzb_eng.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.frodo.kitoblaruzb_eng.repository.Repository
import uz.frodo.kitoblaruzb_eng.repository.shared.LocalStorage
import javax.inject.Inject

@HiltViewModel
class SplashVM @Inject constructor(
    private val directions: SplashContract.Direction,
    private val repository: Repository,
    private val localStorage: LocalStorage
) : ViewModel(), SplashContract.ViewModel {

    override val container = container<SplashContract.UIState,
            SplashContract.SideEffect>(SplashContract.UIState().copy(isDarkTheme = localStorage.isDarkMode))

    init {
//        if(localStorage.isDarkMode){
//            intent { reduce { state.copy(isDarkTheme =  true) }}
//        }else{
//            intent { reduce { state.copy(isDarkTheme = false) } }
//        }
        viewModelScope.launch {
            delay(2000)
            directions.moveToWelcome()
        }
    }

    override fun onEventDispatcher(intent: SplashContract.Intent) = intent {

    }


}
