package hu.ait.musicplayerdemo

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.ait.musicplayerdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MediaPlayer.OnPreparedListener {

    lateinit var binding: ActivityMainBinding

    private var musicPalyer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPlay.setOnClickListener {
            musicPalyer = MediaPlayer.create(this,R.raw.demo)
            musicPalyer?.setOnPreparedListener(this)
        }
        binding.btnStop.setOnClickListener {
            //if (musicPalyer!=null) {
              //  musicPalyer?.stop()
            //}

            musicPalyer?.seekTo(60000)
        }

    }

    override fun onPrepared(p0: MediaPlayer?) {
        // This method will be called when the music is ready to play
        musicPalyer?.start()
    }

    override fun onStop() {
        super.onStop()
        if (musicPalyer!=null) {
            musicPalyer?.stop()
        }
    }
}