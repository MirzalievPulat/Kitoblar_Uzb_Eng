package uz.polat.kitoblaruzb_eng.screens.read

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
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
