package org.codeslu.wpay.ui.permission

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.codeslu.wpay.ui.theme.WPayTheme
import org.codeslu.wpay.ui.theme.onPrimaryLight
import org.codeslu.wpay.ui.theme.onSurfaceLight
import org.codeslu.wpay.ui.theme.primaryLight
import org.codeslu.wpay.ui.theme.surfaceLight

@Composable
fun PermissionNotGrantedScreen(
    modifier: Modifier = Modifier,
    onRequestPermission: () -> Unit,
) {
    Box (
        modifier = modifier.fillMaxSize().background(surfaceLight),
        contentAlignment = Alignment.Center
    ){
        Column (
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Camera permission not granted",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 20.sp,
                lineHeight = 28.sp,
                color = onSurfaceLight,
                textAlign = TextAlign.Center,
            )
            Button(
                colors = ButtonDefaults.buttonColors(
                   containerColor = primaryLight,
                    contentColor = onPrimaryLight
                ),
                onClick = { onRequestPermission() }) {
                Text("Request permission")
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PermissionScreenPreview() {
    WPayTheme {
        PermissionNotGrantedScreen(
            onRequestPermission = {}
        )
    }
}