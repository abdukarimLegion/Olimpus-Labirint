package com.fedlo.tom.olimpuslabirint.ui

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fedlo.tom.olimpuslabirint.R
import com.fedlo.tom.olimpuslabirint.databinding.FragmentLevelBinding
import com.fedlo.tom.olimpuslabirint.game.GameFragment
import com.fedlo.tom.olimpuslabirint.game.GameView
import com.fedlo.tom.olimpuslabirint.util.PrefManager


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class LevelFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private var binding: FragmentLevelBinding? = null

    private val prefManager: PrefManager by lazy { PrefManager.instance }
    private var level = 0

    private var arrayLvlTime = ArrayList<Int>()
    private var tvtxt = ArrayList<Integer>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLevelBinding.inflate(inflater, container, false)


        level = prefManager.level

        Log.i(TAG, "onCreateView: level $level")

        initLvltv()
        initlvl()

        initLvlName()

        binding?.btnBack?.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        binding?.tvText?.text = prefManager.balance.toString()
        return binding?.root
    }

    private fun initLvlName() {
        if (prefManager.level == 1) {
            binding?.lvl1?.tvLvl1?.text = "Level 1"
        }

        if (prefManager.level == 2) {
            binding?.lvl1?.tvLvl2?.text = "Level 2"
        }

        if (prefManager.level == 3) {
            binding?.lvl1?.tvLvl3?.text = "Level 3"
        }

        if (prefManager.level == 4) {
            binding?.lvl1?.tvLvl4?.text = "Level 4"
        }

        if (prefManager.level == 5) {
            binding?.lvl1?.tvLvl5?.text = "Level 5"
        }


        if (prefManager.level == 6) {
            binding?.lvl1?.tvLvl6?.text = "Level 6"
        }


        if (prefManager.level == 7) {
            binding?.lvl1?.tvLvl7?.text = "Level 7"
        }


        if (prefManager.level == 8) {
            binding?.lvl1?.tvLvl8?.text = "Level 8"
        }


        if (prefManager.level == 9) {
            binding?.lvl1?.tvLvl9?.text = "Level 9"
        }



        if (prefManager.level == 10) {
            binding?.lvl1?.tvLvl10?.text = "Level 10"
        }


        if (prefManager.level == 11) {
            binding?.lvl1?.tvLvl11?.text = "Level 11"
        }


        if (prefManager.level == 12) {
            binding?.lvl1?.tvLvl12?.text = "Level 12"
        }


        if (prefManager.level == 13) {
            binding?.lvl1?.tvLvl13?.text = "Level 13"
        }


        if (prefManager.level == 14) {
            binding?.lvl1?.tvLvl14?.text = "Level 14"
        }

        if (prefManager.level == 15) {
            binding?.lvl1?.tvLvl15?.text = "Level 15"
        }

        if (prefManager.level == 16) {
            binding?.lvl1?.tvLvl16?.text = "Level 16"
        }

        if (prefManager.level == 17) {
            binding?.lvl1?.tvLvl17?.text = "Level 17"
        }

        if (prefManager.level == 18) {
            binding?.lvl1?.tvLvl18?.text = "Level 18"
        }

        if (prefManager.level == 19) {
            binding?.lvl1?.tvLvl19?.text = "Level 19"
        }

        if (prefManager.level == 20) {
            binding?.lvl1?.tvLvl20?.text = "Level 20"
        }

    }


