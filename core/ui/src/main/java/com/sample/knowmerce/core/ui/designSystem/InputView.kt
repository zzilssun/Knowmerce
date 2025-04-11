package com.sample.knowmerce.core.ui.designSystem

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp

@Composable
fun InputView(
    modifier: Modifier = Modifier,
    initValue: String,
    hint: String? = null,
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    minLines: Int = 1,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    focusRequester: FocusRequester? = null,
    iconContent: @Composable RowScope.(isFocused: Boolean) -> Unit = {},
    onChangeValue: (changedValue: String) -> Unit,
) {
    var isFocused by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        BasicTextField(
            modifier = Modifier
                .then(
                    if (focusRequester != null) {
                        Modifier
                            .focusRequester(focusRequester)
                    } else {
                        Modifier
                    }
                )
                .fillMaxWidth()
                .onFocusChanged { focus ->
                    isFocused = focus.isFocused
                },
            value = initValue,
            onValueChange = onChangeValue,
            readOnly = readOnly,
            singleLine = singleLine,
            minLines = minLines,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            shape = RoundedCornerShape(12.dp),
                        )
                        .padding(
                            vertical = 14.dp,
                            horizontal = 20.dp,
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                    ) {
                        if (initValue.isEmpty()) {
                            Text(
                                text = hint ?: "",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }

                        innerTextField()
                    }

                    iconContent(isFocused)
                }
            },
        )
    }
}

@Preview
@Composable
private fun PreviewInputBoxView(
    @PreviewParameter(PreviewInputViewDataProvider::class) data: PreviewInputViewData,
) {
    Surface {
        InputView(
            modifier = Modifier
                .padding(
                    all = 8.dp,
                ),
            initValue = data.value,
            hint = data.hint,
            readOnly = data.readOnly,
            iconContent = {
                Spacer(modifier = Modifier.width(8.dp))

                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = null,
                )
            },
            onChangeValue = {},
        )
    }
}

private data class PreviewInputViewData(
    val readOnly: Boolean,
    val value: String,
    val hint: String,
)

private class PreviewInputViewDataProvider : PreviewParameterProvider<PreviewInputViewData> {
    override val values: Sequence<PreviewInputViewData> = sequenceOf(
        PreviewInputViewData(
            value = "",
            hint = "힌트의 영역입니다.",
            readOnly = true,
        ),
        PreviewInputViewData(
            value = "값이 있음",
            hint = "힌트의 영역입니다.",
            readOnly = true,
        ),
        PreviewInputViewData(
            value = "",
            hint = "힌트의 영역입니다.",
            readOnly = false,
        ),
        PreviewInputViewData(
            value = "값이 있음",
            hint = "힌트의 영역입니다.",
            readOnly = false,
        ),
        PreviewInputViewData(
            value = "값이 있음",
            hint = "힌트의 영역입니다.",
            readOnly = false,
        ),
        PreviewInputViewData(
            value = "값이 있음",
            hint = "힌트의 영역입니다.",
            readOnly = false,
        ),
    )
}