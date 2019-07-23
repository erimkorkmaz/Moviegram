package com.example.movies.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.ViewModel.MoviesViewModel
import kotlinx.android.synthetic.main.fragment_movies.*

class MoviesFragment : Fragment() {

    private lateinit var moviesViewModel : MoviesViewModel

    private lateinit var  adapter : MoviesCardAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_movies,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)

        showLoader()

        moviesViewModel.getMovies().observe(this, Observer {
            adapter = MoviesCardAdapter(it.toMutableList())
            moviesRecyclerView.layoutManager = GridLayoutManager(context,2,RecyclerView.VERTICAL,false)
            moviesRecyclerView.adapter = adapter
            hideLoader()
        })
    }

    private fun showLoader() {
        progressBar.visibility = View.VISIBLE
        moviesRecyclerView.visibility = View.GONE
    }

    private fun hideLoader() {
        progressBar.visibility = View.GONE
        moviesRecyclerView.visibility = View.VISIBLE

    }



}