package com.fedlo.tom.olimpuslabirint.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.fedlo.tom.olimpuslabirint.R
import com.fedlo.tom.olimpuslabirint.databinding.ActivityMainBinding
import com.fedlo.tom.olimpuslabirint.util.PrefManager

const val BALANCE = 300

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val prefManager: PrefManager by lazy { PrefManager.instance }
    private var coin = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(binding.root)
        PrefManager.init(this)
        if (prefManager.onceBalance) {
            prefManager.saveBalance(BALANCE)
            prefManager.saveOnceBalance(false)
        }
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setCurrentFragment(LoadingFragment())

    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out
            )
            replace(R.id.flFragment, fragment)
            addToBackStack("fragment")
            commit()
        }
}