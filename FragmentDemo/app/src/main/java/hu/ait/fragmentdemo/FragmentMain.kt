package hu.ait.fragmentdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class FragmentMain : Fragment() {

    companion object {
        const val TAG = "MainFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_main,container,false)

        rootView.findViewById<Button>(R.id.btnFragmentDemo).setOnClickListener {
            (activity as MainActivity).showFragementByTag(BlankFragment.TAG)
        }

        return rootView
    }
}