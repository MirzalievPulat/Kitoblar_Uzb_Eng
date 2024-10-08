package uz.frodo.kitoblaruzb_eng.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import kotlinx.coroutines.launch
import uz.frodo.kitoblaruzb_eng.R
import uz.frodo.kitoblaruzb_eng.ui.theme.BlueSky
import uz.frodo.kitoblaruzb_eng.ui.theme.BorderColor
import uz.frodo.kitoblaruzb_eng.ui.theme.KitoblarUzbEngTheme
import uz.frodo.kitoblaruzb_eng.ui.theme.NightSky
import uz.frodo.kitoblaruzb_eng.ui.theme.Primary

@Composable
fun LanguageSwitch(
    checked: Boolean,
    modifier: Modifier,
    onCheckedChanged: (Boolean) -> Unit) {

//    val switchWidth = 160.dp
//    val switchHeight = 64.dp
//    val handleSize = 52.dp
//    val handlePadding = 10.dp

    val switchWidth = 60.dp
    val switchHeight = 24.dp
    val handleSize = 20.dp
    val handlePadding = 4.dp

    val valueToOffset = if (checked) 1f else 0f
    val offset = remember { Animatable(valueToOffset) }
    val scope = rememberCoroutineScope()

    DisposableEffect(checked) {
        if (offset.targetValue != valueToOffset) {
            scope.launch {
                offset.animateTo(valueToOffset, animationSpec = tween(1000))
            }
        }
        onDispose { }
    }

    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .width(switchWidth)
            .height(switchHeight)
            .clip(RoundedCornerShape(switchHeight))
            .background(lerp(BlueSky, NightSky, offset.value))
            .border(1.dp, Primary, RoundedCornerShape(switchHeight))
            .toggleable(
                value = checked,
                onValueChange = onCheckedChanged,
                role = Role.Switch,
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            )
    ) {
        val backgroundPainter = painterResource(R.drawable.lang_back)
        Canvas(modifier = Modifier.fillMaxSize()) {
            with(backgroundPainter) {
                val scale = size.width / intrinsicSize.width
                val scaledHeight = intrinsicSize.height * scale
                translate(top = (size.height - scaledHeight) * (1f - offset.value)) {
                    draw(Size(size.width, scaledHeight))
                }
            }
        }

        Image(
            painter = painterResource(R.drawable.glow),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(switchWidth)
                .graphicsLayer {
                    scaleX = 1.2f
                    scaleY = scaleX
                    translationX = lerp(
                        -size.width * 0.5f + handlePadding.toPx() + handleSize.toPx() * 0.5f,
                        switchWidth.toPx() - size.width * 0.5f - handlePadding.toPx() - handleSize.toPx() * 0.5f,
                        offset.value
                    )
                }
        )

        Box(
            modifier = Modifier
                .padding(horizontal = handlePadding)
                .size(handleSize + 8.dp)
                // This controls the position of the handle (flag) on the switch based on the offset value
                .offset(x = (switchWidth - handleSize - handlePadding * 2f) * offset.value)
                .clip(CircleShape)
                .background(Color.White) // Add background to make it more visible
        ) {
            // Flag image that appears based on the `checked` state
            Image(
                painter = painterResource(if (checked) R.drawable.ic_flag_uzs else R.drawable.ic_flag_usd),
                contentDescription = null,
                modifier = Modifier
                    .size(handleSize)
                    .align(Alignment.Center) // Align the image in the center of the handle
            )
        }

    }
}

@Preview
@Composable
fun LangSwitchPreview() {

    KitoblarUzbEngTheme {

        var value by remember { mutableStateOf(true) }
        LanguageSwitch(value, Modifier.padding(24.dp)) { value = it }
    }
}