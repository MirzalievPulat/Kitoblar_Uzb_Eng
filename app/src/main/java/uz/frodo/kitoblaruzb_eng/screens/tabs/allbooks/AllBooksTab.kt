package uz.frodo.kitoblaruzb_eng.screens.tabs.allbooks

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import uz.frodo.kitoblaruzb_eng.R
import uz.frodo.kitoblaruzb_eng.model.Categories
import uz.frodo.kitoblaruzb_eng.ui.components.RatingBar
import uz.frodo.kitoblaruzb_eng.ui.theme.BookEdge
import uz.frodo.kitoblaruzb_eng.ui.theme.KitoblarUzbEngTheme
import uz.frodo.kitoblaruzb_eng.ui.theme.Main
import uz.frodo.kitoblaruzb_eng.utils.showToast

class AllBooksTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.public_24px))

            return remember {
                TabOptions(
                    index = 0u,
                    title = "",
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        val context = LocalContext.current
        val viewModel:AllBooksContract.ViewModel = getViewModel<AllBooksVM>()  ///qaraaaaaaa
        viewModel.collectSideEffect {
            if (it is AllBooksContract.SideEffect.Message){
                context.showToast(it.message)
            }
        }

        AllBooksTabContent(viewModel.collectAsState(),viewModel::onEventDispatcher)
    }
}

@Preview
@Composable
fun AllBooksTabPrev() {
    KitoblarUzbEngTheme {
        AllBooksTabContent(remember { mutableStateOf(AllBooksContract.UIState()) },{})
    }
}

@Composable
fun AllBooksTabContent(
    uiState:State<AllBooksContract.UIState>,
    onEventDispatcher:(AllBooksContract.Intent) ->Unit
) {
    var selectedTab by remember { mutableIntStateOf(0) }
//    val tabs = remember { listOf("Art", "Business", "Comedy", "Drama", "Fiction") }

    println("allBooks ${uiState.value.allBooks}")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Text(
            text = stringResource(R.string.txt_categories),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(16.dp)
        )

        ScrollableTabRow(
            selectedTabIndex = selectedTab,
            indicator = {},
            divider = { },
            edgePadding = 0.dp,
            containerColor = Color.Black
        ) {
            Categories.entries.forEachIndexed { index, s ->
                Box(modifier = Modifier.fillMaxHeight()) {
                    // Add the tab
                    Tab(
                        selected = selectedTab == index,
                        onClick = {
                            selectedTab = index
                            onEventDispatcher(AllBooksContract.Intent.CategoryClick(Categories.entries[index].name))
                        },
                        text = {
                            Text(
                                text = s.name,
                                style = MaterialTheme.typography.bodyMedium
                                    .copy(color = if (selectedTab == index) Main else Color.White)
                            )
                        },
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )

                    if (index < Categories.entries.size-1) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(1.dp)
                                .background(Color.White)
                                .align(Alignment.CenterEnd) // Align the divider to the right side of the tab
                        )
                    }
                }
            }

        }

        if (selectedTab == 0){


            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                item(span = { GridItemSpan(2) }) {
                    Text(
                        text = stringResource(R.string.txt_recommended_for_you),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
                    )
                }

                item(span = { GridItemSpan(2) }) {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(3 / 2.6f),
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(uiState.value.allBooks
                            .filterIndexed { index, book ->  index % 3 == 0 }
                        ) {book->
                            Box(
                                modifier = Modifier
                                    .clip(
                                        RoundedCornerShape(
                                            topEnd = 16.dp,
                                            bottomEnd = 16.dp,
                                            topStart = 4.dp,
                                            bottomStart = 4.dp
                                        )
                                    )
                                    .fillMaxWidth()
                                    .border(
                                        width = 1.dp,
                                        color = Color.Black,
                                        shape = RoundedCornerShape(
                                            topEnd = 16.dp,
                                            bottomEnd = 16.dp,
                                            topStart = 4.dp,
                                            bottomStart = 4.dp
                                        )
                                    )
                                    .aspectRatio(4 / 5.5f)
                            ) {
                                Image(
                                    painter = rememberAsyncImagePainter(
                                        model = ImageRequest.Builder(LocalContext.current)
                                            .data(book.image)
                                            .placeholder(R.drawable.book_placeholder)
                                            .error(R.drawable.error_placeholder)
                                            .build()
                                    ),
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
                    }
                }


                item(span = { GridItemSpan(2) }) {
                    Text(
                        text = stringResource(R.string.txt_best_seller),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }

                item(span = { GridItemSpan(2) }) {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(3 / 0.8f),
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(uiState.value.allBooks.filterIndexed { index, book -> book.rating == 5f }) {book->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(2.5f / 1f)
                                    .background(color = Color.Black, shape = RoundedCornerShape(8.dp))
                                    .padding(8.dp),
                            ) {

                                Box(
                                    modifier = Modifier
                                        .clip(
                                            RoundedCornerShape(
                                                topEnd = 4.dp,
                                                bottomEnd = 4.dp,
                                                topStart = 2.dp,
                                                bottomStart = 2.dp
                                            )
                                        )
                                        .border(
                                            width = 1.dp,
                                            color = Color.Black,
                                            shape = RoundedCornerShape(
                                                topEnd = 4.dp,
                                                bottomEnd = 4.dp,
                                                topStart = 2.dp,
                                                bottomStart = 2.dp
                                            )
                                        )
                                        .fillMaxHeight()
                                        .aspectRatio(2.2f / 3f)
                                ) {
                                    Image(
                                        painter = rememberAsyncImagePainter(model = ImageRequest.Builder(LocalContext.current)
                                            .data(book.image)
                                            .placeholder(R.drawable.book_placeholder)
                                            .error(R.drawable.error_placeholder)
                                            .build()),
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

                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxHeight()
                                ) {
                                    Text(
                                        text = book.name,
                                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
                                        modifier = Modifier.padding(start = 16.dp),
                                        overflow = TextOverflow.Ellipsis,
                                        maxLines = 2
                                    )

                                    Text(
                                        text = book.author,
                                        style = MaterialTheme.typography.bodySmall.copy(color = Color.White),
                                        modifier = Modifier.padding(start = 16.dp, top = 4.dp),
                                        overflow = TextOverflow.Ellipsis,
                                        maxLines = 1
                                    )

                                    Spacer(modifier = Modifier.weight(1f))

                                    RatingBar(
                                        modifier = Modifier.padding(start = 16.dp),
                                        rating = book.rating.toDouble()
                                    ) {}
                                }
                            }
                        }
                    }
                }

                item(span = { GridItemSpan(2) }) {
                    Text(
                        text = stringResource(R.string.txt_all),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }

                items(uiState.value.allBooks) {book->
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth()
                            .border(
                                width = 1.dp,
                                color = Color.Black,
                                shape = RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp, topStart = 4.dp, bottomStart = 4.dp)
                            )
                            .aspectRatio(4 / 5.5f)
                            .clip(RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp, topStart = 4.dp, bottomStart = 4.dp))

                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(model = ImageRequest.Builder(LocalContext.current)
                                .data(book.image)
                                .placeholder(R.drawable.book_placeholder)
                                .error(R.drawable.error_placeholder)
                                .build()
                            ),
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

            }



        }else{



            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ){
                items(uiState.value.categoryBook) {book->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                width = 1.dp,
                                color = Color.Black,
                                shape = RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp, topStart = 4.dp, bottomStart = 4.dp)
                            )
                            .aspectRatio(4 / 5.5f)
                            .clip(RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp, topStart = 4.dp, bottomStart = 4.dp))

                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(model = book.image),
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
            }


        }


    }
}

