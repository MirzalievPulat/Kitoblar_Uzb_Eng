package uz.frodo.kitoblaruzb_eng.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import uz.frodo.kitoblaruzb_eng.R

val latoFont = FontFamily(
    Font(R.font.lato_bold, FontWeight.Bold),
    Font(R.font.lato_black, FontWeight.Black),
    Font(R.font.lato_regular, FontWeight.Normal),
    Font(R.font.lato_light, FontWeight.Light),
    Font(R.font.lato_thin, FontWeight.Thin),
)
// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = latoFont,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        color = Color.Black
    ),
    bodyMedium = TextStyle(
        fontFamily = latoFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        color = Color.Black
    ),
    bodySmall = TextStyle(
        fontFamily = latoFont,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = Color.Black
    ),


    headlineLarge = TextStyle(
        fontFamily = latoFont,
        fontWeight = FontWeight.Light,
        fontSize = 48.sp,
        color = Color.Black
    ),

    headlineSmall = TextStyle(
        fontFamily = latoFont,
        fontWeight = FontWeight.Light,
        fontSize = 20.sp,
        color = Color.Black
    ),


 headlineMedium = TextStyle(
        fontFamily = latoFont,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        color = Color.Black
    ),






//    bodyLarge = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Normal,
//        fontSize = 16.sp,
//        lineHeight = 24.sp,
//        letterSpacing = 0.5.sp
//    )
//
//
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)