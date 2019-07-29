package com.example.movies.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.movies.R
import com.example.movies.model.DatabaseService
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var database: DatabaseService
    }

    private val moviesFragment = MoviesFragment()
    private val favoritesFragment = FavoritesFragment()

    val compositeDisposable = CompositeDisposable()

    var isConnected: Boolean = true

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

        if (savedInstanceState == null)
            switchToFragment(moviesFragment)
        checkConnectivity()
    }

    private fun switchToFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, fragment).commit()
    }

    private fun checkConnectivity() {

        compositeDisposable.add(
            ReactiveNetwork
                .observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    val noInternetFragment = NoInternetFragment.newInstance()
                    isConnected = it
                    if (isConnected) {
                        supportFragmentManager.popBackStack()
                    } else {
                        supportFragmentManager.beginTransaction().setCustomAnimations(
                            android.R.anim.fade_in,
                            android.R.anim.fade_out,
                            android.R.anim.fade_in,
                            android.R.anim.fade_out
                        )
                            .add(android.R.id.content, noInternetFragment).addToBackStack("Nointernet").commit()
                    }
                }
        )

    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}
