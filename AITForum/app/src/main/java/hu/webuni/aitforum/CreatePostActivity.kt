package hu.webuni.aitforum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import hu.webuni.aitforum.data.Post
import hu.webuni.aitforum.databinding.ActivityCreatePostBinding

class CreatePostActivity : AppCompatActivity() {

    companion object {
        const val POSTS_COLLECTION = "posts"
    }

    lateinit var binding: ActivityCreatePostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatePostBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun sendClick(v: View) {
        uploadPost()
    }

    fun uploadPost() {
        val newPost = Post(
            FirebaseAuth.getInstance().currentUser!!.uid,
            FirebaseAuth.getInstance().currentUser!!.email!!,
            binding.etTitle.text.toString(),
            binding.etBody.text.toString(),
            ""
        )

        // "connect" to posts collection (table)
        val postsCollection =
            FirebaseFirestore.getInstance().collection(POSTS_COLLECTION)
        postsCollection.add(newPost)
            .addOnSuccessListener {
                Toast.makeText(this@CreatePostActivity,
                    "Post SAVED", Toast.LENGTH_LONG).show()

                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this@CreatePostActivity,
                    "Error ${it.message}", Toast.LENGTH_LONG).show()
            }


    }

}