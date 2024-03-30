package com.avility.shared.ui.components.elements_forms

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.avility.shared.ui.constants.MeasureSmallDimen
import com.avility.shared.ui.constants.roundedShapes

@Composable
fun StoriButton(
    text: String,
    containerColor: Color = MaterialTheme.colorScheme.secondary,
    enabled: Boolean = true,
    onTap: () -> Unit = {}
) {
    Button(
        onClick = {
            onTap()
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(MeasureSmallDimen.DIMEN_X23.value),
        shape = roundedShapes.medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            disabledContainerColor = containerColor.copy(alpha = 0.5f)
        ),
        enabled = enabled
    ) {
        Text(text)
    }
}