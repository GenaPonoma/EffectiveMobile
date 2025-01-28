package com.example.effectivemobile.di

import com.example.effectivemobile.app.App
import com.example.effectivemobile.ui.favourites.FavouritesFragment
import com.example.effectivemobile.ui.matching.MatchingFragment
import com.example.effectivemobile.ui.search.SearchFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(app: SearchFragment)
    fun inject(app: App)
    fun inject(matchingFragment: MatchingFragment)
    fun inject(favouritesFragment: FavouritesFragment)
}