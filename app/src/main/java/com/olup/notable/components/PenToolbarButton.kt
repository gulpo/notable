package com.olup.notable.components

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import com.olup.notable.utils.*

@Composable
fun PenToolbarButton(
    pen: Pen,
    icon: Int,
    isSelected: Boolean,
    onSelect: () -> Unit,
    sizes: List<Pair<String, Float>>,
    penSetting: PenSetting,
    onChangeSetting: (PenSetting) -> Unit,
    onStrokeMenuOpenChange: ((Boolean) -> Unit)? = null
) {
    var isStrokeMenuOpen by remember { mutableStateOf(false) }

    if(onStrokeMenuOpenChange != null){
        LaunchedEffect(isStrokeMenuOpen) {
            onStrokeMenuOpenChange(isStrokeMenuOpen)
        }
    }


    Box {

        ToolbarButton(
            isSelected = isSelected,
            onSelect = {
                if (isSelected) isStrokeMenuOpen = !isStrokeMenuOpen
                else onSelect()
            },
            iconId = icon,
            contentDescription = pen.penName
        )

        if (isStrokeMenuOpen) {
            StrokeMenu(value = penSetting, onChange = { onChangeSetting(it) }, onClose = {isStrokeMenuOpen = false}, options = sizes)
        }
    }
}