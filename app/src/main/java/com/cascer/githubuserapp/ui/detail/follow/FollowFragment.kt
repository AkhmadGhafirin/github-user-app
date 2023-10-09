package com.cascer.githubuserapp.ui.detail.follow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.cascer.githubuserapp.databinding.FragmentFollowBinding
import com.cascer.githubuserapp.ui.detail.DetailViewModel
import com.cascer.githubuserapp.ui.list.MainAdapter
import com.cascer.githubuserapp.utils.Constant
import com.cascer.githubuserapp.utils.gone
import com.cascer.githubuserapp.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowFragment : Fragment() {

    private lateinit var binding: FragmentFollowBinding
    private val userAdapter by lazy { MainAdapter { } }
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var type = 0
        var username = ""
        arguments?.let {
            type = it.getInt("TYPE")
            username = it.getString("username", "")
        }
        if (type == Constant.FOLLOWERS) {
            viewModel.getUserFollowers(username)
        } else {
            viewModel.getUserFollowing(username)
        }
        setupViews()
    }

    private fun setupViews() {
        with(binding) {
            rvList.apply {
                layoutManager =
                    LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
                adapter = userAdapter
            }
            setupViewModel()
        }
    }

    private fun setupViewModel() {
        with(viewModel) {
            followers.observe(viewLifecycleOwner) {
                binding.rvList.visible()
                userAdapter.sendData(it)
            }

            following.observe(viewLifecycleOwner) {
                binding.rvList.visible()
                userAdapter.sendData(it)
            }
            isLoading.observe(viewLifecycleOwner) {
                showLoading(it)
            }
            isError.observe(viewLifecycleOwner) {
                Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
            }
        }
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