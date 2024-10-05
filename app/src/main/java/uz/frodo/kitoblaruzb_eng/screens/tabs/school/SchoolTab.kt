package uz.frodo.kitoblaruzb_eng.screens.tabs.school

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import uz.frodo.kitoblaruzb_eng.R
import uz.frodo.kitoblaruzb_eng.ui.theme.KitoblarUzbEngTheme

class SchoolTab:Tab {
    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(image = ImageVector.vectorResource(R.drawable.school_24px))

            return TabOptions(
                index = 1u,
                title = "",
                icon = icon
            )
        }

    @Composable
    override fun Content() {
        SchoolTabContent()
    }
}

@Preview
@Composable
fun SchoolTabPrev() {
    KitoblarUzbEngTheme {
        SchoolTabContent()
    }
}

@Composable
fun SchoolTabContent() {

}