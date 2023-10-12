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

    private var isFavorite = false
    private var favoriteId = 0

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
            viewModel.getFavorite(user.login)
            val viewPagerAdapter = ViewPagerAdapter(this@DetailActivity, user.login)
            viewPager.adapter = viewPagerAdapter
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = if (position == 0) {
                    getString(R.string.followers, user.followers.toString())
                } else {
                    getString(R.string.following, user.following.toString())
                }
            }.attach()

            fabFavorite.setOnClickListener {
                if (isFavorite) {
                    viewModel.deleteFavorite(favoriteId)
                    Toast.makeText(this@DetailActivity, "Remove from Favorite", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    viewModel.insertFavorite(user)
                    Toast.makeText(this@DetailActivity, "Add to Favorite", Toast.LENGTH_SHORT)
                        .show()
                }
            }
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
            isFavorite.observe(this@DetailActivity) {
                showFavIcon(it)
            }
            favorite.observe(this@DetailActivity) {
                favoriteId = it.id
                showFavIcon(it.login.isNotEmpty())
            }
        }
    }

    private fun showFavIcon(favorite: Boolean) {
        isFavorite = favorite
        if (favorite) {
            binding.fabFavorite.setImageResource(R.drawable.baseline_favorite_24)
        } else {
            binding.fabFavorite.setImageResource(R.drawable.baseline_favorite_border_24)
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