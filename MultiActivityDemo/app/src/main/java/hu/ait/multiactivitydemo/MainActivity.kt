package hu.ait.multiactivitydemo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import hu.ait.multiactivitydemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        const val KEY_DATA = "KEY_DATA"
        const val KEY_ANS = "KEY_ANS"
    }

    lateinit var binding: ActivityMainBinding

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            Toast.makeText(this, "Result: ${intent?.getStringExtra(KEY_ANS)}",
                Toast.LENGTH_LONG).show()
        } else if (result.resultCode == Activity.RESULT_CANCELED) {
            Toast.makeText(this, "CANCELLED", Toast.LENGTH_LONG).show()
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDetails.setOnClickListener {
            val intentDetails = Intent()
            intentDetails.setClass(this,
                DetailsActivity::class.java)

            intentDetails.putExtra(KEY_DATA,
                binding.etData.text.toString())
            DataManager.data.add("data3")

            intentDetails.setAction("ACTION_JUHUUU")
            sendBroadcast(intentDetails)

            //startActivity(intentDetails)
            startForResult.launch(intentDetails)
            //finish()

        }
    }
}