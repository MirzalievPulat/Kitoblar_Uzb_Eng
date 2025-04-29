package uz.polat.kitoblaruzb_eng.screens.main

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import uz.polat.kitoblaruzb_eng.R
import uz.polat.kitoblaruzb_eng.screens.tabs.allbooks.AllBooksTab
import uz.polat.kitoblaruzb_eng.screens.tabs.school.SchoolTab
import uz.polat.kitoblaruzb_eng.screens.tabs.settings.SettingsTab
import uz.polat.kitoblaruzb_eng.screens.tabs.writer.WriterTab
import uz.polat.kitoblaruzb_eng.ui.theme.KitoblarUzbEngTheme

@OptIn(ExperimentalVoyagerApi::class)
class MainScreen : Screen {
    @Composable
    override fun Content() {
//        KitoblarUzbEngTheme(
//            darkTheme = isDarkMode
//        ) {
            MainScreenContent()
//        }
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
    Log.d("TTT", "MainScreenContent: o'zgardi ")
    TabNavigator(tab = AllBooksTab,){
        Scaffold(
            content = {
                Box(modifier = Modifier.padding(it)){
                    CurrentTab()
                }
            },
            topBar = {
                Column {
                    NavigationBar(modifier = Modifier
                        .defaultMinSize(minHeight = 56.dp)
//                        .background(color = MaterialTheme.colorScheme.onSecondary)
                        , containerColor = MaterialTheme.colorScheme.onSecondary
                    ) {

                        Text(text = stringResource(id = R.string.txt_books),
                            style = MaterialTheme.typography.headlineSmall
                                .copy(fontWeight = FontWeight.Light, color = MaterialTheme.colorScheme.secondary),
                            modifier = Modifier.padding(start = 16.dp)
                        )

                        TabNavigationItem(AllBooksTab)
                        TabNavigationItem(SchoolTab)
                        TabNavigationItem(WriterTab)
                        TabNavigationItem(SettingsTab)
                    } 
                    
                    Box(modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.onSurfaceVariant))
                }
            }
        )
        val tabNavigator = LocalTabNavigator.current
        val activity = LocalContext.current as Activity
        BackHandler {
            if (tabNavigator.current != AllBooksTab){
                tabNavigator.current = AllBooksTab
            }else{
                activity.finish()
            }
        }
    }
}

@Composable
fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    Log.d("TAG", "TabNavigationItem: tab:$tab")
    Log.d("TAG", "TabNavigationItem: current tab:${tabNavigator.current}")

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
            tint = if(tabNavigator.current == tab) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}