package uz.frodo.kitoblaruzb_eng.screens.book_info

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.transitions.ScreenTransition
import cafe.adriel.voyager.transitions.SlideTransition
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import org.orbitmvi.orbit.compose.collectAsState
import uz.frodo.kitoblaruzb_eng.R
import uz.frodo.kitoblaruzb_eng.model.Book
import uz.frodo.kitoblaruzb_eng.ui.components.RatingBar
import uz.frodo.kitoblaruzb_eng.ui.components.ShimmerEffect
import uz.frodo.kitoblaruzb_eng.ui.theme.BookEdge
import uz.frodo.kitoblaruzb_eng.ui.theme.KitoblarUzbEngTheme
import uz.frodo.kitoblaruzb_eng.ui.theme.Main
import kotlin.math.log

@OptIn(ExperimentalVoyagerApi::class)
class BookInfoScreen(val book: Book) :Screen{
    @Composable
    override fun Content() {
        val viewModel:BookInfoContract.ViewModel = getViewModel<BookInfoVM>()

        viewModel.onEventDispatcher(BookInfoContract.Intent.SetBook(book))
        BookInfoScreenContent(viewModel.collectAsState(),viewModel::onEventDispatcher)
    }
}

@Preview
@Composable
fun BookInfoScreenPrev() {
    KitoblarUzbEngTheme {
        BookInfoScreenContent(remember { mutableStateOf(BookInfoContract.UIState()) },{})
    }
}

@Composable
fun BookInfoScreenContent(
    uiState: State<BookInfoContract.UIState>,
    onEventDispatcher:(BookInfoContract.Intent)->Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState()),
        ) {

            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(uiState.value.book.image)
                    .size(Size.ORIGINAL)
                    .build()
            )

            Box(
                modifier = Modifier
                    .padding(horizontal = 80.dp, vertical = 32.dp)
                    .clip(RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp, topStart = 4.dp, bottomStart = 4.dp))
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp, topStart = 4.dp, bottomStart = 4.dp)
                    )
                    .aspectRatio(4 / 5.5f)
            ) {
                if (painter.state !is AsyncImagePainter.State.Success){
                    ShimmerEffect(modifier = Modifier.fillMaxSize())
                }else{
                    Image(
                        painter = painter,
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

            }


            Text(
                text = uiState.value.book.name,
                style = MaterialTheme.typography.headlineSmall
                    .copy(fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.secondary),
                modifier = Modifier.padding(horizontal = 32.dp)
            )

            Text(
                text = uiState.value.book.author,
                style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurfaceVariant),
                modifier = Modifier
                    .padding(horizontal = 32.dp)
            )

            Row(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .padding(horizontal = 32.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = stringResource(R.string.txt_language),
                    style = MaterialTheme.typography.bodyMedium
                        .copy(color = MaterialTheme.colorScheme.onSurfaceVariant),
                )

                Text(
                    text = uiState.value.book.language,
                    style = MaterialTheme.typography.bodyMedium
                        .copy(color = MaterialTheme.colorScheme.onSurfaceVariant),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }


            Row(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .padding(horizontal = 32.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = stringResource(R.string.txt_pages),
                    style = MaterialTheme.typography.bodyMedium
                        .copy(color = MaterialTheme.colorScheme.onSurfaceVariant),
                )

                Text(
                    text = uiState.value.book.pages.toString(),
                    style = MaterialTheme.typography.bodyMedium
                        .copy(color = MaterialTheme.colorScheme.onSurfaceVariant),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }


            Row(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .padding(horizontal = 32.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.txt_rating),
                    style = MaterialTheme.typography.bodyMedium
                        .copy(color = MaterialTheme.colorScheme.onSurfaceVariant),
                )

                RatingBar(
                    starColor = MaterialTheme.colorScheme.secondary,
                    rating = uiState.value.book.rating.toDouble(),
                    modifier = Modifier.padding(start = 16.dp)
                ) {}


            }


            LazyRow(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .padding(horizontal = 32.dp)
            ) {
                itemsIndexed(uiState.value.book.genre) {index,genre->
                    Text(
                        text = genre,
                        style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.secondary)
                    )

                    if (index < uiState.value.book.genre.lastIndex) {
                        Text(
                            text = "   |   ",
                            style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.secondary)
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .padding(32.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Button(
                    onClick = {},
                    modifier = Modifier
                        .height(50.dp)
                        .weight(1f),
                    enabled = false,
                    contentPadding = PaddingValues(horizontal = 8.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.play_circle_24px),
                            contentDescription = "play",
                        )
                        Text(
                            text = stringResource(R.string.txt_play_audio),
                            style = MaterialTheme.typography.bodySmall
                                .copy(color = MaterialTheme.colorScheme.onPrimary),
                            modifier = Modifier.padding(start = 8.dp),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 2,
                            textAlign = TextAlign.Center
                        )
                    }

                }

                Button(
                    onClick = {
                        if (uiState.value.isLoading) return@Button

                        if (uiState.value.isDownloaded){
                            onEventDispatcher(BookInfoContract.Intent.ReadBook)
                        }else{
                            onEventDispatcher(BookInfoContract.Intent.DownloadBook)
                        }

                    },
                    modifier = Modifier
                        .height(50.dp)
                        .weight(1f)
                    ,
                    contentPadding = PaddingValues(horizontal = 8.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    if (uiState.value.isLoading){
                        CircularProgressIndicator(
                            progress = {
                                val p = uiState.value.percentage/100f
                                if (p == 0f){ 10f }else p
//                                100f
                            },
                            modifier = Modifier.size(30.dp),
                            color = MaterialTheme.colorScheme.onPrimary,
                        )
                    }else{
                        Row(verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()) {
                            Icon(
                                painter = painterResource(id = R.drawable.menu_book_24px),
                                contentDescription = "read",
                            )
                            Text(
                                text = if (uiState.value.isDownloaded) stringResource(R.string.txt_read_book) else stringResource(R.string.txt_download_book),
                                style = MaterialTheme.typography.bodySmall
                                    .copy(color = MaterialTheme.colorScheme.onPrimary),
                                modifier = Modifier.padding(start = 8.dp),
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 2,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                }
            }

            Text(
                text = stringResource(R.string.txt_summary),
                style = MaterialTheme.typography.headlineSmall
                    .copy(fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.secondary),
                modifier = Modifier
                    .padding(horizontal = 32.dp)
            )

            Text(
                text = uiState.value.book.summary,
                style = MaterialTheme.typography.bodySmall
                    .copy(color = MaterialTheme.colorScheme.secondary),
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 32.dp)
                    .padding(horizontal = 32.dp)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .align(Alignment.BottomCenter)
                .background(brush = Brush.verticalGradient(colors = listOf(Color.Transparent,
                    MaterialTheme.colorScheme.onSecondary)))
        )
    }


}
