package uz.frodo.kitoblaruzb_eng.screens.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import cafe.adriel.voyager.transitions.ScreenTransition
import cafe.adriel.voyager.transitions.SlideTransition
import uz.frodo.kitoblaruzb_eng.R
import uz.frodo.kitoblaruzb_eng.screens.tabs.allbooks.AllBooksTab
import uz.frodo.kitoblaruzb_eng.screens.tabs.school.SchoolTab
import uz.frodo.kitoblaruzb_eng.screens.tabs.settings.SettingsTab
import uz.frodo.kitoblaruzb_eng.screens.tabs.writer.WriterTab
import uz.frodo.kitoblaruzb_eng.ui.theme.KitoblarUzbEngTheme
import uz.frodo.kitoblaruzb_eng.ui.theme.Main

@OptIn(ExperimentalVoyagerApi::class)
class MainScreen : Screen {

    @Composable
    override fun Content() {
        MainScreenContent()
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
    TabNavigator(tab = AllBooksTab,
        tabDisposable = {
            TabDisposable(
                navigator = it,
                tabs = listOf(AllBooksTab, SchoolTab,WriterTab)
            )
        }){
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
                        .background(color = MaterialTheme.colorScheme.onSecondary)
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
        val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
        DisposableEffect(Unit) {
            val callback = object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (tabNavigator.current != AllBooksTab) {
                        // Navigate back to the AllBooksTab
                        tabNavigator.current = AllBooksTab
                    } else {
                        // If we are already in the AllBooksTab, invoke the default back press behavior
                        backDispatcher?.onBackPressed()
                    }
                }
            }

            // Add the back press callback
            backDispatcher?.addCallback(callback)
            onDispose {
                // Remove the back press callback when the composable is disposed
                callback.remove()
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