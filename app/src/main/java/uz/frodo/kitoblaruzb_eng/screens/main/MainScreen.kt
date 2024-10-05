package uz.frodo.kitoblaruzb_eng.screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import uz.frodo.kitoblaruzb_eng.R
import uz.frodo.kitoblaruzb_eng.screens.tabs.allbooks.AllBooksTab
import uz.frodo.kitoblaruzb_eng.screens.tabs.school.SchoolTab
import uz.frodo.kitoblaruzb_eng.screens.tabs.writer.WriterTab
import uz.frodo.kitoblaruzb_eng.ui.theme.KitoblarUzbEngTheme
import uz.frodo.kitoblaruzb_eng.ui.theme.Main

class MainScreen : Screen {
    @Composable
    override fun Content() {
        KitoblarUzbEngTheme {
            MainScreenContent()
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    KitoblarUzbEngTheme {
        MainScreenContent()
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreenContent() {
    TabNavigator(AllBooksTab()){
        Scaffold(
            content = {
                CurrentTab()
            },
            topBar = {
                NavigationBar(modifier = Modifier
                    .defaultMinSize(minHeight = 56.dp)
                    .background(color = Color.White)
                    , containerColor = Color.White
                ) {

                    Text(text = stringResource(id = R.string.txt_books),
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(start = 16.dp))

                    TabNavigationItem(AllBooksTab())
                    TabNavigationItem(SchoolTab())
                    TabNavigationItem(WriterTab())
                }
            }


        )
    }
}

@Composable
fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    val interactionSource = remember { MutableInteractionSource() }


    Column(
        modifier = Modifier
            .height(56.dp)
            .weight(1f)
            .padding(top = 6.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { tabNavigator.current = tab }
            ),

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = tab.options.icon!!,
            contentDescription = tab.options.title,
            modifier = Modifier.size(24.dp),
            tint = if(tabNavigator.current == tab) Main else Color.Gray
        )
    }
}