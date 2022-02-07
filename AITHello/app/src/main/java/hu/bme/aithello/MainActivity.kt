package hu.bme.aithello

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnRate = findViewById<Button>(R.id.btnRate)
        val tvData = findViewById<TextView>(R.id.tvData)
        val etRestaurant = findViewById<EditText>(R.id.etRestaurant)


        btnRate.setOnClickListener {
            tvData.text =
                "The ${etRestaurant.text.toString()} restaurant is the BEST!"
        }
    }


}