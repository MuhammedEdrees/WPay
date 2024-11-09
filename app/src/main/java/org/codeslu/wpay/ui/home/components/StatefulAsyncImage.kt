package org.codeslu.wpay.ui.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImagePainter
import coil3.compose.AsyncImagePainter.State.Empty.painter
import coil3.compose.SubcomposeAsyncImage
import coil3.compose.SubcomposeAsyncImageContent
import org.codeslu.wpay.R
import org.codeslu.wpay.ui.util.shimmerEffect

@Composable
fun StatefulAsyncImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
    shape: Shape = MaterialTheme.shapes.large,
    shadowElevation: Dp = 0.dp,
    contentScale: ContentScale = ContentScale.Crop
) {
    Surface(
        modifier = modifier,
        shadowElevation = shadowElevation,
        shape = shape
    ) {
        SubcomposeAsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = contentScale
        ) {
            val state by painter.state.collectAsState()
            when(state){
                is AsyncImagePainter.State.Success -> {
                    SubcomposeAsyncImageContent()
                }
                is AsyncImagePainter.State.Loading -> {
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .shimmerEffect()
                    )
                }
                is AsyncImagePainter.State.Error -> {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = Color.Gray.copy(alpha = 0.3f)
                    ){
                        Icon(
                            painter = painterResource(id = R.drawable.ic_broken_image),
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(36.dp)
                        )
                    }
                }
                else -> Unit
            }
        }
    }
}