package com.example.sci_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sci_project.ui.theme.SCI_ProjectTheme


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.ui.unit.dp
import com.example.sci_project.ui.theme.SCI_ProjectTheme

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SCI_ProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ControlScreen()
                }
            }
        }
    }
}





@Composable
fun ControlScreen() {
    val client = OkHttpClient()

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "ESP32 Control")
        Button(onClick = { sendRequest(client, "http://192.168.4.1/gpio/32/on") }) {
            Text(text = "Turn GPIO 32 ON")
        }
        Button(onClick = { sendRequest(client, "http://192.168.4.1/gpio/32/off") }) {
            Text(text = "Turn GPIO 32 OFF")
        }
        Button(onClick = { sendRequest(client, "http://192.168.4.1/gpio/27/on") }) {
            Text(text = "Turn GPIO 27 ON")
        }
        Button(onClick = { sendRequest(client, "http://192.168.4.1/gpio/27/off") }) {
            Text(text = "Turn GPIO 27 OFF")
        }
    }
}

fun sendRequest(client: OkHttpClient, url: String) {
    GlobalScope.launch(Dispatchers.IO) {
        val request = Request.Builder().url(url).build()
        val response = client.newCall(request).execute()
        response.body?.string()?.let { responseString ->
            println(responseString)
        }
    }
}

