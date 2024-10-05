package uz.frodo.kitoblaruzb_eng.screens.book_info

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import uz.frodo.kitoblaruzb_eng.R
import uz.frodo.kitoblaruzb_eng.ui.components.RatingBar
import uz.frodo.kitoblaruzb_eng.ui.theme.BookEdge
import uz.frodo.kitoblaruzb_eng.ui.theme.KitoblarUzbEngTheme
import uz.frodo.kitoblaruzb_eng.ui.theme.Main

class BookInfoScreen :Screen{
    @Composable
    override fun Content() {

    }

}

@Preview
@Composable
fun BookInfoScreenPrev() {
    KitoblarUzbEngTheme {
        BookInfoScreenContent()
    }
}

@Composable
fun BookInfoScreenContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .verticalScroll(rememberScrollState()),
        ) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 64.dp, vertical = 32.dp)
                    .clip(RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp, topStart = 4.dp, bottomStart = 4.dp))
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp, topStart = 4.dp, bottomStart = 4.dp)
                    )
                    .aspectRatio(5 / 7f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.robinson),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds,
                )

                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1 / 28f)
                        .background(color = BookEdge)
                )
            }


            Text(
                text = "To kill a mocking bird",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.SemiBold),
                modifier = Modifier.padding(horizontal = 32.dp)
            )

            Text(
                text = "Herbelt Shtild",
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray),
                modifier = Modifier
                    .padding(top = 8.dp)
                    .padding(horizontal = 32.dp)
            )

            Row(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .padding(horizontal = 32.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RatingBar(
                    starColor = Color.Black,
                    rating = 4.0,
                    modifier = Modifier.size(24.dp)
                ) {}

                Text(
                    text = "4.0",
                    style = MaterialTheme.typography.headlineMedium.copy(color = Color.Gray),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            LazyRow(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .padding(horizontal = 32.dp)
            ) {
                items(3) { index ->
                    Text(
                        text = "Fantasy",
                        style = MaterialTheme.typography.bodySmall
                    )

                    if (index < 2) {
                        Text(
                            text = "   |   ",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .padding(horizontal = 32.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Button(
                    onClick = {},
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .height(50.dp)
                        .weight(1f),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Main)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.play_circle_24px),
                            contentDescription = "play",
                        )
                        Text(
                            text = stringResource(R.string.txt_play_audio),
                            style = MaterialTheme.typography.bodySmall.copy(color = Color.White),
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }

                }

                Button(
                    onClick = {},
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .height(50.dp)
                        .weight(1f),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Main)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.menu_book_24px),
                            contentDescription = "read",
                        )
                        Text(
                            text = stringResource(R.string.txt_read_book),
                            style = MaterialTheme.typography.bodySmall.copy(color = Color.White),
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }

            Text(
                text = stringResource(R.string.txt_summary),
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.SemiBold),
                modifier = Modifier
                    .padding(top = 32.dp)
                    .padding(horizontal = 32.dp)
            )

            Text(
                text = stringResource(R.string.txt_lorem),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(vertical = 32.dp)
                    .padding(horizontal = 32.dp)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .align(Alignment.BottomCenter)
                .background(brush = Brush.verticalGradient(colors = listOf(Color.Transparent, Color.White)))
        )
    }


}