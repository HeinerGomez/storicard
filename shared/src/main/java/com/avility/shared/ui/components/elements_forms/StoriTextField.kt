package com.avility.shared.ui.components.elements_forms

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import com.avility.shared.ui.constants.MeasureSmallDimen
import com.avility.shared.ui.constants.roundedShapes
import com.avility.shared.ui.styles.elements_forms.TextFieldStyle

@Composable
fun StoriTextField(
    style: TextFieldStyle = TextFieldStyle.Standard,
    placeholder: String = "",
    value: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onTextChange: (value: String) -> Unit = {}
) {
    val focusRequester = remember { FocusRequester() }
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(style.dimensionParams.height)
            .padding(MeasureSmallDimen.DIMEN_X00.value)
            .focusRequester(focusRequester),
        value = value,
        onValueChange = onTextChange,
        colors = TextFieldDefaults.colors(
            focusedTextColor = style.contentColor ?: MaterialTheme.colorScheme.onPrimary,
            focusedContainerColor = style.containerColor ?: MaterialTheme.colorScheme.primary,
            unfocusedContainerColor = style.containerColor ?: MaterialTheme.colorScheme.primary,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.secondary,
            unfocusedTextColor = MaterialTheme.colorScheme.outlineVariant
        ),
        textStyle = MaterialTheme.typography.bodyMedium.run {
            style.fontSize?.let {
                copy(fontSize = TextUnit(it, TextUnitType.Sp))
            } ?: this
        },
        placeholder = {
            Text(
                text = placeholder,
                color = style.contentColor ?: MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodyMedium.run {
                    style.fontSize?.let {
                        copy(fontSize = TextUnit(it, TextUnitType.Sp))
                    } ?: this
                }
            )
        },
        isError = false,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        singleLine = true,
        shape = roundedShapes.large,
        visualTransformation = visualTransformation
    )
}

@Composable
@Preview(showBackground = true)
fun StoriTextFieldPreview() {
    StoriTextField(
        style = TextFieldStyle.Standard,
        placeholder = "Correo Electronico"
    )
}