package hu.ait.highlowgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import hu.ait.highlowgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            startActivity(Intent(this, GameActivity::class.java))
        }

        binding.btnAbout.setOnClickListener {
            Toast.makeText(this, "About...", Toast.LENGTH_SHORT).show()
        }
        binding.btnHelp.setOnClickListener {
            Toast.makeText(this, "Help...", Toast.LENGTH_SHORT).show()
        }
    }
}