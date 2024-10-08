package uz.frodo.kitoblaruzb_eng.screens.tabs.allbooks

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.frodo.kitoblaruzb_eng.R
import uz.frodo.kitoblaruzb_eng.repository.Repository
import uz.frodo.kitoblaruzb_eng.utils.NetworkStatusValidator
import uz.frodo.kitoblaruzb_eng.utils.onFailure
import uz.frodo.kitoblaruzb_eng.utils.onSuccess
import java.util.Locale.Category
import javax.inject.Inject

@HiltViewModel
class AllBooksVM @Inject constructor(
    private val directions: AllBooksContract.Direction,
    private val repository: Repository,
    private val networkStatusValidator: NetworkStatusValidator,
    @ApplicationContext private val context: Context
) : ViewModel(), AllBooksContract.ViewModel {

    override val container = container<AllBooksContract.UIState, AllBooksContract.SideEffect>(AllBooksContract.UIState())

    init {
        if (networkStatusValidator.isNetworkEnabled){
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
        }else{
            intent { postSideEffect(AllBooksContract.SideEffect.Message(context.getString(R.string.txt_no_internet))) }
        }
    }

    override fun onEventDispatcher(intent: AllBooksContract.Intent) = intent {
        when(intent){
            is AllBooksContract.Intent.BookClick->{
                directions.moveToBookInfo(intent.book)
            }
            is AllBooksContract.Intent.CategoryClick->{
                getByCategory(intent.category)
            }
        }
    }

    private fun getByCategory(category: String) = intent{

        if (category == "All"){

            if (networkStatusValidator.isNetworkEnabled){
                repository.getBooks()
                    .onStart { reduce { state.copy(isRefreshing = true) } }
                    .onEach {
                        it.onSuccess {
                            intent { reduce { state.copy(allBooks = it) } }
                            intent { reduce { state.copy(isRefreshing = false)}}
                            Log.d("TAG", "getByCategory: all:$it")
                        }
                        it.onFailure {
                            intent { postSideEffect(AllBooksContract.SideEffect.Message(it.message!!)) }
                            intent { reduce { state.copy(isRefreshing = false)}}
                        }
                    }

                    .launchIn(viewModelScope)
            }else{
                intent { postSideEffect(AllBooksContract.SideEffect.Message(context.getString(R.string.txt_no_internet))) }
            }
            return@intent

        }else{

            if (networkStatusValidator.isNetworkEnabled){
                repository.getCategories(category)
                    .onStart { reduce { state.copy(isRefreshing = true) } }
                    .onSuccess {
                        reduce { state.copy(categoryBook = it) }
                        intent { reduce { state.copy(isRefreshing = false)}}
                        Log.d("TAG", "getByCategory: $category $it")
                    }
                    .onFailure {
                        postSideEffect(AllBooksContract.SideEffect.Message(it.message!!))
                        intent { reduce { state.copy(isRefreshing = false)}}
                    }
                    .launchIn(viewModelScope)
            }else{
                postSideEffect(AllBooksContract.SideEffect.Message(context.getString(R.string.txt_no_internet)))
            }
        }


    }
}
