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
import com.lindevhard.android.univerrebornlite.viewmodel.UmkdViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.appbar_with_session.*
import kotlinx.android.synthetic.main.fragment_umkd.*
import javax.inject.Inject

class UmkdFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: UmkdViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_umkd, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = viewModelProvider(viewModelFactory)
        recycleView.layoutManager = LinearLayoutManager(this.context)

        swipe_refresher.isRefreshing = true
        toolbar_title.setText(R.string.ukmd_title)

        viewModel.umkd.observe(this, Observer {
            swipe_refresher.isRefreshing = false
            recycleView.adapter = UkmdAdapter(it)
        })
    }
}