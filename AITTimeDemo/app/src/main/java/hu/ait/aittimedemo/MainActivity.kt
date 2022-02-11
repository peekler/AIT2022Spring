package hu.ait.aittimedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import hu.ait.aittimedemo.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // this is where we set the LinearLayout for the Activity

        binding.btnTime.setOnClickListener {
            val dateTime = Date(System.currentTimeMillis()).toString()

            val number = binding.etNumber.text.toString().toInt()

            Toast.makeText(this, dateTime,
                Toast.LENGTH_LONG).show()
            binding.tvData.text = "Result is: $number"
        }

    }
}