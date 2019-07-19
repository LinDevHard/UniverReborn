package com.lindevhard.android.univerrebornlite.ui.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.lindevhard.android.univerrebornlite.R
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var factor: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
