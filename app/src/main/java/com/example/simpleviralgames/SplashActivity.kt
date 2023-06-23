package com.example.simpleviralgames

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
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

class SplashActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
// Check if the device is running Android 11 or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowInsetsController = window.insetsController

            // Hide the navigation bar and make the activity full screen
            windowInsetsController?.hide(WindowInsets.Type.navigationBars())

            // Enable immersive mode to hide status bar
            windowInsetsController?.systemBarsBehavior =
                WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

            // Set the appearance to a light status bar (optional)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            // For devices running Android versions prior to 11
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
        }


        val intent = Intent(this, MainActivity::class.java)

        val apiCall = RetrofitHelper.getInstance().create(apiCall::class.java)
        val gameRepository = gameRepository(apiCall)
        mainViewModel = ViewModelProvider(
            this, MainViewModelFactory(gameRepository)
        ).get(MainViewModel::class.java)

        mainViewModel.gameData.observe(this, Observer { response ->
            response.data.let { data ->
                Log.d("Hello", data.toString())
                title = data.property.name
                val splashImageView: ImageView = findViewById(R.id.imageView)
                val splashImageUrl = data.property.image
                if (splashImageUrl.isNotEmpty()) {
                    Glide.with(this).load(splashImageUrl).into(splashImageView)
                }

                // Create an intent for MainActivity
                // Add extras to the intent
                intent.putExtra("URL", data.url) // Replace "key" with the appropriate key and data.someValue with the actual data you want to pass
                // Start MainActivity
            }
        })

        Handler().postDelayed({
            startActivity(intent)
            finish()
        }, 2500L)


    }
}