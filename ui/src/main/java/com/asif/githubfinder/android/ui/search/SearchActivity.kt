package com.asif.githubfinder.android.ui.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.asif.githubfinder.android.DefaultServiceLocator
import com.asif.githubfinder.android.R
import com.asif.githubfinder.android.databinding.ActivitySearchBinding
import com.asif.githubfinder.android.ui.common.ViewModelFactory
import com.asif.githubfinder.android.ui.userdetails.UserDetailsActivity

class SearchActivity : AppCompatActivity() {

    private val viewModel: SearchViewModel by viewModels {
        ViewModelFactory(DefaultServiceLocator.getInstance(application))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
            ActivitySearchBinding.inflate(layoutInflater).apply {
                viewModel = this@SearchActivity.viewModel
                lifecycleOwner = this@SearchActivity
            }.root
        )

        viewModel.openUserDetailsActivityEvent.observe(this, { event ->
            event.getContentIfNotHandled()?.let { username ->
                startActivity(
                    Intent(this, UserDetailsActivity::class.java).apply {
                        putExtra(UserDetailsActivity.EXTRA_USERNAME, username)
                    }
                )
            }
        })

        viewModel.showErrorToastEvent.observe(this, { event ->
            if (!event.hasBeenHandled) {
                Toast.makeText(this@SearchActivity, R.string.an_error_occurred, Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.load()
    }
}