package com.keeghan.chatgh.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.ai.client.generativeai.type.BlobPart
import com.google.ai.client.generativeai.type.Content
import com.google.ai.client.generativeai.type.ImagePart
import com.google.ai.client.generativeai.type.TextPart
import com.google.ai.client.generativeai.type.asBlobPartOrNull
import com.google.ai.client.generativeai.type.asImageOrNull
import com.google.ai.client.generativeai.type.asTextOrNull
import com.keeghan.chatgh.ChatText

@Composable
fun MessageCard(content: Content) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            modifier = Modifier
                .align(
                    if (content.role.equals("user")) Alignment.CenterEnd else Alignment.CenterStart
                )
                .fillMaxWidth(0.8F)
        ) {
            Column {
                for (part in content.parts) {
                    when (part) {
                        is TextPart -> ChatText(text = part.text)
                        is BlobPart -> "Blob of type: ${part.mimeType}"
                        is ImagePart -> ChatImage(imageUrl = part.image)
                    }
                    Log.i("===", "partImage: ${part.asImageOrNull()}")
                    Log.i("===", "partBlob: ${part.asBlobPartOrNull()}")
                    Log.i("===", "partText: ${part.asTextOrNull()}")
                }
            }
        }
    }
}