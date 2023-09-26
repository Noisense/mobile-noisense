package com.example.audiorecorderapp.service

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("add_recording")
    fun addRecording(
        @Field("audio_id") audioId: String,
        @Field("audio_title") audioTitle: String,
        @Field("audio_timestamp") audioTimestamp: String,
        @Field("audio_path") audioPath: String,
        @Field("audio_size") audioSize: Float,
        @Field("audio_duration") audioDuration: String
    ): Call<Map<String, String>>
}
