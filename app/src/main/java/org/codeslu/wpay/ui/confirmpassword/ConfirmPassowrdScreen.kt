package org.codeslu.wpay.ui.confirmpassword

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.codeslu.wpay.R
import org.codeslu.wpay.ui.components.TransparentCenterAlignedTopAppBar
import org.codeslu.wpay.ui.confirmpassword.components.PasswordTextField
import org.codeslu.wpay.ui.notifications.components.OutlinedTopBarIcon
import org.codeslu.wpay.ui.scantopay.ScanToPayUiAction
import org.codeslu.wpay.ui.theme.WPayTheme
import org.codeslu.wpay.ui.theme.notificationHeaderColor
import org.codeslu.wpay.ui.theme.onBackgroundLight
import org.codeslu.wpay.ui.theme.onPrimaryLight
import org.codeslu.wpay.ui.theme.primaryContainerLight

@Composable
fun ConfirmPasswordScreen(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit,
    onPasswordConfirmed: () -> Unit
) {
    ConfirmPasswordScreenContent(
        modifier = modifier,
        onAction = { action ->
            when (action) {
                is ConfirmPasswordUiAction.OnBackClicked -> onBackClicked()
                is ConfirmPasswordUiAction.OnConfirmPasswordClicked -> onPasswordConfirmed()
            }
        }
    )
}

@Composable
fun ConfirmPasswordScreenContent(
    modifier: Modifier = Modifier,
    onAction: (ConfirmPasswordUiAction) -> Unit
) {
    var password by remember{
        mutableStateOf("")
    }
    Scaffold (
        modifier = modifier,
        containerColor = Color.Transparent,
        contentColor = onBackgroundLight,
        topBar = {
            TransparentCenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.confirm_password),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 20.sp,
                        lineHeight = 28.sp,
                    )
                },
                navigationIcon = {
                    OutlinedTopBarIcon(onClick = { onAction(ConfirmPasswordUiAction.OnBackClicked) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = "Navigate back",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            )
        }
    ){ paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            Text(
                text = stringResource(R.string.please_input_your_password_before_continuing_payment),
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 18.sp,
                lineHeight = 25.sp,
                color = notificationHeaderColor
            )
            PasswordTextField(
                onValueChanged = {
                    password = it
                }
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 54.dp),
                shape = RoundedCornerShape(16.dp),
                contentPadding = PaddingValues(vertical = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = primaryContainerLight,
                    contentColor = onPrimaryLight
                ),
                onClick = { onAction(ConfirmPasswordUiAction.OnConfirmPasswordClicked(password)) }
            ) {
                Text(
                    text = stringResource(id = R.string.confirm_password),
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 16.sp,
                    lineHeight = 22.sp,
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun ConfirmPasswordScreenContentPreview() {
    WPayTheme {
        ConfirmPasswordScreenContent(
            onAction = {}
        )
    }
}