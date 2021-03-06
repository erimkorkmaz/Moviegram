package com.example.movies.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.R
import com.example.movies.ui.MainActivity.Companion.database
import kotlinx.android.synthetic.main.fragment_favorites.*

class FavoritesFragment : Fragment() {

    private lateinit var adapter: FavoritesAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorites,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
  
        database.getAllFavorites().observe(this, Observer {
            adapter = FavoritesAdapter(it.toMutableList())
            favoritesRecyclerView.layoutManager = LinearLayoutManager(context)
            favoritesRecyclerView.adapter = adapter
        })

        val heightInPixels =resources.getDimensionPixelSize(R.dimen.divider_height)
        favoritesRecyclerView.addItemDecoration(DividerItemDecoration(ContextCompat.getColor(context!!,R.color.secondaryDarkColor),heightInPixels))

    }



}