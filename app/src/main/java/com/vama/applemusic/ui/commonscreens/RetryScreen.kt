package com.vama.applemusic.ui.commonscreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vama.applemusic.R
import com.vama.applemusic.ui.util.ButtonWithLoadingState
import com.vama.applemusic.ui.util.LottieAnimationLoader

@Preview
@Composable
fun RetryScreen(
    isLoading: Boolean = false,
    onRetryClicked: () -> Unit = {}
) {
    LazyColumn(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            LottieAnimationLoader(
                resId = R.raw.retry_lottie_animation
            )

            Text(
                text = stringResource(R.string.something_went_wrong),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )

            ButtonWithLoadingState(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                text = stringResource(R.string.retry).toUpperCase(Locale.current),
                isEnabled = !isLoading,
                isLoading = isLoading,
                onClick = onRetryClicked
            )
        }
    }
}