package com.vama.applemusic.ui.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.vama.applemusic.R

@Composable
fun LoadImage(
    url: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop
) {
    SubcomposeAsyncImage(
        model = url,
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier
    ) {
        val state = painter.state
        when (state) {
            is AsyncImagePainter.State.Loading, is AsyncImagePainter.State.Empty -> {
                Box(modifier = modifier.background(Color.LightGray)) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(24.dp)
                            .align(Alignment.Center)
                    )
                }
            }

            is AsyncImagePainter.State.Error -> {
                Image(
                    modifier = modifier,
                    painter = painterResource(id = R.drawable.baseline_broken_image_24),
                    contentDescription = "Error loading album poster"
                )
            }

            else -> {
                SubcomposeAsyncImageContent()
            }
        }
    }
}