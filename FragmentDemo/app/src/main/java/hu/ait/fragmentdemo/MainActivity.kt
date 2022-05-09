package hu.ait.fragmentdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import hu.ait.fragmentdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnMain.setOnClickListener {
            showFragementByTag(FragmentMain.TAG)
        }
        binding.btnDetails.setOnClickListener {
            showFragementByTag(BlankFragment.TAG)
        }
    }

    fun showFragementByTag(tag: String) {
        var fragment : Fragment? = supportFragmentManager.findFragmentByTag(tag)
        if (fragment == null) {
            if (tag == BlankFragment.TAG) {
                fragment = BlankFragment()
            } else {
                fragment = FragmentMain()
            }
        }

        val fragTrans = supportFragmentManager.beginTransaction()
        fragTrans.replace(R.id.fragmentContainer,fragment)
        fragTrans.addToBackStack(null)
        fragTrans.commit()
    }


}