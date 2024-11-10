package org.codeslu.wpay.ui.scantopay.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.codeslu.wpay.R
import org.codeslu.wpay.ui.theme.WPayTheme

@Composable
fun ScannerFrame(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_scan_frame),
            contentDescription = null,
            tint = Color.Unspecified,
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_scan_center),
            contentDescription = null,
            tint = Color.Unspecified,
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun ScannerFramePreview() {
    WPayTheme {
        ScannerFrame(Modifier.padding(16.dp))
    }
}