package com.example.aisleassignment.view.user

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.aisleassignment.R
import com.example.aisleassignment.databinding.ActivityUserBinding
import com.google.android.material.badge.BadgeDrawable

class UserActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityUserBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_user)

        initNavController()
        setBadge()
    }

    private fun initNavController() {
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.fcvNavHost
        ) as NavHostFragment
        navController = navHostFragment.navController
        dataBinding.bottomNav.setupWithNavController(navController)
    }

    private fun setBadge() {
        val notesItem: MenuItem = dataBinding.bottomNav.menu.findItem(R.id.notesFragment)

        val notesBadge: BadgeDrawable =
            dataBinding.bottomNav.getOrCreateBadge(notesItem.itemId)
        notesBadge.backgroundColor = ContextCompat.getColor(this, R.color.badge_color)
        notesBadge.badgeTextColor = ContextCompat.getColor(this, R.color.white)
        notesBadge.number = 9

        val matchesItem: MenuItem = dataBinding.bottomNav.menu.findItem(R.id.matchesFragment)

        val matchesBadge: BadgeDrawable =
            dataBinding.bottomNav.getOrCreateBadge(matchesItem.itemId)
        matchesBadge.backgroundColor = ContextCompat.getColor(this, R.color.badge_color)
        matchesBadge.badgeTextColor = ContextCompat.getColor(this, R.color.white)
        matchesBadge.number = 51
    }
}