package uz.frodo.kitoblaruzb_eng.screens.tabs.allbooks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.frodo.kitoblaruzb_eng.repository.Repository
import uz.frodo.kitoblaruzb_eng.utils.onFailure
import uz.frodo.kitoblaruzb_eng.utils.onSuccess
import javax.inject.Inject

@HiltViewModel
class AllBooksVM @Inject constructor(
    private val directions: AllBooksContract.Direction,
    private val repository: Repository
) : ViewModel(), AllBooksContract.ViewModel {

    init {
        repository.getBooks()
            .onEach {
                it.onSuccess {
                    intent { reduce { state.copy(allBooks = it) } }
                }
                it.onFailure {
                    intent { postSideEffect(AllBooksContract.SideEffect.Message(it.message!!)) }
                }
            }
            .launchIn(viewModelScope)
    }

    override val container = container<AllBooksContract.UIState, AllBooksContract.SideEffect>(AllBooksContract.UIState())

    override fun onEventDispatcher(intent: AllBooksContract.Intent) = intent {
        when(intent){
            is AllBooksContract.Intent.BookClick->{
                directions.moveToBookInfo(intent.book)
            }
            is AllBooksContract.Intent.CategoryClick->{
                repository.getCategories(intent.category)
                    .onSuccess { reduce { state.copy(categoryBook = it) } }
                    .onFailure { postSideEffect(AllBooksContract.SideEffect.Message(it.message!!)) }
                    .launchIn(viewModelScope)
            }
        }
    }


}
