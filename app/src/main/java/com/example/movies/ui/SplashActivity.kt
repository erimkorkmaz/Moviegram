package com.example.movies.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movies.R
import android.view.Window
import android.view.WindowManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.movies.ViewModel.SplashScreenViewModel


class SplashActivity : AppCompatActivity() {

    private lateinit var splashScreenViewModel: SplashScreenViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.requestFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)



        splashScreenViewModel = ViewModelProviders.of(this).get(SplashScreenViewModel::class.java)

        splashScreenViewModel.isFinished.observe(this, Observer {
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        })
    }
}
