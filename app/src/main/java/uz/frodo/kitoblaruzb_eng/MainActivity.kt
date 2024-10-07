package uz.frodo.kitoblaruzb_eng

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.lifecycleScope
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.NavigatorDisposeBehavior
import cafe.adriel.voyager.transitions.SlideTransition
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.frodo.kitoblaruzb_eng.repository.shared.LocalStorage
import uz.frodo.kitoblaruzb_eng.screens.splash.SplashScreen
import uz.frodo.kitoblaruzb_eng.ui.theme.KitoblarUzbEngTheme
import uz.frodo.kitoblaruzb_eng.utils.navigation.NavigationHandler
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationHandler: NavigationHandler

    @Inject
    lateinit var localStorage: LocalStorage

    @OptIn(ExperimentalVoyagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            KitoblarUzbEngTheme(darkTheme = localStorage.isDarkMode) {
                Navigator(
                    screen = SplashScreen(),
                    disposeBehavior = NavigatorDisposeBehavior(disposeSteps = false)
                ) { navigator ->
                    LaunchedEffect(key1 = navigator) {
                        navigationHandler.navigationStack
                            .onEach {
                                it.invoke(navigator)
                            }
                            .launchIn(lifecycleScope)
                    }
//                    CurrentScreen()
                    SlideTransition(
                        navigator = navigator,
                        disposeScreenAfterTransitionEnd = true
                    )
                }
            }


        }
    }
}
