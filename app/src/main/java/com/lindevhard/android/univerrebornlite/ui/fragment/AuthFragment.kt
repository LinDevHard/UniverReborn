package com.lindevhard.android.univerrebornlite.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.lindevhard.android.univerrebornlite.R
import com.lindevhard.android.univerrebornlite.utils.showError
import com.lindevhard.android.univerrebornlite.utils.viewModelProvider
import com.lindevhard.android.univerrebornlite.viewmodel.AuthViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_auth.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class AuthFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: AuthViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_auth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = viewModelProvider(viewModelFactory)

        login_btn.setOnClickListener {
            viewModel.authRequest(input_login.text.toString(), input_password.text.toString())
        }

        viewModel.isLogin.observe(this, Observer { isLogin ->
            when (isLogin) {
                true -> {
                    val navDir = AuthFragmentDirections.actionAuthFragmentToAttendanceFragment3()
                    findNavController().navigate(navDir)
                }
                false -> showError(R.string.error_auth_message)
            }
        })

        viewModel.progress.observe(this, Observer { isLoading ->
            when (isLoading) {
                true -> {
                    progressBar.visibility = View.VISIBLE
                    login_btn.isClickable = false
                }
                false -> {
                    progressBar.visibility = View.GONE
                    login_btn.isClickable = true
                }
            }
        })
    }
}

