package hu.ait.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import hu.ait.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnReset.setOnClickListener {
            binding.ticTacToeView.resetGame()

        }
    }

    public fun showText(message: String) {
        //Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        binding.tvMessage.text = message
    }

    public fun isFlagModeOn() : Boolean {
        return binding.toggleFlag.isChecked
    }

}