package com.johncoimbra.tmdbapimvvmkotlin.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.johncoimbra.tmdbapimvvmkotlin.R
import com.johncoimbra.tmdbapimvvmkotlin.databinding.ActivityNowPlayingBinding
import com.johncoimbra.tmdbapimvvmkotlin.listener.MovieListener
import com.johncoimbra.tmdbapimvvmkotlin.model.entity.MovieModel
import com.johncoimbra.tmdbapimvvmkotlin.view.adapter.NowPlayingAdapter
import com.johncoimbra.tmdbapimvvmkotlin.viewmodel.NowPlayingViewModel

class NowPlayingActivity : AppCompatActivity(), MovieListener {

    private val nowPlayingViewModel = NowPlayingViewModel()
    private lateinit var mNowPlayingAdapter: NowPlayingAdapter
    private lateinit var binding: ActivityNowPlayingBinding
    private var language: String = "pt-BR"
    private var page: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(2000)
        binding = ActivityNowPlayingBinding.inflate(layoutInflater)
        setTheme(R.style.Theme_TMDbAPIMVVMKotlin)
        setContentView(binding.root)
        supportActionBar?.hide()

        setUpRecyclerView()
        setUpObservers()
        nowPlayingViewModel.getNowPlaying(language, page++)
    }

    private fun setUpObservers() {
        nowPlayingViewModel.nowPlaying.observe(this, {
            mNowPlayingAdapter.replaceData(it)
        })

        nowPlayingViewModel.nowPlayingError.observe(this, {
            Toast.makeText(this, "Ocorreu um erro na chamado do servidor", Toast.LENGTH_SHORT)
                .show()
        })
    }

    private fun setUpRecyclerView() {
        val recyclerView = binding.recyclerNowPlaying
        mNowPlayingAdapter = NowPlayingAdapter(ArrayList(0), this, this)
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.adapter = mNowPlayingAdapter
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (!recyclerView.canScrollVertically(1)) {
                    nowPlayingViewModel.getNowPlaying(language, page++)
                }
            }
        })
    }

    override fun onMovieItemClicked(position: MovieModel) {
        val mMovieDetails = Intent(this, MovieDetailsActivity::class.java)
        mMovieDetails.putExtra("movieId", position.movie_id)
        mMovieDetails.putExtra("posterPath", position.posterPath)
        mMovieDetails.putExtra("title", position.title)
        mMovieDetails.putExtra("voteCount", position.vote_count)
        mMovieDetails.putExtra("popularity", position.popularity)
        this.startActivity(mMovieDetails)
    }
}