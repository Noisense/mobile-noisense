package com.example.audiorecorderapp.db

data class Recording(
    val id: Int = 0,
    val audio_id: String,
    val audio_title: String,
    val audio_timestamp: String,
    val audio_path: String,
    val audio_size: Float,
    val audio_duration: String)

