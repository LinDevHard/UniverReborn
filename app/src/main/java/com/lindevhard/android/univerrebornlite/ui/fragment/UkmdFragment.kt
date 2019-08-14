package com.lindevhard.android.univerrebornlite.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lindevhard.android.univerrebornlite.R
import com.lindevhard.android.univerrebornlite.ui.adapter.UkmdAdapter
import com.lindevhard.android.univerrebornlite.utils.viewModelProvider
import com.lindevhard.android.univerrebornlite.viewmodel.UkmdViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.appbar_with_session.*
import kotlinx.android.synthetic.main.fragment_ukmd.*
import javax.inject.Inject

class UkmdFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: UkmdViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_ukmd, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = viewModelProvider(viewModelFactory)
        recycleView.layoutManager = LinearLayoutManager(this.context)

        swipe_refresher.isRefreshing = true
        val mainAdapter = UkmdAdapter(listOf())
        toolbar_title.setText(R.string.ukmd_title)

        viewModel.ukmd.observe(this, Observer {
            swipe_refresher.isRefreshing = false
            mainAdapter.items = it
        })
    }
}