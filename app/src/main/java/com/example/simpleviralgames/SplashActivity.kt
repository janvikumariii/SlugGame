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

        val apiCall = RetrofitHelper.getInstance().create(apiCall::class.java)
        val gameRepository = gameRepository(apiCall)
        mainViewModel = ViewModelProvider(
            this, MainViewModelFactory(gameRepository)
        ).get(MainViewModel::class.java)

        mainViewModel.gameData.observe(this, Observer { response ->
            response.data.let { data ->
                Log.d("Hello", data.toString())
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