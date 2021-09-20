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
import com.johncoimbra.tmdbapimvvmkotlin.listener.MovieListener
import com.johncoimbra.tmdbapimvvmkotlin.model.entity.MovieModel
import com.johncoimbra.tmdbapimvvmkotlin.model.service.Credentials
import com.squareup.picasso.Picasso

class SimilarAdapter(
    var mSimilarList: List<MovieModel>,
    private val context: Context? = null,
    val movieListener: MovieListener
) :
    RecyclerView.Adapter<SimilarAdapter.SimilarHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarHolder {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.movie_list_similar_layout, parent, false)
        return SimilarHolder(view)
    }

    override fun onBindViewHolder(holder: SimilarHolder, position: Int) {
        val mMoviesSimilar: MovieModel = mSimilarList[position]

        holder.mMovieTitle.text = mMoviesSimilar.title
        holder.mMovieOverview.text = mMoviesSimilar.overview

        Picasso.get()
            .load(Credentials.BASE_URL_IMAGE + mMoviesSimilar.posterPath)
            .fit().centerCrop()
            .into(holder.mMovieImage)

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

    class SimilarHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mMovieTitle: TextView = itemView.findViewById(R.id.txt_title_similar)
        val mMovieOverview: TextView = itemView.findViewById(R.id.txt_overview_similar)
        val mMovieImage: ImageView = itemView.findViewById(R.id.image_similar)
    }
}