//    private fun initLvlTime() {
//        arrayLvlTime = prefManager.list
//
//
//    }

    private fun initLvltv() {
        if (prefManager.level > 1) {
            binding?.lvl1?.tvLvl1?.text = "Done"
            binding?.lvl1?.btnLvl1?.setClickable(false)
        }

        if (prefManager.level > 2) {
            binding?.lvl1?.tvLvl2?.text = "Done"
            binding?.lvl1?.btnLvl2?.setClickable(false)
        }

        if (prefManager.level > 3) {
            binding?.lvl1?.tvLvl3?.text = "Done"
            binding?.lvl1?.btnLvl3?.setClickable(false)
        }

        if (prefManager.level > 4) {
            binding?.lvl1?.tvLvl4?.text = "Done"
            binding?.lvl1?.btnLvl4?.setClickable(false)
        }

        if (prefManager.level > 5) {
            binding?.lvl1?.tvLvl5?.text = "Done"
            binding?.lvl1?.btnLvl5?.setClickable(false)
        }

        if (prefManager.level > 6) {
            binding?.lvl1?.tvLvl6?.text = "Done"
            binding?.lvl1?.btnLvl6?.setClickable(false)
        }

        if (prefManager.level > 7) {
            binding?.lvl1?.tvLvl7?.text = "Done"
            binding?.lvl1?.btnLvl7?.setClickable(false)
        }

        if (prefManager.level > 8) {
            binding?.lvl1?.tvLvl8?.text = "Done"
            binding?.lvl1?.btnLvl8?.setClickable(false)
        }

        if (prefManager.level > 9) {
            binding?.lvl1?.tvLvl9?.text = "Done"
            binding?.lvl1?.btnLvl9?.setClickable(false)
        }

        if (prefManager.level > 10) {
            binding?.lvl1?.tvLvl10?.text = "Done"
            binding?.lvl1?.btnLvl10?.setClickable(false)
        }

        if (prefManager.level > 11) {
            binding?.lvl1?.tvLvl11?.text = "Done"
            binding?.lvl1?.btnLvl11?.setClickable(false)
        }

        if (prefManager.level > 12) {
            binding?.lvl1?.tvLvl12?.text = "Done"
            binding?.lvl1?.btnLvl12?.setClickable(false)
        }

        if (prefManager.level > 13) {
            binding?.lvl1?.tvLvl13?.text = "Done"
            binding?.lvl1?.btnLvl13?.setClickable(false)
        }

        if (prefManager.level > 14) {
            binding?.lvl1?.tvLvl14?.text = "Done"
            binding?.lvl1?.btnLvl14?.setClickable(false)
        }

        if (prefManager.level > 15) {
            binding?.lvl1?.tvLvl15?.text = "Done"
            binding?.lvl1?.btnLvl15?.setClickable(false)
        }

        if (prefManager.level > 16) {
            binding?.lvl1?.tvLvl16?.text = "Done"
            binding?.lvl1?.btnLvl16?.setClickable(false)
        }

        if (prefManager.level > 17) {
            binding?.lvl1?.tvLvl17?.text = "Done"
            binding?.lvl1?.btnLvl17?.setClickable(false)
        }

        if (prefManager.level > 18) {
            binding?.lvl1?.tvLvl18?.text = "Done"
            binding?.lvl1?.btnLvl18?.setClickable(false)
        }

        if (prefManager.level > 19) {
            binding?.lvl1?.tvLvl19?.text = "Done"
            binding?.lvl1?.btnLvl19?.setClickable(false)
        }

        if (prefManager.level > 19) {
            binding?.lvl1?.tvLvl19?.text = "Done"
            binding?.lvl1?.btnLvl19?.setClickable(false)
        }

        if (prefManager.level > 20) {
            binding?.lvl1?.tvLvl20?.text = "Done"
            binding?.lvl1?.btnLvl20?.setClickable(false)
        }
    }

    private fun initlvl() {

        binding?.lvl1?.btnLvl1?.setOnClickListener {

            if (level == 1) {
                setCurrentFragment(GameFragment())
            }
        }


        binding?.lvl1?.btnLvl2?.setOnClickListener {

            if (level == 2) {
                setCurrentFragment(GameFragment())
            }
        }

        binding?.lvl1?.btnLvl3?.setOnClickListener {

            if (level == 3) {
                setCurrentFragment(GameFragment())
            }
        }

        binding?.lvl1?.btnLvl4?.setOnClickListener {

            if (level == 4) {
                setCurrentFragment(GameFragment())
            }
        }
        binding?.lvl1?.btnLvl5?.setOnClickListener {

            if (level == 5) {
                setCurrentFragment(GameFragment())
            }
        }

        binding?.lvl1?.btnLvl6?.setOnClickListener {

            if (level == 6) {
                setCurrentFragment(GameFragment())
            }
        }


        binding?.lvl1?.btnLvl7?.setOnClickListener {

            if (level == 7) {
                setCurrentFragment(GameFragment())
            }
        }


        binding?.lvl1?.btnLvl8?.setOnClickListener {

            if (level == 8) {
                setCurrentFragment(GameFragment())
            }
        }

        binding?.lvl1?.btnLvl9?.setOnClickListener {

            if (level == 9) {
                setCurrentFragment(GameFragment())
            }
        }

        binding?.lvl1?.btnLvl10?.setOnClickListener {

            if (level == 10) {
                setCurrentFragment(GameFragment())
            }
        }

        binding?.lvl1?.btnLvl11?.setOnClickListener {

            if (level == 11) {
                setCurrentFragment(GameFragment())
            }
        }

        binding?.lvl1?.btnLvl12?.setOnClickListener {

            if (level == 12) {
                setCurrentFragment(GameFragment())
            }
        }

        binding?.lvl1?.btnLvl13?.setOnClickListener {

            if (level == 13) {
                setCurrentFragment(GameFragment())
            }
        }

        binding?.lvl1?.btnLvl14?.setOnClickListener {

            if (level == 14) {
                setCurrentFragment(GameFragment())
            }
        }

        binding?.lvl1?.btnLvl15?.setOnClickListener {

            if (level == 15) {
                setCurrentFragment(GameFragment())
            }
        }

        binding?.lvl1?.btnLvl16?.setOnClickListener {

            if (level == 16) {
                setCurrentFragment(GameFragment())
            }
        }

        binding?.lvl1?.btnLvl17?.setOnClickListener {

            if (level == 17) {
                setCurrentFragment(GameFragment())
            }
        }

        binding?.lvl1?.btnLvl18?.setOnClickListener {

            if (level == 18) {
                setCurrentFragment(GameFragment())
            }
        }

        binding?.lvl1?.btnLvl19?.setOnClickListener {

            if (level == 19) {
                setCurrentFragment(GameFragment())
            }
        }

        binding?.lvl1?.btnLvl20?.setOnClickListener {

            if (level == 20) {
                setCurrentFragment(GameFragment())
            }
        }

    }

    private fun setCurrentFragment(fragment: Fragment) =
        parentFragmentManager.beginTransaction().apply {
            setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out
            )
            replace(R.id.flContainer, fragment)
            addToBackStack("fragment")
            commit()
        }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LevelFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}