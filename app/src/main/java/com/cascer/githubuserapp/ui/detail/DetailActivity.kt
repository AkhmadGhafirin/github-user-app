package com.cascer.githubuserapp.ui.detail

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.cascer.githubuserapp.R
import com.cascer.githubuserapp.data.model.UserModel
import com.cascer.githubuserapp.databinding.ActivityDetailBinding
import com.cascer.githubuserapp.utils.ImageUtils.loadCircle
import com.cascer.githubuserapp.utils.gone
import com.cascer.githubuserapp.utils.visible
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val username = intent.getStringExtra("username")
        viewModel.getUserDetail(username.orEmpty())
        setupViewModel()
    }

    private fun setupViews(user: UserModel) {
        with(binding) {
            containerContent.visible()
            ivUser.loadCircle(this@DetailActivity, user.avatarUrl)
            tvName.text = user.name
            tvUsername.text = user.login
            val viewPagerAdapter = ViewPagerAdapter(this@DetailActivity, user.login)
            viewPager.adapter = viewPagerAdapter
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = if (position == 0) {
                    getString(R.string.followers, user.followers.toString())
                } else {
                    getString(R.string.following, user.following.toString())
                }
            }.attach()
        }
    }

    private fun setupViewModel() {
        with(viewModel) {
            user.observe(this@DetailActivity) {
                it?.let { user -> setupViews(user) }
            }
            isLoading.observe(this@DetailActivity) {
                showLoading(it)
            }
            isError.observe(this@DetailActivity) {
                Toast.makeText(this@DetailActivity, it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showLoading(show: Boolean) {
        with(binding) {
            if (show) {
                progressbar.visible()
                containerContent.gone()
            } else {
                progressbar.gone()
                containerContent.visible()
            }
        }
    }
}