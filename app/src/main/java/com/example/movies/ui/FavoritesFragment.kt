package com.example.movies.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.R
import com.example.movies.ViewModel.MoviesViewModel
import com.example.movies.model.Movie
import com.example.movies.ui.MainActivity.Companion.database
import kotlinx.android.synthetic.main.fragment_favorites.*
import kotlinx.android.synthetic.main.fragment_movies.*

class FavoritesFragment : Fragment() {

    private lateinit var adapter: MoviesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorites,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showLoader()

        database.getAllFavorites().observe(this, Observer {
            adapter = MoviesAdapter(it.toMutableList())
            favoritesRecyclerView.layoutManager = LinearLayoutManager(context)
            favoritesRecyclerView.adapter = adapter
            hideLoader()
        })
    }

    private fun showLoader() {
        favoritesProgressBar.visibility = View.VISIBLE
        favoritesRecyclerView.visibility = View.GONE
    }

    private fun hideLoader() {
        favoritesProgressBar.visibility = View.GONE
        favoritesRecyclerView.visibility = View.VISIBLE

    }


}