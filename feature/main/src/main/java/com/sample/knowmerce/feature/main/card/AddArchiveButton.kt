package com.sample.knowmerce.feature.main.card

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sample.knowmerce.core.ui.extensions.rippleClickable

@Composable
internal fun AddArchiveButton(
    modifier: Modifier = Modifier,
    onClickArchive: () -> Unit,
) {
    Icon(
        modifier = modifier
            .padding(
                all = 8.dp,
            )
            .background(
                color = Color.White,
                shape = CircleShape,
            )
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = CircleShape,
            )
            .rippleClickable(
                shape = CircleShape,
                onClick = onClickArchive,
            )
            .padding(
                all = 8.dp,
            ),
        imageVector = Icons.Filled.AddBox,
        contentDescription = "저장",
    )
}

@Preview
@Composable
private fun PreviewAddArchiveButton() {
    Surface {
        AddArchiveButton(
            onClickArchive = {},
        )
    }
}