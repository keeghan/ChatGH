package com.keeghan.chatgh.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import com.google.ai.client.generativeai.type.Content
import com.keeghan.chatgh.ui.components.InputField
import com.keeghan.chatgh.ui.components.MessageCard

@Composable
fun ChatScreen(
    isLoading: Boolean,
    responses: List<Content>,
    onResetChat: () -> Unit,
    onMessageSent: (String) -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 8.dp)
    ) {
        BoxWithConstraints(Modifier.weight(1F)) {
            // val scrollState = rememberScrollState()
            LazyColumn(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom
            ) {
                items(responses.size) { MessageCard(responses[it]) }
                if (isLoading) {
                    item {
                        CircularProgressIndicator(
                            Modifier
                                .padding(4.dp)
                                .scale(0.6F)
                        )
                    }
                }
            }
            IconButton(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .align(Alignment.BottomEnd),
                onClick = { onResetChat() },
                colors = IconButtonDefaults.iconButtonColors(contentColor = MaterialTheme.colorScheme.primary)
            ) {
                Icon(Icons.Outlined.Refresh, contentDescription = "reset Chat")
            }
        }
        //end of LazyList
        BoxWithConstraints() {
            InputField() {
                onMessageSent(it)
            }
        }
    }
}
