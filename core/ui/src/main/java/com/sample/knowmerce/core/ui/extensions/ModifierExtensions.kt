package com.sample.knowmerce.core.ui.extensions

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow

/**
 * 리플 효과 없는 클릭
 *
 * @param onClick 클릭 이벤트
 */
@Stable
fun Modifier.clickableWithoutRipple(
    onClick: () -> Unit
): Modifier = composed {
    clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null,
        onClick = onClick,
    )
}

/**
 * 리플 클릭 효과 적용
 *
 * @param shape [Shape]             클릭 시 Ripple 모양
 * @param backgroundColor [Color]   클릭 시 Ripple 색상
 * @param usingThrottle [Boolean]   중복 클릭 방지를 사용할 것인가?
 * @param duration [Long]           중복 클릭 방지 지연 시간
 * @param onClick                   클릭 이벤트
 */
@Composable
fun Modifier.rippleClickable(
    shape: Shape,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceDim,
    usingThrottle: Boolean = true,
    duration: Long = 1000L,
    onClick: () -> Unit
): Modifier = composed {
    multipleEventsCutter(
        duration = duration,
    ) { manager ->
        clip(shape)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(
                    bounded = true,
                    color = backgroundColor.getAdjustedRippleColor(),
                ),
                onClick = {
                    if (usingThrottle) {
                        manager.processEvent { onClick() }
                    } else {
                        onClick()
                    }
                },
            )
    }
}

/**
 * 리플 클릭 효과 적용
 *
 * @param radius [Dp]               클릭 시 Ripple 의 [RoundedCornerShape] radius
 * @param backgroundColor [Color]   클릭 시 Ripple 색상
 * @param usingThrottle [Boolean]   중복 클릭 방지를 사용할 것인가?
 * @param duration [Long]           중복 클릭 방지 지연 시간
 * @param onClick                   클릭 이벤트
 */
@Composable
fun Modifier.rippleClickable(
    radius: Dp,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceDim,
    usingThrottle: Boolean = true,
    duration: Long = 1000L,
    onClick: () -> Unit
): Modifier = rippleClickable(
    shape = RoundedCornerShape(radius),
    backgroundColor = backgroundColor,
    usingThrottle = usingThrottle,
    duration = duration,
    onClick = onClick
)

private fun Color.getAdjustedRippleColor(): Color {
    // 확장 함수로 색상을 밝게 또는 어둡게 조정
    fun Color.darken(factor: Float): Color {
        return copy(
            red = (red * (1 - factor)).coerceIn(0f, 1f),
            green = (green * (1 - factor)).coerceIn(0f, 1f),
            blue = (blue * (1 - factor)).coerceIn(0f, 1f)
        )
    }

    fun Color.lighten(factor: Float): Color {
        return copy(
            red = (red + (1 - red) * factor).coerceIn(0f, 1f),
            green = (green + (1 - green) * factor).coerceIn(0f, 1f),
            blue = (blue + (1 - blue) * factor).coerceIn(0f, 1f)
        )
    }

    // 배경색의 명도를 기준으로 리플 색상의 밝기를 조정
    return if (luminance() > 0.5) {
        // 밝은 배경일 경우 약간 더 어두운 색을 리플로 사용
        copy(alpha = 0.2f).darken(0.5f)
    } else {
        // 어두운 배경일 경우 약간 더 밝은 색을 리플로 사용
        copy(alpha = 0.3f).lighten(0.5f)
    }
}

/**
 * Border 처리된 배경 적용
 *
 * @param shape [Shape]         배경 모양
 * @param innerColor [Color]    배경 색상
 * @param borderWidth [Dp]      Border 두께
 * @param borderColor [Color]   Border 색상 / [borderBrush] 과는 중복으로 사용불가
 * @param borderBrush [Brush]   Brush 형태의 Border 색상 / [borderColor] 과는 중복으로 사용불가
 */
fun Modifier.backgroundWithBorder(
    shape: Shape,
    innerColor: Color? = null,
    borderWidth: Dp = 1.dp,
    borderColor: Color? = null,
    borderBrush: Brush? = null,
): Modifier = then(
    Modifier
        .then(
            if (innerColor != null) {
                Modifier
                    .background(
                        color = innerColor,
                        shape = shape,
                    )
            } else {
                Modifier
            }
        )
        .then(
            if (borderColor != null) {
                Modifier
                    .border(
                        width = borderWidth,
                        color = borderColor,
                        shape = shape,
                    )
            } else if (borderBrush != null) {
                Modifier
                    .border(
                        width = borderWidth,
                        brush = borderBrush,
                        shape = shape,
                    )
            } else {
                Modifier
            }
        )
)

/**
 * 키보드 숨김 처리
 */
fun Modifier.hideKeyboard(): Modifier = composed {
    val keyboardController = LocalSoftwareKeyboardController.current
    DisposableEffect(Unit) {
        onDispose {
            keyboardController?.hide()
        }
    }

    clickableWithoutRipple {
        keyboardController?.hide()
    }
}

/**
 * Input 포커스 관리
 *
 * @param nowFocus  현재 포커스
 * @param nextFocus 다음 포커스
 */
@Composable
fun Modifier.focusChange(
    nowFocus: FocusRequester,
    nextFocus: FocusRequester,
): Modifier = then(
    // 상호 작용 소스를 저장
    Modifier
        .onPreviewKeyEvent { event ->
            if (event.key == Key.Tab) {
                when (event.type) {
                    KeyEventType.KeyDown -> {
                        nextFocus.requestFocus()
                        return@onPreviewKeyEvent true
                    }
                }
            }
            return@onPreviewKeyEvent false
        }
        .focusRequester(
            focusRequester = nowFocus,
        ),
)

////////////////////////////////////////////////////////////////////////////////////////////

interface MultipleEventsCutterManager {
    fun processEvent(event: () -> Unit)
}

/**
 * 다중 클릭 방지
 */
@Composable
private fun <T> multipleEventsCutter(
    duration: Long = 1000L,
    content: @Composable (MultipleEventsCutterManager) -> T,
): T {
    val throttleState = remember {
        MutableSharedFlow<() -> Unit>(
            replay = 0,
            extraBufferCapacity = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
    }

    val result = content(
        object : MultipleEventsCutterManager {
            override fun processEvent(event: () -> Unit) {
                throttleState.tryEmit(event)
            }
        }
    )

    LaunchedEffect(true) {
        throttleState
            .throttleFirst(duration)
            .collect { onClick ->
                onClick.invoke()
            }
    }

    return result
}