package hu.ait.simplecalculator.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.ait.simplecalculator.R
import hu.ait.simplecalculator.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.btnPlus.setOnClickListener {
            try {
                if (checkInputFields()) {
                    val numA = binding.etNumA.text.toString().toInt()
                    val numB = binding.etNumB.text.toString().toInt()
                    val result = numA + numB
                    binding.tvResult.text = "$result"
                }
            } catch (e: Exception) {
                binding.tvResult.text = "Error: ${e.message}"
            }
        }

        binding.btnMinus.setOnClickListener {
            try {
                if (checkInputFields()) {
                    val numA = binding.etNumA.text.toString().toInt()
                    val numB = binding.etNumB.text.toString().toInt()
                    val result = numA - numB
                    binding.tvResult.text = "$result"
                }
            } catch (e: Exception) {
                binding.tvResult.text = "Error: ${e.message}"
            }
        }
    }

    fun checkInputFields(): Boolean {
        if (binding.etNumA.text.isEmpty()) {
            binding.etNumA.error = "This field can not be empty"
            return false
        }
        if (binding.etNumB.text.isEmpty()) {
            binding.etNumB.error = "This field can not be empty"
            return false
        }

        return true
    }

}