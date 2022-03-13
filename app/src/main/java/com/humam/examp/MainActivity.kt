package com.humam.examp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.humam.examp.fragment.Fragment1
import com.humam.examp.fragment.Fragment2
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    val fragHome: Fragment = Fragment1()
    val fragFire: Fragment = Fragment2()

    val fm: FragmentManager = supportFragmentManager
    var active: Fragment = fragHome
    private lateinit var menuItem: MenuItem
    private lateinit var menu: Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    setupBottomNav()
    }

    private fun setupBottomNav() {

        fm.beginTransaction().add(R.id.nav_container, fragHome).show(fragHome).commit()
        fm.beginTransaction().add(R.id.nav_container, fragFire).hide(fragFire).commit()

        menu = btn_nav_view.menu
        menuItem = menu.getItem(0)
        menuItem.isChecked = true

        btn_nav_view.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    callFrag(0, fragHome)
                }
                R.id.navigation_fire -> {
                    callFrag(1, fragFire)
                }
            }

            false
        }

    }

    private fun callFrag(i: Int, fragment: Fragment) {
        menuItem = menu.getItem(i)
        menuItem.isChecked = true
        fm.beginTransaction().hide(active).show(fragment).commit()
        active = fragment

    }
}
