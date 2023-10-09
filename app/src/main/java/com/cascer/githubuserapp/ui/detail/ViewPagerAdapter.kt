package com.cascer.githubuserapp.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cascer.githubuserapp.ui.detail.follow.FollowFragment
import com.cascer.githubuserapp.utils.Constant.FOLLOWERS
import com.cascer.githubuserapp.utils.Constant.FOLLOWING

class ViewPagerAdapter(activity: AppCompatActivity, private val username: String) :
    FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> {
                fragment = FollowFragment()
                fragment.arguments = Bundle().apply {
                    putInt("TYPE", FOLLOWERS)
                    putString("username", username)
                }
            }

            1 -> {
                fragment = FollowFragment()
                fragment.arguments = Bundle().apply {
                    putInt("TYPE", FOLLOWING)
                    putString("username", username)
                }
            }
        }
        return fragment as Fragment
    }
}