package uz.frodo.kitoblaruzb_eng.screens.book_info

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class BookInfoVM @Inject constructor(
    private val directions: BookInfoContract.Direction,
) : ViewModel(), BookInfoContract.ViewModel {

    init {

    }

    override val container = container<BookInfoContract.UIState, BookInfoContract.SideEffect>(BookInfoContract.UIState())

    override fun onEventDispatcher(intent: BookInfoContract.Intent) = intent {

    }


}
