package com.jcoello.mymovieslistv2

import android.app.Application
import com.jcoello.domain.model.Gender
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    companion object {
        lateinit var instance: App
        val tabsList = mutableListOf<String>()
        val moviesGendersList = mutableListOf<Gender>()
        val seriesGendersList = mutableListOf<Gender>()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initTabsList()
        initMoviesGendersList()
        initSeriesGendersList()
    }

    private fun initTabsList(): Unit = with(tabsList) {
        add(getString(R.string.tab_series))
        add(getString(R.string.tab_movies))
        add(getString(R.string.tab_my_list))
    }

    private fun initMoviesGendersList(): Unit = with(moviesGendersList) {
        add(
            Gender(
                id = 0,
                name = getString(R.string.gender_all)
            )
        )
        add(
            Gender(
                id = 28,
                name = getString(R.string.gender_action)
            )
        )
        add(
            Gender(
                id = 12,
                name = getString(R.string.gender_adventure)
            )
        )
        add(
            Gender(
                id = 16,
                name = getString(R.string.gender_animation)
            )
        )
        add(
            Gender(
                id = 35,
                name = getString(R.string.gender_comedy)
            )
        )
        add(
            Gender(
                id = 80,
                name = getString(R.string.gender_crime)
            )
        )
        add(
            Gender(
                id = 99,
                name = getString(R.string.gender_documentary)
            )
        )
        add(
            Gender(
                id = 18,
                name = getString(R.string.gender_drama)
            )
        )
        add(
            Gender(
                id = 10751,
                name = getString(R.string.gender_family)
            )
        )
        add(
            Gender(
                id = 14,
                name = getString(R.string.gender_fantasy)
            )
        )
        add(
            Gender(
                id = 36,
                name = getString(R.string.gender_history)
            )
        )
        add(
            Gender(
                id = 27,
                name = getString(R.string.gender_horror)
            )
        )
        add(
            Gender(
                id = 10402,
                name = getString(R.string.gender_musical)
            )
        )
        add(
            Gender(
                id = 9648,
                name = getString(R.string.gender_mystery)
            )
        )
        add(
            Gender(
                id = 10749,
                name = getString(R.string.gender_romance)
            )
        )
        add(
            Gender(
                id = 878,
                name = getString(R.string.gender_scifi)
            )
        )
        add(
            Gender(
                id = 10770,
                name = getString(R.string.gender_tv)
            )
        )
        add(
            Gender(
                id = 53,
                name = getString(R.string.gender_thriller)
            )
        )
        add(
            Gender(
                id = 10752,
                name = getString(R.string.gender_war)
            )
        )
        add(
            Gender(
                id = 37,
                name = getString(R.string.gender_western)
            )
        )
    }

    private fun initSeriesGendersList(): Unit = with(seriesGendersList) {
        add(
            Gender(
                id = 0,
                name = getString(R.string.gender_all)
            )
        )
        add(
            Gender(
                id = 10759,
                name = getString(R.string.gender_action)
            )
        )
        add(
            Gender(
                id = 16,
                name = getString(R.string.gender_animation)
            )
        )
        add(
            Gender(
                id = 35,
                name = getString(R.string.gender_comedy)
            )
        )
        add(
            Gender(
                id = 80,
                name = getString(R.string.gender_crime)
            )
        )
        add(
            Gender(
                id = 99,
                name = getString(R.string.gender_documentary)
            )
        )
        add(
            Gender(
                id = 18,
                name = getString(R.string.gender_drama)
            )
        )
        add(
            Gender(
                id = 10751,
                name = getString(R.string.gender_family)
            )
        )
        add(
            Gender(
                id = 10762,
                name = getString(R.string.gender_kids)
            )
        )
        add(
            Gender(
                id = 9648,
                name = getString(R.string.gender_mystery)
            )
        )
        add(
            Gender(
                id = 10763,
                name = getString(R.string.gender_news)
            )
        )
        add(
            Gender(
                id = 10764,
                name = getString(R.string.gender_reality)
            )
        )
        add(
            Gender(
                id = 10765,
                name = getString(R.string.gender_scifi)
            )
        )
        add(
            Gender(
                id = 10766,
                name = getString(R.string.gender_soap)
            )
        )
        add(
            Gender(
                id = 10767,
                name = getString(R.string.gender_talk)
            )
        )
        add(
            Gender(
                id = 10768,
                name = getString(R.string.gender_war)
            )
        )
        add(
            Gender(
                id = 37,
                name = getString(R.string.gender_western)
            )
        )
    }
}