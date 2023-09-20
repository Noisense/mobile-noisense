package com.example.audiorecorderapp.service

import com.example.audiorecorderapp.db.Recording
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("add_recording")
    fun addRecording(@Body recording: Recording): Call<Map<String, String>>
}
