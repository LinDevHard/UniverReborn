package com.lindevhard.android.univerrebornlite.ui.activity

import android.os.Bundle
import com.lindevhard.android.univerrebornlite.R
import com.lindevhard.android.univerrebornlite.di.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
