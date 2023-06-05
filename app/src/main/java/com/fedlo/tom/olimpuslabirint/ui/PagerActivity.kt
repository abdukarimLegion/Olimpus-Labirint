package com.fedlo.tom.olimpuslabirint.ui

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.fedlo.tom.olimpuslabirint.R


import com.fedlo.tom.olimpuslabirint.adapter.PagerAdapter
import com.fedlo.tom.olimpuslabirint.databinding.ActivityPagerBinding
import com.fedlo.tom.olimpuslabirint.model.PagerModel
import com.fedlo.tom.olimpuslabirint.util.PrefManager


class PagerActivity : AppCompatActivity() {


    private lateinit var binding: ActivityPagerBinding

    private lateinit var data: ArrayList<PagerModel>
    private lateinit var myAdapter: PagerAdapter
    private var currentItemPager = 1

    private val prefManager: PrefManager by lazy { PrefManager.instance }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        PrefManager.init(this)

        if (prefManager.once) {
            loadData()
            Log.i(ContentValues.TAG, "onCreate: $data ketyapti")
            myAdapter = PagerAdapter(data, supportFragmentManager)

            binding.viewPager.adapter = myAdapter
            binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                }

                override fun onPageSelected(position: Int) {
                    Log.d(ContentValues.TAG, "my position is : " + position);
                }

                override fun onPageScrollStateChanged(state: Int) {}
            })


            binding.btnNext.setOnClickListener {
                if (currentItemPager == 2) {
                    //     binding.btnNextTv.text = getString(R.string.start_application)

                }
                if (currentItemPager == 3) {

                    goToStartActivity()
                } else {
                    binding.viewPager.setCurrentItem(currentItemPager++, true)
                }
            }
            prefManager.saveOnce(false)
        }
        else{
            goToStartActivity()
        }

//        binding.tabLayout.attachTo(binding.viewPager)


    }
    private fun loadData() {
        data = ArrayList()
        data.add(
            PagerModel(
                "Welcome",
                "Go through the mazes at speed \nand set records",
                R.drawable.pager1
            )
        )
        data.add(
            PagerModel(
               "",
                "Train limitlessly in training mode",
                R.drawable.pager2
            )
        )
        data.add(
            PagerModel(
              "",
                "Set records and compete with players",
                R.drawable.pager3
            )
        )
    }

    private fun goToStartActivity() {
        val startActivityIntent = Intent(applicationContext, StartActivity::class.java)
        startActivity(startActivityIntent)
        finish()
    }


}