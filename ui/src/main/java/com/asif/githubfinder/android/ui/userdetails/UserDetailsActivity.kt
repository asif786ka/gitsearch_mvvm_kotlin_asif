package com.asif.githubfinder.android.ui.userdetails

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.asif.githubfinder.android.DefaultServiceLocator
import com.asif.githubfinder.android.R
import com.asif.githubfinder.android.databinding.ActivityUserDetailsBinding
import com.asif.githubfinder.android.ui.common.ViewModelFactory

class UserDetailsActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USERNAME = "username"
    }

    private val viewModel: UserDetailsViewModel by viewModels {
        ViewModelFactory(DefaultServiceLocator.getInstance(application))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val username = intent.getStringExtra(EXTRA_USERNAME)

        if (username == null) {
            finish()
            return
        }

        setContentView(
            ActivityUserDetailsBinding.inflate(layoutInflater).apply {
                viewModel = this@UserDetailsActivity.viewModel
                lifecycleOwner = this@UserDetailsActivity
            }.root
        )

        viewModel.openBrowserEvent.observe(this, { event ->
            event.getContentIfNotHandled()?.let { url ->
                startActivity(
                    Intent(Intent.ACTION_VIEW).apply {
                        data = if (url.matches("(http|https)://".toRegex())) Uri.parse(url) else Uri.parse("http://$url")
                    }
                )
            }
        })

        viewModel.showErrorToastEvent.observe(this, { event ->
            if (!event.hasBeenHandled) {
                Toast.makeText(this@UserDetailsActivity, R.string.an_error_occurred, Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.closeActivityEvent.observe(this, {
            finish()
        })

        viewModel.load(username)
    }
}