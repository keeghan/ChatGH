package com.keeghan.chatgh.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

//TextField and Send Button
@Composable
fun InputField(onMessageSent: (String) -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(all = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        var prompt by remember { mutableStateOf("") }
        OutlinedTextField(
            value = prompt,
            onValueChange = { prompt = it },
            placeholder = { Text(text = "your prompt...") },
            modifier = Modifier.weight(1F)
        )
        Spacer(Modifier.width(6.dp))
        IconButton(
            modifier = Modifier.width(48.dp),
            onClick = {
                onMessageSent(prompt)
                prompt = ""
                Log.i("===", "button  pressed ")
            }, colors = IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.primary, contentColor = Color.Black
            )
        ) {
            Icon(Icons.Outlined.ArrowForward, contentDescription = "Send prompt")
        }
    }
}