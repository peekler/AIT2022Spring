package hu.ait.intentdemo

import android.app.SearchManager
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnIntent).setOnClickListener {
            //intentSearch()
            //intentCall()
            //intentShare()
            //intentWaze()
            intentStreetMaps()
        }
    }

    fun intentSearch() {
        val intentSearch = Intent(Intent.ACTION_WEB_SEARCH)
        intentSearch.putExtra(SearchManager.QUERY, "Balaton")
        startActivity(intentSearch)
    }

    private fun intentCall() {
        val intentCall = Intent(Intent.ACTION_DIAL,
            Uri.parse("tel:+36208225581")
        )
        startActivity(intentCall)
    }

    private fun intentShare() {
        val intentSend = Intent(Intent.ACTION_SEND)
        intentSend.type = "text/plain"
        intentSend.`package` = "com.facebook.katana"
        intentSend.putExtra(Intent.EXTRA_TEXT, "share demo")
        startActivity(intentSend)
    }

    private fun intentWaze() {
        //String wazeUri = "waze://?favorite=Home&navigate=yes";
        //val wazeUri = "waze://?ll=40.761043, -73.980545&navigate=yes"
        val wazeUri = "waze://?q=BME&navigate=yes"
        val intentTest = Intent(Intent.ACTION_VIEW)
        intentTest.data = Uri.parse(wazeUri)
        startActivity(intentTest)
    }

    private fun intentStreetMaps() {
        val gmmIntentUri = Uri.parse(
            "google.streetview:cbll=29.9774614,31.1329645&cbp=0,30,0,0,-15")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }

}