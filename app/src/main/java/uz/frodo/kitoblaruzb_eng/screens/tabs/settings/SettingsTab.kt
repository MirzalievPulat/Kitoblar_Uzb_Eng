package uz.frodo.kitoblaruzb_eng.screens.tabs.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.orbitmvi.orbit.compose.collectAsState
import uz.frodo.kitoblaruzb_eng.R
import uz.frodo.kitoblaruzb_eng.ui.components.DarkModeSwitch
import uz.frodo.kitoblaruzb_eng.ui.components.HyperLinkText
import uz.frodo.kitoblaruzb_eng.ui.theme.KitoblarUzbEngTheme
import uz.frodo.kitoblaruzb_eng.ui.theme.latoFont

object SettingsTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.settings_24px))

            return TabOptions(
                index = 3u,
                title = "Settings",
                icon = icon
            )
        }

    @Composable
    override fun Content() {
        val viewModel: SettingsContract.ViewModel = getViewModel<SettingsVM>()
        val state = viewModel.collectAsState()

        SettingsTabContent(state, viewModel::onEventDispatcher)

    }
}


@Preview
@Composable
fun SettingsTabPrev() {
    KitoblarUzbEngTheme {
        SettingsTabContent(
            remember { mutableStateOf(SettingsContract.UIState()) },
            {}
        )
    }
}

@Composable
fun SettingsTabContent(
    uiState: State<SettingsContract.UIState>,
    onEventDispatcher: (SettingsContract.Intent) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.txt_theme),
                style = MaterialTheme.typography.bodyLarge
                    .copy(color = MaterialTheme.colorScheme.secondary)
            )

            DarkModeSwitch(
                checked = uiState.value.isDarkMode,
                modifier = Modifier,
                onCheckedChanged = {
                    onEventDispatcher(SettingsContract.Intent.SwitchClick(it))
                })
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.txt_language2),
                style = MaterialTheme.typography.bodyLarge
                    .copy(color = MaterialTheme.colorScheme.secondary)
            )

            Switch(
                checked = uiState.value.currentLang == "uz",
                onCheckedChange = {
                    onEventDispatcher(SettingsContract.Intent.LangChange(it))
                },
                thumbContent = {
                    if (uiState.value.currentLang == "uz") {
                        Image(
                            painter = painterResource(id = R.drawable.ic_flag_uzs),
                            contentDescription = ""
                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.ic_flag_usd),
                            contentDescription = ""
                        )
                    }
                },
                colors = SwitchDefaults.colors(
                    checkedTrackColor = MaterialTheme.colorScheme.onSecondary,
                    uncheckedTrackColor = MaterialTheme.colorScheme.onSecondary,
                    checkedThumbColor = Color.Transparent,
                    uncheckedThumbColor = Color.Transparent,
                    checkedBorderColor = MaterialTheme.colorScheme.secondary,
                    uncheckedBorderColor = MaterialTheme.colorScheme.secondary
                )
            )
        }

        Text(
            text = stringResource(R.string.txt_info),
            style = MaterialTheme.typography.bodyLarge
                .copy(color = MaterialTheme.colorScheme.secondary),
            modifier = Modifier.padding(top = 32.dp)
        )

        Text(
            text = stringResource(id = R.string.info),
            style = MaterialTheme.typography.bodyMedium
                .copy(color = MaterialTheme.colorScheme.secondary),
            modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp)
        )

        HyperLinkText(
            fullText = "1. https://www.pdfdrive.com/ \n2. https://manybooks.net/",
            linkText = listOf("https://www.pdfdrive.com/", "https://manybooks.net/"),
            hyperLinks = listOf("https://www.pdfdrive.com", "https://manybooks.net"),
            fontFamily = latoFont,
            fontSize = 18.sp,
            textColor = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        )


    }
}