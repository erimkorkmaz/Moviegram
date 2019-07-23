package com.example.movies.ui



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.movies.R
import com.example.movies.model.DatabaseService
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var database: DatabaseService
    }

    private val moviesFragment = MoviesFragment()
    private val favoritesFragment = FavoritesFragment()



    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val fragment = when (item.itemId) {
            R.id.navigation_movies -> {
                title = "Now Playing"
                moviesFragment
            }
            R.id.navigation_Favorites -> {
                title = "Favorites"
                favoritesFragment
            }

            else -> moviesFragment
        }
        switchToFragment(fragment)
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = DatabaseService(this)

        setSupportActionBar(movieToolbar)

        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        switchToFragment(moviesFragment)
    }

    private fun switchToFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, fragment).commit()
    }

}
