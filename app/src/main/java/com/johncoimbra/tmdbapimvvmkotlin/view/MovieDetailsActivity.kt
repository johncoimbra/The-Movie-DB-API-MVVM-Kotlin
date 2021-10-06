package com.johncoimbra.tmdbapimvvmkotlin.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.johncoimbra.tmdbapimvvmkotlin.R
import com.johncoimbra.tmdbapimvvmkotlin.databinding.ActivityMovieDetailsBinding
import com.johncoimbra.tmdbapimvvmkotlin.listener.MovieListener
import com.johncoimbra.tmdbapimvvmkotlin.model.entity.MovieModel
import com.johncoimbra.tmdbapimvvmkotlin.model.service.Credentials
import com.johncoimbra.tmdbapimvvmkotlin.view.adapter.SimilarAdapter
import com.johncoimbra.tmdbapimvvmkotlin.viewmodel.SimilarViewModel
import com.squareup.picasso.Picasso
import java.text.DecimalFormat

class MovieDetailsActivity : AppCompatActivity(), MovieListener {

    private val similarViewModel = SimilarViewModel()
    private lateinit var mSimilarAdapter: SimilarAdapter
    private var language: String = "pt-BR"
    private var page: Int = 1
    private var movieId: Int = 0
    private var isFavorite: Boolean = false
    private lateinit var binding: ActivityMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        changeButtonFavorite()
        getDataIntent()
        setUpRecyclerView()
        setUpObservers()

        similarViewModel.getSimilar(movieId, language, page++)
    }

    private fun setUpObservers() {
        similarViewModel.similar.observe(this, Observer {
            Log.v("Similar", "Dados recebidos com sucesso")
            mSimilarAdapter.replaceDataSimilar(it)
        })

        similarViewModel.similarError.observe(this, Observer {
            Toast.makeText(this, "Ocorreu um erro na chamado do servidor", Toast.LENGTH_SHORT)
                .show()
        })
    }

    private fun setUpRecyclerView() {
        val recyclerView = binding.recyclerViewSimilar
        mSimilarAdapter = SimilarAdapter(ArrayList(0), this, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mSimilarAdapter
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (!recyclerView.canScrollVertically(1)) {
                    similarViewModel.getSimilar(movieId, language, page++)
                }
            }
        });
    }

    private fun changeButtonFavorite() {
        val imgButton = binding.favBtn
        imgButton.setOnClickListener {
            isFavorite = !isFavorite
            if (!isFavorite) {
                imgButton.setImageResource(R.drawable.ic_fav_off)
            } else {
                imgButton.setImageResource(R.drawable.ic_fav_on)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun getDataIntent() {
        intent.extras?.let {
            movieId = it.getInt("movieId")
            val title = it.getString("title")
            val image = it.getString("posterPath")
            val voteCounte = it.getInt("voteCount")
            val popularity = it.getDouble("popularity")

            val dec = DecimalFormat("####.0")

            binding.textViewTitleDetails.text = title
            binding.textVoteCount.text = "$voteCounte Likes"
            binding.textPopularity.text = "Popularity: ${dec.format(popularity)}"

            Picasso.get()
                .load(Credentials.BASE_URL_IMAGE + image)
                .fit()
                .centerCrop()
                .into(binding.imageViewDetails)
        }
    }

    override fun onMovieItemClicked(movieModel: MovieModel) {
        val mMovieDetails = Intent(this, MovieDetailsActivity::class.java)
        mMovieDetails.putExtra("movieId", movieModel.movie_id)
        mMovieDetails.putExtra("posterPath", movieModel.posterPath)
        mMovieDetails.putExtra("title", movieModel.title)
        mMovieDetails.putExtra("voteCount", movieModel.vote_count)
        mMovieDetails.putExtra("popularity", movieModel.popularity)
        this.startActivity(mMovieDetails)
    }
}