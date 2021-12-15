package com.example.rxjava2hw4.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rxjava2hw4.R
import com.example.rxjava2hw4.databinding.ActivityMainBinding
import com.example.rxjava2hw4.mvpcalculation.CalculationScreen
import com.github.terrakok.cicerone.androidx.AppNavigator

class MainActivity : AppCompatActivity(), IMainView {
    private val navigator = AppNavigator(this, R.id.container)

    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        App.instance.router.navigateTo(CalculationScreen)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is IBackButtonListener && it.backPressed()){
                return
            }
        }

    }
}