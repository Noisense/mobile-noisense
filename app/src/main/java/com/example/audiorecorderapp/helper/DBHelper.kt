package com.example.audiorecorderapp.helper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.audiorecorderapp.db.Recording

class DBHelper(context: Context) : SQLiteOpenHelper(context, "recordings.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("""
        CREATE TABLE recordings (
            audio_id VARCHAR(100) NOT NULL PRIMARY KEY,
            audio_title VARCHAR(25) NOT NULL,
            audio_timestamp DATETIME,
            audio_path VARCHAR(100) NOT NULL,
            audio_size FLOAT NOT NULL,
            audio_duration VARCHAR(9) NOT NULL
        )
    """)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS recordings")
        onCreate(db)
    }

    fun addRecording(recording: Recording): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put("audio_id", recording.audio_id)
            put("audio_title", recording.audio_title)
            put("audio_timestamp", recording.audio_timestamp) // Anda mungkin ingin mengkonversi ke string terlebih dahulu jika Anda tidak menggunakan tipe data String untuk timestamp.
            put("audio_path", recording.audio_path)
            put("audio_size", recording.audio_size)
            put("audio_duration", recording.audio_duration)
        }
        val result = db.insert("recordings", null, values)
        db.close()
        return result
    }


    // Anda bisa menambahkan fungsi lain seperti mendapatkan semua rekaman, menghapus rekaman, dll.
}