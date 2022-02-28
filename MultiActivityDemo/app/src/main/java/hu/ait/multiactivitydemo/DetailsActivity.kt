package hu.ait.multiactivitydemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.ait.multiactivitydemo.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // There is always an intent object who starts an activity, and we can ask
        // what is "inside" this intent, does it have an argument (extra), etc.
        if (intent.hasExtra(MainActivity.KEY_DATA)) {
            binding.tvData.text = intent.getStringExtra(MainActivity.KEY_DATA)
            binding.tvData.append(DataManager.data.get(0))
        }
    }
}