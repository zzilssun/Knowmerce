package com.sample.knowmerce.feature.main.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.sample.knowmerce.feature.main.card.models.SearchViewData
import com.sample.knowmerce.feature.main.card.models.sampleSearchViewDataImages
import java.util.Locale

@Composable
internal fun SearchImageCardView(
    modifier: Modifier = Modifier,
    image: SearchViewData.Image,
    onClickArchive: (() -> Unit)? = null,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                all = 8.dp,
            ),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
            ) {
                AsyncImage(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            color = MaterialTheme.colorScheme.surfaceDim,
                        )
                        .aspectRatio(1f),
                    model = image.thumbnail,
                    contentScale = ContentScale.Crop,
                    contentDescription = image.collection,
                )

                Text(
                    modifier = Modifier
                        .padding(
                            all = 8.dp,
                        )
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = RoundedCornerShape(4.dp),
                        )
                        .padding(
                            all = 4.dp,
                        ),
                    text = image.collection.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            }

            Text(
                text = image.siteName,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.tertiary,
            )
        }

        onClickArchive?.let {
            AddArchiveButton(
                modifier = Modifier
                    .align(Alignment.TopEnd),
                onClickArchive = onClickArchive,
            )
        }
    }
}

@Preview
@Composable
private fun PreviewSearchImageCardView() {
    Surface {
        SearchImageCardView(
            image = sampleSearchViewDataImages.first(),
            onClickArchive = {},
        )
    }
}