package com.example.simpleviralgames

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.simpleviralgames.api.RetrofitHelper
import com.example.simpleviralgames.api.apiCall
import com.example.simpleviralgames.repository.gameRepository
import com.example.simpleviralgames.viewmodels.MainViewModel
import com.example.simpleviralgames.viewmodels.MainViewModelFactory
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

        // Read the API response from the api_response.json file
        val apiResponseFile = File(cacheDir, "api_response.json")
        if (apiResponseFile.exists()) {
            val apiResponse = apiResponseFile.readText()

            try {
                val responseData = JSONObject(apiResponse).getJSONObject("data")
                val appName = responseData.getJSONObject("property").getString("name")
                val splashImageUrl = responseData.getJSONObject("property").getString("image")

                title = appName

                val splashImageView: ImageView = findViewById(R.id.imageView)
                if (splashImageUrl.isNotEmpty()) {
                    Glide.with(this).load(splashImageUrl).into(splashImageView)
                }

                // Create an intent for MainActivity
                // Add extras to the intent
                intent.putExtra(
                    "URL",
                    responseData.getString("url")
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