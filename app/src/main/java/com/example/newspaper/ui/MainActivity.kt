package com.example.newspaper.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.example.newspaper.R
import com.example.newspaper.repository.NewsRepository
import com.example.newspaper.ui.fragments.*
import com.shrikanthravi.customnavigationdrawer2.data.MenuItem
import com.shrikanthravi.customnavigationdrawer2.widget.SNavigationDrawer
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var aClass: Class<*>? = null
    var fragment: Fragment? = null
    var countryCode: String="eg"

    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPref =getSharedPreferences("myPref", Context.MODE_PRIVATE)

        countryCode= sharedPref.getString("CurrentCode","us").toString()



        val newsRepository= NewsRepository()
        val viewModelProviderFactory= NewsViewModelProviderFactory(countryCode!!,application,newsRepository)
        viewModel= ViewModelProvider(this,viewModelProviderFactory).get(NewsViewModel::class.java)




        val menuItems: MutableList<MenuItem> = ArrayList()
        //Use the MenuItem given by this library and not the default one.
        //First parameter is the title of the menu item and then the second parameter is the image which will be the background of the menu item.
        menuItems.add(MenuItem("News", R.drawable.news))
        menuItems.add(MenuItem("Sport", R.drawable.mo_salah))
        menuItems.add(MenuItem("Technology", R.drawable.technology))
        menuItems.add(MenuItem("Entertainment", R.drawable.im3))
        menuItems.add(MenuItem("Setting", R.drawable.settings))

        //then add them to navigation drawer
        navigation_drawer.menuItemList = menuItems

        //set default title
        navigation_drawer.setAppbarTitleTV("News")
        //set default fragment
        aClass = NewsFragment::class.java

        openFragment()

        navigation_drawer.setOnMenuItemClickListener{ position ->
            println("Position $position")
            when (position) {
                0 -> {
                    aClass = NewsFragment::class.java
                }
                1 -> {
                    aClass = SportFragment::class.java
                }
                2 -> {
                    aClass = TechnologyFragment::class.java
                }
                3 -> {
                    aClass = EntertainmentFragment::class.java
                }
                4 -> {
                    aClass = SettingFragment::class.java
                }
            }

            //Listener for drawer events such as opening and closing.
            navigation_drawer.setDrawerListener(object : SNavigationDrawer.DrawerListener {
                override fun onDrawerOpened() {}
                override fun onDrawerOpening() {}
                override fun onDrawerClosing() {
                    println("Drawer closed")
                    try {
                        fragment = aClass!!.newInstance() as Fragment
                    } catch (e: java.lang.Exception) {
                        e.printStackTrace()
                    }
                    if (fragment != null) {
                        val fragmentManager: FragmentManager = supportFragmentManager
                        fragmentManager.beginTransaction().setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).replace(
                            R.id.frame_layout, fragment!!).commit()
                    }
                }

                override fun onDrawerClosed() {}
                override fun onDrawerStateChanged(newState: Int) {
                    println("State $newState")
                }
            })
        }

    }

    private fun openFragment() {
        try {
            fragment = aClass?.newInstance() as Fragment

            //open fragment
            supportFragmentManager.beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    .replace(R.id.frame_layout, fragment!!).commit()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}