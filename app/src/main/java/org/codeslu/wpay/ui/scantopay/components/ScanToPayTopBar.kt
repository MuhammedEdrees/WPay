package org.codeslu.wpay.ui.scantopay.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import org.codeslu.wpay.ui.theme.onPrimaryLight

@Composable
fun ScanToPayTopBar(
    onBackClicked: () -> Unit,
    onHelpClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTopBarIcon(onClick = { onBackClicked() }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = "Navigate back",
                modifier = Modifier.size(24.dp)
            )
        }
        Text(
            text = stringResource(R.string.scan_to_pay),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 20.sp,
            lineHeight = 28.sp,
            modifier = Modifier.weight(1f)
        )
        OutlinedTopBarIcon(onClick = { onHelpClicked() }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_help),
                contentDescription = "Settings",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}