package com.example.simpleviralgames

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.simpleviralgames.viewmodels.MainViewModel
import org.json.JSONException
import org.json.JSONObject
import java.io.File

class SplashActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                )

        // Make the activity full screen
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val intent = Intent(this, MainActivity::class.java)
        val image = System.getenv("IMAGE")
        val url = System.getenv("URL")

            try {
                val splashImageView: ImageView = findViewById(R.id.imageView)
                if (image != null) {
                    if (image.isNotEmpty()) {
                        Glide.with(this).load(image).into(splashImageView)
                    }
                }

                // Create an intent for MainActivity
                // Add extras to the intent
                intent.putExtra(
                    "URL",url
                ) // Replace "URL" with the appropriate key and responseData.getString("url") with the actual data you want to pass
                // Start MainActivity
                startActivity(intent)
                finish()
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            Handler().postDelayed({
                startActivity(intent)
                finish()
            }, 2500L);
        }
    }

}
