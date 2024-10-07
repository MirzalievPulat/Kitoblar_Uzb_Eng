package uz.frodo.kitoblaruzb_eng.screens.read

import android.app.Activity
import android.content.pm.ActivityInfo
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.google.android.gms.measurement.api.AppMeasurementSdk.OnEventListener
import com.rajat.pdfviewer.PdfRendererView
import com.rajat.pdfviewer.compose.PdfRendererViewCompose
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState
import uz.frodo.kitoblaruzb_eng.app.App
import uz.frodo.kitoblaruzb_eng.ui.theme.KitoblarUzbEngTheme
import uz.frodo.kitoblaruzb_eng.utils.navigation.AppNavigator
import uz.frodo.kitoblaruzb_eng.utils.navigation.AppScreen
import java.io.File
import javax.inject.Inject

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
    val coroutineScope = rememberCoroutineScope()

    Box(
        Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .clickable {
//                showBackButton = !showBackButton
            }
    ) {




//        AndroidView(
//            factory = {
//                PdfRendererView(it).apply {
////                    initWithFile(File(filePath))
//                    setOnClickListener {
//
//                    }
//                }
//            },
//            modifier = Modifier.fillMaxSize()
//        )

        PdfRendererViewCompose(
            file = File(filePath),
            modifier = Modifier
                .fillMaxSize()
//                .background(color = Color.Red)

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
            ){
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
