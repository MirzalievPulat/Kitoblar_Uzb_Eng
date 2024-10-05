package uz.frodo.kitoblaruzb_eng.screens.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import uz.frodo.kitoblaruzb_eng.R
import uz.frodo.kitoblaruzb_eng.ui.theme.KitoblarUzbEngTheme
import uz.frodo.kitoblaruzb_eng.ui.theme.Main

class WelcomeScreen :Screen{
    @Composable
    override fun Content() {
        val viewModel: WelcomeContract.ViewModel = getViewModel<WelcomeVM>()

        WelcomeScreenContent(viewModel.collectAsState(), viewModel::onEventDispatcher)
    }
}


@Preview
@Composable
fun WelcomeScreenPrev() {
    KitoblarUzbEngTheme {
        WelcomeScreenContent(remember { mutableStateOf(WelcomeContract.UIState()) }, {})
    }
}

@Composable
fun WelcomeScreenContent(
    uiState: State<WelcomeContract.UIState>,
    onEventDispatcher: (WelcomeContract.Intent) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.splash_back),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .scale(scaleX = 1.3f, scaleY = 1.5f),
            contentScale = ContentScale.Crop,
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 48.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = stringResource(R.string.txt_welcome),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.align(Alignment.Start)
            )

            Text(
                text = stringResource(R.string.txt_welcome_info),
                style = MaterialTheme.typography.headlineLarge,

                )

            Text(
                text = stringResource(R.string.txt_welcome_info2),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 16.dp)
            )

            Button(
                onClick = {
                    onEventDispatcher(WelcomeContract.Intent.Next)
                },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .height(50.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Main)
            ) {
                Text(
                    text = stringResource(R.string.txt_next),
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
                )
            }

//            Button(
//                onClick = {},
//                modifier = Modifier
//                    .padding(top = 8.dp)
//                    .height(50.dp)
//                    .fillMaxWidth(),
//                shape = RoundedCornerShape(8.dp),
//                border = BorderStroke(width = 2.dp, color = Color.Black),
//                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
//            ) {
//                Text(
//                    text = stringResource(R.string.txt_skip),
//                    style = MaterialTheme.typography.bodyMedium
//                )
//            }


        }

    }

}