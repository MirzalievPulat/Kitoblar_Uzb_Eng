package uz.frodo.kitoblaruzb_eng.screens.read

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import uz.frodo.kitoblaruzb_eng.repository.Repository
import uz.frodo.kitoblaruzb_eng.screens.splash.SplashContract
import javax.inject.Inject

@HiltViewModel
class ReadScreenVM @Inject constructor(
    private val directions: ReadScreenContract.Direction,
) : ViewModel(), ReadScreenContract.ViewModel {


    override val container = container<ReadScreenContract.UIState, ReadScreenContract.SideEffect>(ReadScreenContract.UIState())

    override fun onEventDispatcher(intent: ReadScreenContract.Intent) = intent {
        when(intent){
            ReadScreenContract.Intent.BackClick->{
                directions.moveBack()
            }
        }
    }


}
