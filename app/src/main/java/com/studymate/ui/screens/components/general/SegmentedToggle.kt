package com.studymate.ui.screens.components.general

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.studymate.ui.theme.StudyMateTheme
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

/**
 * A single option of the segmented toggle.
 *
 * @property label Text of the option
 * @property icon Optional icon displayed before the text
 */
data class ToggleOption(
    val label: String,
    val icon: ImageVector? = null
)

private val ContainerPadding = 5.dp
private val ItemSpacing = 4.dp
private val MaxLift = 3.dp

/**
 * Segmented toggle with a sliding "pill".
 *
 * The container must have a bounded width (e.g. [Modifier.fillMaxWidth] or a fixed width).
 *
 * @param modifier Modifier applied to the container
 * @param options List of options (usually 2–3)
 * @param selectedIndex Index of the currently selected option
 * @param onSelect Callback invoked with the index of the clicked option
 * @param containerShape Shape of the container
 * @param itemShape Shape of the pill and of the ripple of each item
 * @param containerColor Background color of the container
 * @param selectedColor Color of the sliding pill
 * @param selectedContentColor Text/icon color of the selected item
 * @param unselectedContentColor Text/icon color of the unselected items
 */
@Composable
fun SegmentedToggle(
    modifier: Modifier = Modifier,
    options: List<ToggleOption>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    containerShape: RoundedCornerShape = RoundedCornerShape(16.dp),
    itemShape: RoundedCornerShape = RoundedCornerShape(12.dp),
    containerColor: Color = MaterialTheme.colorScheme.surfaceContainerHigh,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    selectedContentColor: Color = MaterialTheme.colorScheme.onPrimary,
    unselectedContentColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
) {
    // Lift amount in dp (0 = flat, MaxLift = fully raised)
    val lift = remember { Animatable(MaxLift.value) }
    var isFirstComposition by remember { mutableStateOf(true) }

    // On every selection change: put the pill down, wait for the slide, then lift it up
    LaunchedEffect(selectedIndex) {
        if (isFirstComposition) {
            isFirstComposition = false
            return@LaunchedEffect
        }
        lift.animateTo(0f, tween(durationMillis = 100))
        delay(180.milliseconds)
        lift.animateTo(
            targetValue = MaxLift.value,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
    }

    BoxWithConstraints(
        modifier = modifier
            .clip(containerShape)
            .background(containerColor)
            .padding(ContainerPadding)
    ) {
        val itemWidth = (maxWidth - ItemSpacing * (options.size - 1)) / options.size

        val pillOffset by animateDpAsState(
            targetValue = (itemWidth + ItemSpacing) * selectedIndex,
            animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
            label = "pill_offset"
        )

        Box(modifier = Modifier.height(IntrinsicSize.Min)) {
            // Sliding pill (drawn under the items)
            Box(
                modifier = Modifier
                    .offset { IntOffset(pillOffset.roundToPx(), 0) }
                    .width(itemWidth)
                    .fillMaxHeight()
                    .graphicsLayer {
                        shadowElevation = lift.value.dp.toPx()
                        shape = itemShape
                        clip = false
                    }
                    .background(selectedColor, itemShape)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectableGroup(),
                horizontalArrangement = Arrangement.spacedBy(ItemSpacing),
                verticalAlignment = Alignment.CenterVertically
            ) {
                options.forEachIndexed { index, option ->
                    ToggleItem(
                        option = option,
                        selected = index == selectedIndex,
                        shape = itemShape,
                        selectedContentColor = selectedContentColor,
                        unselectedContentColor = unselectedContentColor,
                        onClick = { onSelect(index) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
private fun ToggleItem(
    modifier: Modifier = Modifier,
    option: ToggleOption,
    selected: Boolean,
    shape: RoundedCornerShape,
    selectedContentColor: Color,
    unselectedContentColor: Color,
    onClick: () -> Unit,
) {
    val contentColor by animateColorAsState(
        targetValue = if (selected) selectedContentColor else unselectedContentColor,
        animationSpec = tween(durationMillis = 300),
    )

    Row(
        modifier = modifier
            .clip(shape)
            .selectable(
                selected = selected,
                onClick = onClick,
                role = Role.RadioButton
            )
            .padding(horizontal = 12.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (option.icon != null) {
            Icon(
                imageVector = option.icon,
                contentDescription = null,
                tint = contentColor,
                modifier = Modifier.size(18.dp)
            )
            Spacer(Modifier.width(6.dp))
        }

        Text(
            text = option.label,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.SemiBold,
            color = contentColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview(group = "Segmented Toggle")
@Composable
fun SegmentedToggleLightPreview() {
    StudyMateTheme {
        Surface {
            var selected by remember { mutableIntStateOf(1) }
            SegmentedToggle(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                options = listOf(
                    ToggleOption("1 teacher", Icons.Outlined.Person),
                    ToggleOption("2 teachers", Icons.Outlined.People)
                ),
                selectedIndex = selected,
                onSelect = { selected = it }
            )
        }
    }
}

@Preview(group = "Segmented Toggle")
@Composable
fun SegmentedToggleDarkPreview() {
    StudyMateTheme(darkTheme = true) {
        Surface {
            var selected by remember { mutableIntStateOf(0) }
            SegmentedToggle(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                options = listOf(ToggleOption("Current"), ToggleOption("Next")),
                selectedIndex = selected,
                onSelect = { selected = it }
            )
        }
    }
}