package com.fedlo.tom.olimpuslabirint.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

import com.fedlo.tom.olimpuslabirint.model.PagerModel
import com.fedlo.tom.olimpuslabirint.ui.PagerFragment

class PagerAdapter(val data: ArrayList<PagerModel>, fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int): Fragment {
        return PagerFragment.newInstance(data[position])
    }
}