package uz.polat.kitoblaruzb_eng.screens.tabs.settings

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.polat.kitoblaruzb_eng.repository.Repository
import uz.polat.kitoblaruzb_eng.repository.shared.LocalStorage
import uz.polat.kitoblaruzb_eng.utils.LanguageChangeHelper
import javax.inject.Inject

@HiltViewModel
class SettingsVM @Inject constructor(
    private val repository: Repository,
    private val localStorage: LocalStorage,
    private val languageChangeHelper: LanguageChangeHelper,
    @ApplicationContext private val context: Context
) : ViewModel(), SettingsContract.ViewModel {

    override val container = container<SettingsContract.UIState,
            SettingsContract.SideEffect>(SettingsContract.UIState()
                .copy(isDarkMode = localStorage.isDarkMode, currentLang = languageChangeHelper.getLanguageCode())
            )


    override fun onEventDispatcher(intent: SettingsContract.Intent) = intent {
        when(intent){
            is SettingsContract.Intent.SwitchClick->{
                reduce { state.copy(isDarkMode = intent.isDarkMode) }
                localStorage.isDarkMode = intent.isDarkMode
//                changeAppToDarkMode(intent.isDarkMode)

            }
            is SettingsContract.Intent.LangChange->{
                reduce { state.copy(currentLang = if (intent.checked) "uz" else "en") }
                viewModelScope.launch(Dispatchers.Main) {
                    languageChangeHelper.changeLanguage(if (intent.checked) "uz" else "en")
                }

            }
        }
    }


}
