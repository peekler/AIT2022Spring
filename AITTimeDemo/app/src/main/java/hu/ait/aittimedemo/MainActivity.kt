package hu.ait.aittimedemo

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.android.material.snackbar.Snackbar
import hu.ait.aittimedemo.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // this is where we set the LinearLayout for the Activity

        binding.btnTime.setOnClickListener {
            Log.d("TAG_ACTIVITY", "btnTime was pressed")

            val dateTime = Date(System.currentTimeMillis()).toString()

            //Toast.makeText(this, dateTime,
            //    Toast.LENGTH_LONG).show()
            // dateTime will replace the %1$s in the text_time_result string resource
            binding.tvData.text = getString(R.string.text_time_result, dateTime)

            Snackbar.make(binding.root, dateTime, Snackbar.LENGTH_LONG)
                .setAction("Ok") {
                    binding.tvData.text = "SNACKBAR"
                }
                .show()

            revealCard()

            val myTesla = Car("demo","Tesla model 3")
            myTesla.print()

            val myVehicle = Vehicle("hello")
        }
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun revealCard() {
        val x = binding.cardView.getRight()
        val y = binding.cardView.getBottom()

        val startRadius = 0
        val endRadius = Math.hypot(binding.cardView.getWidth().toDouble(),
            binding.cardView.getHeight().toDouble()).toInt()

        val anim = ViewAnimationUtils.createCircularReveal(
            binding.cardView,
            x,
            y,
            startRadius.toFloat(),
            endRadius.toFloat()
        )

        binding.cardView.setVisibility(View.VISIBLE)
        anim.start()
    }


}