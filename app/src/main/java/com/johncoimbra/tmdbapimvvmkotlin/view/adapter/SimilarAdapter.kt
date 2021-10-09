package com.johncoimbra.tmdbapimvvmkotlin.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.johncoimbra.tmdbapimvvmkotlin.R
import com.johncoimbra.tmdbapimvvmkotlin.databinding.MovieListLayoutBinding
import com.johncoimbra.tmdbapimvvmkotlin.databinding.MovieListSimilarLayoutBinding
import com.johncoimbra.tmdbapimvvmkotlin.listener.MovieListener
import com.johncoimbra.tmdbapimvvmkotlin.model.entity.MovieModel
import com.johncoimbra.tmdbapimvvmkotlin.model.service.Credentials
import com.squareup.picasso.Picasso

class SimilarAdapter(
    private var mSimilarList: List<MovieModel>,
    private val context: Context? = null,
    private val movieListener: MovieListener
) : RecyclerView.Adapter<SimilarAdapter.SimilarHolder>() {

    private lateinit var binding: MovieListSimilarLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarHolder {
        binding = MovieListSimilarLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return SimilarHolder(binding)
    }

    override fun onBindViewHolder(holder: SimilarHolder, position: Int) {
        val mMoviesSimilar: MovieModel = mSimilarList[position]
        holder.bind(mMoviesSimilar)
        holder.itemView.setOnClickListener {
            movieListener.onMovieItemClicked(mMoviesSimilar)
        }
    }

    override fun getItemCount(): Int = mSimilarList.size

    @SuppressLint("NotifyDataSetChanged")
    fun replaceDataSimilar(mMoviesSimilar: List<MovieModel>) {
        val mListMoviesSimilar = ArrayList<MovieModel>()
        mListMoviesSimilar.addAll(mSimilarList)
        mListMoviesSimilar.addAll(mMoviesSimilar)
        setList(mListMoviesSimilar)
        notifyDataSetChanged()
    }

    private fun setList(mListMoviesSimilar: List<MovieModel>) {
        mSimilarList = mListMoviesSimilar
    }

    class SimilarHolder(binding: MovieListSimilarLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        private val mMovieTitle = binding.txtTitleSimilar
        private val mMovieOverview = binding.txtOverviewSimilar
        private val mMovieImage = binding.imageSimilar

        fun bind(mMovieSimilar: MovieModel) {
            mMovieTitle.text = mMovieSimilar.title
            mMovieOverview.text = mMovieSimilar.overview
            Picasso.get()
                .load(Credentials.BASE_URL_IMAGE + mMovieSimilar.posterPath)
                .fit().centerCrop()
                .into(mMovieImage)
        }
    }
}