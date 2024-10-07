package uz.frodo.kitoblaruzb_eng.ui.components

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit

@Composable
fun HyperLinkText(
    modifier: Modifier = Modifier,
    fullText:String,
    linkText:List<String>,
    linkTextColor:Color = Color.Blue,
    textColor:Color = Color.Black,
    linkTextFontWeight: FontWeight = FontWeight.Medium,
    linkTextDecoration: TextDecoration = TextDecoration.Underline,
    fontFamily: FontFamily = FontFamily.Default,
    hyperLinks:List<String>,
    fontSize:TextUnit = TextUnit.Unspecified
) {

    val annotatedString = buildAnnotatedString {
        append(fullText)

        addStyle(
                style = SpanStyle(
                    fontFamily = fontFamily,
                    fontSize = fontSize,
                    color = textColor
                ),
        start =  0,
        end = fullText.length
        )


        linkText.forEachIndexed { index, link ->
            val start = fullText.indexOf(link)
            val end = start + link.length

            addStyle(
                style = SpanStyle(
                    color = linkTextColor,
                    fontSize = fontSize,
                    fontWeight = linkTextFontWeight,
                    textDecoration = linkTextDecoration
                ),
                start = start,
                end = end
            )
            addStringAnnotation(
                tag = "URL",
                annotation = hyperLinks[index],
                start = start,
                end = end
            )
        }

    }
    val uriHandler = LocalUriHandler.current

    ClickableText(
        modifier = modifier,
        text = annotatedString
    ) {
        annotatedString.getStringAnnotations("URL",it,it)
            .firstOrNull()?.let {
                uriHandler.openUri(it.item)
            }
    }
}