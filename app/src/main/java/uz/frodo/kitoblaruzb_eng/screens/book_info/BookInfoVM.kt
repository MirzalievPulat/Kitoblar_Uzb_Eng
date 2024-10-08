package uz.frodo.kitoblaruzb_eng.screens.book_info

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.frodo.kitoblaruzb_eng.R
import uz.frodo.kitoblaruzb_eng.model.DownloadResult
import uz.frodo.kitoblaruzb_eng.repository.Repository
import uz.frodo.kitoblaruzb_eng.repository.room.BookEntity
import uz.frodo.kitoblaruzb_eng.screens.tabs.allbooks.AllBooksContract
import uz.frodo.kitoblaruzb_eng.utils.NetworkStatusValidator
import javax.inject.Inject

@HiltViewModel
class BookInfoVM @Inject constructor(
    private val directions: BookInfoContract.Direction,
    private val repository: Repository,
    @ApplicationContext private val context: Context,
    private val networkStatusValidator: NetworkStatusValidator
) : ViewModel(), BookInfoContract.ViewModel {
    private var filePath:String? = null
    override val container = container<BookInfoContract.UIState,
            BookInfoContract.SideEffect>(BookInfoContract.UIState())

    init {
        intent {
            repository.getDownloadedBooks()
                .onEach {
                    Log.d("TAG", "room books: $it")
                    val book = it.find { it.id == state.book.id }
                    filePath = book?.filePath
                    Log.d("TAG", "finded book: $book")
                    book?.let {
                        reduce { state.copy(isDownloaded = true) }
                    }
                    Log.d("TAG", "isDownloaded: ${state.isDownloaded}: ")
                }
                .launchIn(viewModelScope)
        }

    }

    override fun onEventDispatcher(intent: BookInfoContract.Intent) = intent {
        when (intent) {
            is BookInfoContract.Intent.SetBook -> {
                reduce { state.copy(book = intent.book) }
            }

            BookInfoContract.Intent.ReadBook -> {
                Log.d("TAG", "onEventDispatcher: read book vm")
                filePath?.let {
                    directions.moveToReadScreen(it)
                }
            }

            BookInfoContract.Intent.DownloadBook->{
                Log.d("TAG", "onEventDispatcher: download book vm")
                if (!networkStatusValidator.isNetworkEnabled)
                    postSideEffect(BookInfoContract.SideEffect.Message(context.getString(R.string.txt_no_internet)))

                repository.downloadBook(state.book.path)
                    .onStart { reduce { state.copy(isLoading = true) } }
                    .onEach {
                        when (it) {
                            is DownloadResult.Success -> {
                                Log.d("TAG", "onEventDispatcher: ${it.filePath}")
                                viewModelScope.launch(Dispatchers.IO) {
                                    repository.insertBook(BookEntity(state.book.id, it.filePath.toString()))
                                }

                            }

                            is DownloadResult.Fail -> {
                                Log.d("TAG", "onEventDispatcher:fail ${it.message}")
                                intent { postSideEffect(BookInfoContract.SideEffect.Message(it.message ?: "Unknown error")) }
                            }

                            is DownloadResult.Progress -> {
                                Log.d("TAG", "onEventDispatcher: ${it.percent}")
                                reduce { state.copy(percentage = it.percent) }
                            }
                        }
                    }
                    .onCompletion {
                        reduce { state.copy(isLoading = false) }
                        Log.d("TAG", "onEventDispatcher: onCompelation: ${state.isLoading}")
                    }
                    .launchIn(viewModelScope)
            }

        }
    }


}


