package org.codeslu.wpay.ui.scantopay

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.codeslu.wpay.ui.scantopay.components.CameraPreview
import org.codeslu.wpay.ui.scantopay.components.ScanToPayBottomSheet
import org.codeslu.wpay.ui.scantopay.components.ScanToPayTopBar
import org.codeslu.wpay.ui.scantopay.components.ScannerFrame
import org.koin.androidx.compose.koinViewModel

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
        topBar = {
            ScanToPayTopBar(
                onBackClicked = { onAction(ScanToPayUiAction.OnBackClicked) },
                onHelpClicked = { onAction(ScanToPayUiAction.OnHelpClicked) }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
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