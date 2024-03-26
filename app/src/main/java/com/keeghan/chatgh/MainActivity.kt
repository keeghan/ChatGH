package com.keeghan.chatgh

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.Content
import com.google.ai.client.generativeai.type.asTextOrNull
import com.google.ai.client.generativeai.type.content
import com.keeghan.chatgh.ui.screens.ChatScreen
import com.keeghan.chatgh.ui.theme.ChatGHTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val generativeModel = GenerativeModel(modelName = "gemini-pro", apiKey = BuildConfig.GEMINI_API_KEY)

        setContent {
            val chatHistory = remember { mutableStateListOf<Content>() }
            val coroutineScope = rememberCoroutineScope()
            var isLoading by remember { mutableStateOf(false) }

            val chat = generativeModel.startChat(history = chatHistory.map { it ->
                //include only text as Content for conversation
                content(role = it.role) { text(it.parts.joinToString { it.asTextOrNull().toString() }) }
            })

            ChatGHTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    ChatScreen(responses = chatHistory,
                        isLoading = isLoading,
                        onResetChat = { chatHistory.clear() }
                    ) {
                        //execute conversation when send button is clicked in chatScreen
                        isLoading = true
                        coroutineScope.launch {
                            try {
                                chatHistory.add(content(role = "user") { text(it) })
                                val response = chat.sendMessage(it)
                                response.candidates.forEach { chatHistory.add(it.content) }
                                isLoading = false
                            } catch (e: Exception) {
                                isLoading = false
                                chatHistory.add(content(role = "user") { text(e.message ?: "error unknown") })
                            }
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun ChatText(text: String) {
    Text(
        modifier = Modifier.padding(8.dp),
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.Medium
    )
}