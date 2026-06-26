package com.lgnb666poemer.alice


import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. 将媒体音量调至最大
        val audioManager = getSystemService(AUDIO_SERVICE) as AudioManager
        val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume, 0)

        // 2. 加载并播放音频（请将 alert_sound 换成你的音频文件名）
        mediaPlayer = MediaPlayer.create(this, R.raw.sound)

        // 3. 设置播放完成监听器
        mediaPlayer.setOnCompletionListener {
            // 播放完毕后，延迟0.5s（500ms）关闭窗口
            Handler(Looper.getMainLooper()).postDelayed({
                finish()
            }, 500)
        }

        // 开始播放
        mediaPlayer.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        // 释放资源，防止内存泄漏
        if (::mediaPlayer.isInitialized) {
            mediaPlayer.release()
        }
    }
}