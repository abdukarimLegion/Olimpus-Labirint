package com.fedlo.tom.olimpuslabirint.ui

import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.marginLeft
import com.fedlo.tom.olimpuslabirint.R
import com.fedlo.tom.olimpuslabirint.databinding.FragmentRecordBoardBinding
import com.fedlo.tom.olimpuslabirint.model.User
import com.fedlo.tom.olimpuslabirint.util.PrefManager
import kotlin.math.roundToInt


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class RecordBoardFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var binding: FragmentRecordBoardBinding?=null
    private val prefManager: PrefManager by lazy { PrefManager.instance }
    private var arrayUser = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    lateinit var linearLayout: LinearLayout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRecordBoardBinding.inflate(inflater, container, false)

initArray()


        binding?.btnBack?.setOnClickListener {
            parentFragmentManager.popBackStack()
        }


        return binding?.root
    }

    private fun initArray() {
        arrayUser = ArrayList()
        arrayUser.add(User("1.Alex", 10.0))
        arrayUser.add(User("2.Mimi", 12.2))
        arrayUser.add(User("3.Alan", 13.3))
        arrayUser.add(User("4.Bob", 15.26))
        arrayUser.add(User("5.Billy", 17.36))
        arrayUser.add(User("6.Donni", 19.26))
        arrayUser.add(User("7.Lill", 22.12))
        arrayUser.add(User("8.Rose", 26.26))
        arrayUser.add(User("9.Amber", 30.36))
        arrayUser.add(User("10.You", 40.26))

    }


    companion object {


        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RecordBoardFragment().apply {
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