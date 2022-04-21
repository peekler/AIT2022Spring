package hu.webuni.aitforum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import hu.webuni.aitforum.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    override fun onResume() {
        super.onResume()

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(
            "demo22@ait.hu", "123456"
        ).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(this, "USER CREATED",
                    Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Failed ${it.exception?.message}",
                    Toast.LENGTH_LONG).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Error: ${it.message}",
                Toast.LENGTH_LONG).show()
        }
    }

}