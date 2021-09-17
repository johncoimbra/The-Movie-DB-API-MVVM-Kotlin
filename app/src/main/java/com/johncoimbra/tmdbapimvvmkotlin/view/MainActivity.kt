package com.johncoimbra.tmdbapimvvmkotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.johncoimbra.tmdbapimvvmkotlin.R
import com.johncoimbra.tmdbapimvvmkotlin.databinding.ActivityMoviesBinding
import com.johncoimbra.tmdbapimvvmkotlin.extensions.addFragment

class MoviesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoviesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        loadFragment(NowPlayingFragment.newInstance())

        binding.bottomMenu.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.nav_now_playing -> {
                    val fragment = NowPlayingFragment.newInstance()
                    loadFragment(fragment)
                    return@setOnItemSelectedListener true
                }

                R.id.nav_search -> {
                    val fragment = SearchFragment.newInstance()
                    loadFragment(fragment)
                    return@setOnItemSelectedListener true
                }

                R.id.nav_favorite -> {
                    val fragment = FavoriteFragment.newInstance()
                    loadFragment(fragment)
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }

    fun loadFragment(mFragmentLoaded: Fragment){
        addFragment(mFragmentLoaded, supportFragmentManager, binding.content)
    }
}