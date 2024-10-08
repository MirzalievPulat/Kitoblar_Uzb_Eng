package uz.frodo.kitoblaruzb_eng.ui.theme

import android.app.Activity
import android.os.Build
import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import uz.frodo.kitoblaruzb_eng.repository.shared.LocalStorage

private val DarkColorScheme = darkColorScheme(
    primary = Primary,
    onPrimary = Color.White,
    secondary = Color.White,
    onSecondary = Color.Black,
    background = Background,
    onSurfaceVariant = Color.LightGray
)

private val LightColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = Color.White,
    secondary = Color.Black,
    onSecondary = Color.White,
    background = Color.White,
    onSurfaceVariant = Color.LightGray

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

private var statusBarColor by mutableIntStateOf(Color(0xFFFFFFFF).toArgb())
private var isLightStatusBarTheme by mutableStateOf(true)
fun changeStatusBarColor(color: Color) {
    statusBarColor = color.toArgb()
}

fun isStatusBarThemeLight(isLight: Boolean) {
    isLightStatusBarTheme = isLight
}

private var isDarkMode = mutableStateOf(false)

fun changeAppToDarkMode(isDark:Boolean){
    isDarkMode.value = isDark
}

@Composable
fun KitoblarUzbEngTheme(
    darkTheme: Boolean = isDarkMode.value,
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (isDarkMode.value) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> {
            Log.d("TAG", "KitoblarUzbEngTheme: $darkTheme")
            changeStatusBarColor(Color.Black)
            isStatusBarThemeLight(false)
            DarkColorScheme
        }
        else -> {
            changeStatusBarColor(Color.White)
            isStatusBarThemeLight(true)
            LightColorScheme
        }
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = statusBarColor
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
                isLightStatusBarTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}