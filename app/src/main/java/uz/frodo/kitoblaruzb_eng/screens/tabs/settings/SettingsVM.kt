package uz.frodo.kitoblaruzb_eng.screens.tabs.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.frodo.kitoblaruzb_eng.repository.Repository
import uz.frodo.kitoblaruzb_eng.repository.shared.LocalStorage
import uz.frodo.kitoblaruzb_eng.screens.splash.SplashContract
import uz.frodo.kitoblaruzb_eng.ui.theme.changeAppToDarkMode
import uz.frodo.kitoblaruzb_eng.utils.LanguageChangeHelper
import javax.inject.Inject

@HiltViewModel
class SettingsVM @Inject constructor(
    private val repository: Repository,
    private val localStorage: LocalStorage,
    private val languageChangeHelper: LanguageChangeHelper
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
                changeAppToDarkMode(intent.isDarkMode)
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
