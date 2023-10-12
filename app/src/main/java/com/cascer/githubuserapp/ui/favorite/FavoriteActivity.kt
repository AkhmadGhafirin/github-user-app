package com.cascer.githubuserapp.ui.favorite

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cascer.githubuserapp.data.model.UserModel
import com.cascer.githubuserapp.databinding.ActivityFavoriteBinding
import com.cascer.githubuserapp.ui.detail.DetailActivity
import com.cascer.githubuserapp.ui.list.MainAdapter
import com.cascer.githubuserapp.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private val viewModel: FavoriteViewModel by viewModels()
    private val userAdapter by lazy { MainAdapter { toDetail(it) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
    }

    override fun onResume() {
        viewModel.getFavorites()
        super.onResume()
    }

    private fun setupViews() {
        with(binding) {
            rvList.apply {
                layoutManager =
                    LinearLayoutManager(
                        this@FavoriteActivity,
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                adapter = userAdapter
            }
            setupViewModel()
        }
    }

    private fun setupViewModel() {
        with(viewModel) {
            favorites.observe(this@FavoriteActivity) {
                binding.rvList.visible()
                userAdapter.sendData(it)
            }
        }
    }

    private fun toDetail(user: UserModel) {
        val intent = Intent(this@FavoriteActivity, DetailActivity::class.java)
        intent.putExtra("username", user.login)
        startActivity(intent)
    }
}