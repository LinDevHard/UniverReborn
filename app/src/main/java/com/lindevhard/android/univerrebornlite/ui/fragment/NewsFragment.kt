package com.lindevhard.android.univerrebornlite.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.lindevhard.android.univerrebornlite.R
import com.lindevhard.android.univerrebornlite.utils.viewModelProvider
import com.lindevhard.android.univerrebornlite.viewmodel.NewsViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class NewsFragment : DaggerFragment() {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: NewsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = viewModelProvider(viewModelFactory)

    }
}