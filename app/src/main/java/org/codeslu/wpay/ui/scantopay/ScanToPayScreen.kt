package org.codeslu.wpay.ui.scantopay

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.codeslu.wpay.R
import org.codeslu.wpay.ui.notifications.components.OutlinedTopBarIcon
import org.codeslu.wpay.ui.scantopay.components.CameraPreview
import org.codeslu.wpay.ui.scantopay.components.ScanToPayBottomSheet
import org.codeslu.wpay.ui.scantopay.components.ScannerFrame
import org.codeslu.wpay.ui.components.TransparentCenterAlignedTopAppBar
import org.codeslu.wpay.ui.theme.onPrimaryLight

@Composable
fun ScanToPayScreen(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit,
) {
    ScanToPayScreenContent(
        onAction = { action ->
            when (action) {
                ScanToPayUiAction.OnBackClicked -> onBackClicked()
                else -> Unit
            }
        }
    )
}

@Composable
private fun ScanToPayScreenContent(
    modifier: Modifier = Modifier,
    onAction: (ScanToPayUiAction) -> Unit,
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        containerColor = Color.Transparent,
        contentColor = onPrimaryLight,
        topBar = {
            TransparentCenterAlignedTopAppBar(
                navigationIcon = {
                    OutlinedTopBarIcon(onClick = { onAction(ScanToPayUiAction.OnBackClicked) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = "Navigate back",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                title = {
                    Text(
                        text = stringResource(R.string.scan_to_pay),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 20.sp,
                        lineHeight = 28.sp,
                    )
                },
                actions = {
                    OutlinedTopBarIcon(onClick = { onAction(ScanToPayUiAction.OnHelpClicked) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_help),
                            contentDescription = "Help",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(top = paddingValues.calculateTopPadding())
                .fillMaxSize()

        ) {
            CameraPreview()
            ScannerFrame(
                modifier = Modifier
                    .padding(horizontal = 37.dp)
                    .padding(top = 64.dp)
                    .align(Alignment.TopCenter)
            )
            ScanToPayBottomSheet(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
            )
        }
    }
}