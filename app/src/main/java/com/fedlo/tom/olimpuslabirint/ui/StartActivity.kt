package com.fedlo.tom.olimpuslabirint.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.fedlo.tom.olimpuslabirint.R
import com.fedlo.tom.olimpuslabirint.util.PrefManager

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start2)
        PrefManager.init(this)
        setCurrentFragment(MenuFragment())
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out
            )
            replace(R.id.flContainer, fragment)
            commit()
        }
}