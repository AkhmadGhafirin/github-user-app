package com.cascer.githubuserapp.ui.list

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cascer.githubuserapp.data.model.UserModel
import com.cascer.githubuserapp.databinding.ActivityMainBinding
import com.cascer.githubuserapp.ui.detail.DetailActivity
import com.cascer.githubuserapp.utils.gone
import com.cascer.githubuserapp.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val userAdapter by lazy { MainAdapter { toDetail(it) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
    }

    private fun setupViews() {
        setupViewModel()
        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { _, _, _ ->
                    searchBar.text = searchView.text
                    searchView.hide()
                    viewModel.searchUser(searchView.text.toString())
                    false
                }
            rvList.apply {
                layoutManager =
                    LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                adapter = userAdapter
            }
        }
    }

    private fun setupViewModel() {
        with(viewModel) {
            users.observe(this@MainActivity) {
                binding.rvList.visible()
                userAdapter.sendData(it)
            }
            isLoading.observe(this@MainActivity) {
                showLoading(it)
            }
            isError.observe(this@MainActivity) {
                Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun toDetail(user: UserModel) {
        val intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtra("username", user.login)
        startActivity(intent)
    }

    private fun showLoading(show: Boolean) {
        with(binding) {
            if (show) {
                progressbar.visible()
                rvList.gone()
            } else {
                progressbar.gone()
                rvList.visible()
            }
        }
    }
}