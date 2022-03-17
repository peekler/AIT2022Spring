package hu.ait.threadandtimerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.ait.threadandtimerdemo.databinding.ActivityMainBinding
import java.lang.Exception
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var enabled = false

    inner class MyThread : Thread() {
        override fun run() {
            while (enabled) {
                runOnUiThread {
                    binding.tvData.append("$")
                }
                sleep(1000)
            }
        }
    }

    inner class MyTimerTask : TimerTask() {
        override fun run() {
            runOnUiThread {
                binding.tvData.append("M")
            }
        }
    }
    lateinit var timer: Timer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
//            if (!enabled) {
//                enabled = true
//                MyThread().start()
//                binding.tvData.append("!")
//            }
            timer = Timer()
            timer.schedule(MyTimerTask(),3000, 1000)
        }

        binding.btnStop.setOnClickListener {
            enabled = false
            timer.cancel()
        }
    }

    override fun onStop() {
        super.onStop()
        enabled = false
        try {
            timer.cancel()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}