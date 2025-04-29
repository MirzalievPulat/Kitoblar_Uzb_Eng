package uz.polat.kitoblaruzb_eng.screens.read

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.rajat.pdfviewer.compose.PdfRendererViewCompose
import org.orbitmvi.orbit.compose.collectAsState
import uz.polat.kitoblaruzb_eng.ui.theme.KitoblarUzbEngTheme
import java.io.File

@OptIn(ExperimentalVoyagerApi::class)
class ReadScreen(val filePath: String) : Screen {


    @Composable
    override fun Content() {

        val context = LocalContext.current
        DisposableEffect(Unit) {
            val activity = context as? Activity
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR
            onDispose {
                activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
        }

        val viewModel:ReadScreenContract.ViewModel = getViewModel<ReadScreenVM>()

        ReadScreenContent(filePath,viewModel.collectAsState(),viewModel::onEventDispatcher)
    }
}

@Preview
@Composable
fun ReadScreenPrev() {
    KitoblarUzbEngTheme {
        ReadScreenContent(filePath = "book/book.pdf", remember { mutableStateOf(ReadScreenContract.UIState()) },{})
    }
}

@Composable
fun ReadScreenContent(
    filePath: String,
    state: State<ReadScreenContract.UIState>,
    onEventListener: (ReadScreenContract.Intent)->Unit
) {
    var showBackButton by remember { mutableStateOf(true) }
//    val systemUiController = rememberSystemUiController()
////    val useDarkIcons = MaterialTheme.colorScheme.isLight
//
//    LaunchedEffect(Unit) {
//        systemUiController.isStatusBarVisible = false // Hide status bar
////        systemUiController.isNavigationBarVisible = true // Keep navigation bar visible
//    }

    Surface(modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.systemBars)) {
        Box(
            Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .windowInsetsPadding(WindowInsets.systemBars)
                .clickable {
                }
        ) {
            PdfRendererViewCompose(
                file = File(filePath),
                modifier = Modifier
                    .fillMaxSize()

            )

            if (showBackButton) {
                Box(modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.TopEnd)
                    .background(color = Color.LightGray, shape = CircleShape)
                    .clip(CircleShape)
                    .clickable {
                        onEventListener(ReadScreenContract.Intent.BackClick)
                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        tint = Color.Black,
                        contentDescription = "",
                        modifier = Modifier.padding(8.dp)
                    )
                }

            }
        }
    }

}
