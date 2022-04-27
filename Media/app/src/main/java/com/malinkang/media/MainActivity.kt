package com.malinkang.media

import android.media.MediaMetadataRetriever
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlin.concurrent.thread
import wseemann.media.FFmpegMediaMetadataRetriever


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btn).setOnClickListener {
            thread {
                getDurationFromUrl2(URL)
            }
        }


    }

    /**
     * 方法1
     */
    private fun getDurationFromUrl1(url: String) {
        val start = System.currentTimeMillis()
        val retriever = MediaMetadataRetriever()
        retriever.setDataSource(url, HashMap())
        val time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
        val timeInMillisec = time!!.toLong()

        retriever.release()
        val duration: String = convertMillieToHMmSs(timeInMillisec) //use this duration
        val end = System.currentTimeMillis()
        Log.d(TAG,"duration is $duration")
        Log.d(TAG,"cost ${end-start} ms")
    }

    private fun getDurationFromUrl2(url: String) {
        val start = System.currentTimeMillis()
        val mFFmpegMediaMetadataRetriever = FFmpegMediaMetadataRetriever()
        mFFmpegMediaMetadataRetriever.setDataSource(url)
        val mVideoDuration: String =
            mFFmpegMediaMetadataRetriever.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_DURATION)
        val mTimeInMilliseconds: Long = mVideoDuration.toLong()
        val duration: String = convertMillieToHMmSs(mTimeInMilliseconds) //use this duration

        val end = System.currentTimeMillis()
        Log.d(TAG,"duration is $duration")
        Log.d(TAG,"cost ${end-start} ms")

    }

    private fun convertMillieToHMmSs(millie: Long): String {
        val seconds = millie / 1000
        val second = seconds % 60
        val minute = seconds / 60 % 60
        val hour = seconds / (60 * 60) % 24
        val result = ""
        return if (hour > 0) {
            String.format("%02d:%02d:%02d", hour, minute, second)
        } else {
            String.format("%02d:%02d", minute, second)
        }
    }

    companion object {
        const val TAG = "MainActivity"
        const val URL = "https://cdn.malinkang.com/videos/1650795940196223.mp4"
    }
}