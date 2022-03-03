package hu.ait.highlowgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.ait.highlowgame.databinding.ActivityGameBinding
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    lateinit var binding: ActivityGameBinding
    var generatedNum = 0

    companion object {
        const val KEY_GENERATED = "KEY_GENERATED"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_GENERATED)) {
            generatedNum = savedInstanceState.getInt(KEY_GENERATED)
        } else {
            generateNewNumber()
        }

        binding.btnGuess.setOnClickListener {
            try {
                if (binding.etGuess.text!!.isNotEmpty()) {
                    val myNumber = binding.etGuess.text.toString().toInt()
                    if (myNumber < generatedNum) {
                        binding.tvResult.text = "The number is higher"
                    } else if (myNumber > generatedNum) {
                        binding.tvResult.text = "The number is lower"
                    } else if (myNumber == generatedNum) {
                        binding.tvResult.text = "CONGRATULATIONS!"
                        startActivity(Intent(
                            this, ResultActivity::class.java
                        ))
                    }
                } else {
                    binding.etGuess.error = "This field can not be empty"
                }
            } catch (e: Exception) {
                binding.etGuess.error = "Error: ${e.message}"
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_GENERATED, generatedNum)
    }


    fun generateNewNumber() {
        // 0..2
        generatedNum = Random(System.currentTimeMillis()).nextInt(3)
    }

}