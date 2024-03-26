package com.keeghan.chatgh.ui.components

import android.graphics.Bitmap
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.keeghan.chatgh.R

@Composable
fun ChatImage(imageUrl: Bitmap) {
    AsyncImage(  //Coil to load images
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .networkCachePolicy(CachePolicy.ENABLED)
            .diskCachePolicy(CachePolicy.ENABLED)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .build(),
        placeholder = painterResource(R.drawable.ic_launcher_background),
        error = painterResource(R.drawable.ic_launcher_background),
        contentScale = ContentScale.Crop,
        contentDescription = "",
        modifier = Modifier.height(200.dp)// height(220.dp).width(220.dp)
    )
}