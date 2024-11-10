package org.codeslu.wpay.ui.confirmpassword.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.codeslu.wpay.R
import org.codeslu.wpay.ui.theme.WPayTheme
import org.codeslu.wpay.ui.theme.notificationHeaderColor
import org.codeslu.wpay.ui.theme.segmentedBarBackground

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    onValueChanged: (String) -> Unit
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    var value by remember { mutableStateOf("") }
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = stringResource(R.string.password),
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 16.sp,
            lineHeight = 22.sp,
            color = notificationHeaderColor,
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = value,
            onValueChange = {
                value = it
                onValueChanged(it)
            },
            shape = RectangleShape,
            visualTransformation = if (isPasswordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(
                        painterResource(id = if (isPasswordVisible) R.drawable.ic_visible else R.drawable.ic_unvisible),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
            }
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            HorizontalDivider(
                thickness = 2.dp,
                color = segmentedBarBackground
            )
            Text(
                text = stringResource(R.string.must_be_at_least_8_characters),
                style = MaterialTheme.typography.bodySmall,
                fontSize = 14.sp,
                lineHeight = 19.sp,
                color = notificationHeaderColor
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PasswordTextFieldPreview() {
    WPayTheme {
        PasswordTextField (modifier = Modifier.padding(horizontal = 16.dp)){
            // do nothing
        }
    }
}