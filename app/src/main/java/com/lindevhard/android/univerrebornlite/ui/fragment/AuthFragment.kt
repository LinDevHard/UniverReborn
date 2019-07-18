package com.lindevhard.android.univerrebornlite.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.lindevhard.android.univerrebornlite.R
import dagger.android.support.DaggerFragment

/**
 * A simple [Fragment] subclass.
 *
 */
class AuthFragment : DaggerFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return TextView(activity).apply {
            setText(R.string.hello_blank_fragment)
        }
    }


}
