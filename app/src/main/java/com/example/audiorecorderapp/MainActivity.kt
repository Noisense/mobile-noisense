package com.example.audiorecorderapp

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.example.audiorecorderapp.playback.AndroidAudioPlayer
import com.example.audiorecorderapp.recorder.AndroidAudioRecorder
import com.example.audiorecorderapp.ui.theme.AudioRecorderAppTheme
import java.io.File

class MainActivity : ComponentActivity() {
    private val recorder by lazy { AndroidAudioRecorder(applicationContext) }
    private val player by lazy { AndroidAudioPlayer(applicationContext) }
    private var audioFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), 0)

        setContent {
            AudioRecorderAppTheme {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 700.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    val context = LocalContext.current
                    Button(
                        onClick = {
                            File(cacheDir, "audio.mp3").also {
                                recorder.start(it)
                                audioFile = it
                                Toast.makeText(context,"Start Recording", Toast.LENGTH_SHORT).show()
                            }
                        },
                        colors = buttonColors(backgroundColor = Red),
                        modifier = Modifier.size(70.dp),
                        shape = CircleShape
                    ) {
                        Icon(
                            imageVector = Icons.Default.Mic,
                            contentDescription = null,
                            tint = White,
                            modifier = Modifier.size(40.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = {
                            recorder.stop()
                            Toast.makeText(context,"Stop Recording", Toast.LENGTH_SHORT).show()
                        },
                        colors = buttonColors(backgroundColor = Red),
                        modifier = Modifier.size(70.dp),
                        shape = CircleShape
                    ) {
//                        Text(text = "Stop Recording", color = White, fontSize = 18.sp)
                        Icon(imageVector = Icons.Default.Stop,
                            contentDescription = null,
                            tint = White,
                            modifier = Modifier.size(40.dp))
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = {
                            player.playFile(audioFile ?: return@Button)
                        },
                        colors = buttonColors(backgroundColor = Red),
                        modifier = Modifier.size(70.dp),
                        shape = CircleShape
                    ) {
//                        Text(text = "Play", color = White, fontSize = 18.sp)
                        Icon(imageVector = Icons.Default.PlayArrow,
                            contentDescription = null,
                            tint = White,
                            modifier = Modifier.size(40.dp))
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = {
                            player.stop()
                        },
                        colors = buttonColors(backgroundColor = Red),
                        modifier = Modifier.size(70.dp),
                        shape = CircleShape
                    ) {
//                        Text(text = "Stop Playing", color = White, fontSize = 18.sp)
                        Icon(imageVector = Icons.Default.Stop,
                            contentDescription = null,
                            tint = White,
                            modifier = Modifier.size(40.dp))
                    }
                }
            }
        }
    }
}
