package com.johncoimbra.tmdbapimvvmkotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.johncoimbra.tmdbapimvvmkotlin.R
import com.johncoimbra.tmdbapimvvmkotlin.databinding.ActivityMainBinding
import com.johncoimbra.tmdbapimvvmkotlin.extensions.addFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(2000)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setTheme(R.style.Theme_TMDbAPIMVVMKotlin)
        setContentView(binding.root)
        supportActionBar?.hide()

        loadFragment(NowPlayingFragment.newInstance())
    }

    fun loadFragment(mFragmentLoaded: Fragment) {
        addFragment(mFragmentLoaded, supportFragmentManager, binding.content)
    }
}