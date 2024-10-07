package uz.frodo.kitoblaruzb_eng.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.transitions.ScreenTransition
import cafe.adriel.voyager.transitions.SlideTransition
import org.orbitmvi.orbit.compose.collectAsState
import uz.frodo.kitoblaruzb_eng.R
import uz.frodo.kitoblaruzb_eng.ui.theme.KitoblarUzbEngTheme

@OptIn(ExperimentalVoyagerApi::class)
class SplashScreen :Screen{


    @Composable
    override fun Content() {
        val viewModel: SplashContract.ViewModel = getViewModel<SplashVM>()

        SplashScreenContent(viewModel.collectAsState(), viewModel::onEventDispatcher)
    }

}

@Preview
@Composable
fun SplashScreenPrev() {
    KitoblarUzbEngTheme {
        SplashScreenContent(remember { mutableStateOf(SplashContract.UIState()) }, {})
    }
}

@Composable
fun SplashScreenContent(
    uiState: State<SplashContract.UIState>,
    onEventDispatcher: (SplashContract.Intent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter =  painterResource(
                id = if(uiState.value.isDarkTheme) R.drawable.splash_back_dark else R.drawable.splash_back
            ),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )

        Text(
            text = stringResource(R.string.txt_books),
            style = MaterialTheme.typography.headlineLarge
                .copy(color = MaterialTheme.colorScheme.secondary)
        )
    }
}