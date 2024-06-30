@file:OptIn(ExperimentalMaterial3AdaptiveApi::class)

package com.vama.applemusic.ui.albumsfeed

import androidx.activity.compose.BackHandler
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vama.applemusic.R
import com.vama.applemusic.ui.commonscreens.RetryScreen
import com.vama.applemusic.ui.model.AlbumUiModel
import com.vama.applemusic.ui.util.LoadImage
import kotlinx.collections.immutable.PersistentList


@Composable
fun AlbumsFeedScreen(
    viewModel: AlbumsFeedViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        when (val state = uiState) {
            is AlbumsFeedUiState.Error, AlbumsFeedUiState.Retrying -> {
                RetryScreen(
                    isLoading = state is AlbumsFeedUiState.Retrying,
                    onRetryClicked = {
                        viewModel.setIntent(
                            AlbumsFeedUiIntent.OnRetryClicked
                        )
                    }
                )
            }

            is AlbumsFeedUiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            is AlbumsFeedUiState.Success -> {
                AlbumsGrid(
                    albums = state.albumsFeed.feeds,
                    copyright = state.albumsFeed.copyright
                )
            }
        }
    }
}

@Composable
private fun AlbumsGrid(
    albums: PersistentList<AlbumUiModel>,
    copyright: String
) {
    val navigator = rememberListDetailPaneScaffoldNavigator<AlbumUiModel>()

    BackHandler(navigator.canNavigateBack()) {
        navigator.navigateBack()
    }

    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {
            AnimatedPane {
                LazyVerticalGrid(
                    modifier = Modifier.padding(8.dp),
                    columns = GridCells.Fixed(NUM_OF_CELLS_PER_ROW),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(items = albums) { album ->
                        AlbumCard(album = album) {
                            navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, it)
                        }
                    }
                }
            }
        },
        detailPane = {
            AnimatedPane {
                navigator.currentDestination?.content?.let {
                    AlbumDetails(album = it, copyright = copyright)
                }
            }
        }
    )

}

@Composable
private fun AlbumCard(
    album: AlbumUiModel,
    cardBackgroundColor: Color = MaterialTheme.colorScheme.secondary,
    cardContentColor: Color = MaterialTheme.colorScheme.onSecondary,
    onItemClicked: (AlbumUiModel) -> Unit
) {
    Card(
        onClick = { onItemClicked(album) },
        shape = RoundedCornerShape(CornerSize(12.dp)),
        colors = CardColors(
            containerColor = cardBackgroundColor,
            contentColor = cardContentColor,
            disabledContainerColor = cardBackgroundColor,
            disabledContentColor = cardContentColor
        )
    ) {
        Column {
            LoadImage(
                url = album.artworkUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Text(
                text = album.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = album.artistName,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(
                    start = 8.dp,
                    end = 8.dp,
                    bottom = 16.dp
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}


@Composable
private fun AlbumDetails(
    album: AlbumUiModel,
    copyright: String,
    titleTextColor: Color = Color.White,
    titleBackgroundColor: Color = Color.Black
) {
    val uriHandler = LocalUriHandler.current
    LazyColumn {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            ) {
                LoadImage(
                    url = album.artworkUrl,
                    modifier = Modifier.fillMaxSize()
                )

                Text(
                    text = album.name,
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = titleTextColor,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    titleBackgroundColor.copy(alpha = 0.2f),
                                    titleBackgroundColor.copy(alpha = 0.7f),
                                    titleBackgroundColor.copy(alpha = 1f)
                                )
                            )
                        )
                        .padding(16.dp)
                        .align(Alignment.BottomCenter)
                )
            }

            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AlbumDetailsBodyText(
                    title = R.string.artist_name,
                    text = album.artistName
                )

                AlbumDetailsBodyText(
                    title = R.string.genre_name,
                    text = album.genres.joinToString { it.name }
                )

                AlbumDetailsBodyText(
                    title = R.string.release_date,
                    text = album.releaseDate
                )

                AlbumDetailsBodyText(
                    title = R.string.copyright,
                    text = copyright
                )

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    onClick = {
                        uriHandler.openUri(album.url)
                    }) {
                    Text(text = stringResource(R.string.itunes_store))
                }
            }
        }
    }
}

@Composable
private fun AlbumDetailsBodyText(
    @StringRes title: Int,
    text: String
) {
    val titleText = stringResource(id = title)
    val boldText = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append(titleText)
        }
        append(" $text")
    }
    Text(
        text = boldText,
        style = MaterialTheme.typography.bodyLarge
    )
}

private const val NUM_OF_CELLS_PER_ROW = 2