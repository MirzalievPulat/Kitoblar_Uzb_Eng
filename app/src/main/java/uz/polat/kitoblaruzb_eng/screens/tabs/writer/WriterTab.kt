package uz.polat.kitoblaruzb_eng.screens.tabs.writer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import uz.polat.kitoblaruzb_eng.R
import uz.polat.kitoblaruzb_eng.ui.theme.KitoblarUzbEngTheme

@OptIn(ExperimentalVoyagerApi::class)
object WriterTab:Tab{
    private fun readResolve(): Any = WriterTab

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.person_24px))

            return TabOptions(
                index = 2u,
                title = "",
                icon = icon
            )
        }

    @Composable
    override fun Content() {
        WriterTabContent()
    }
}

@Preview
@Composable
fun WriterTabPrev() {
    KitoblarUzbEngTheme {
        WriterTabContent()
    }
}

@Composable
fun WriterTabContent() {

    LocalConfiguration.current

    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = stringResource(R.string.txt_soon_author),
            style = MaterialTheme.typography.bodyMedium
                .copy(color = MaterialTheme.colorScheme.secondary), modifier = Modifier.padding(horizontal = 32.dp))
    }
}