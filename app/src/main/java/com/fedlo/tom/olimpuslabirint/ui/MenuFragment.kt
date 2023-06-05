package com.fedlo.tom.olimpuslabirint.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fedlo.tom.olimpuslabirint.R
import com.fedlo.tom.olimpuslabirint.databinding.FragmentMenuBinding
import com.fedlo.tom.olimpuslabirint.game.GameFragment
import com.fedlo.tom.olimpuslabirint.ui.workout.CardSlotFragment
import com.fedlo.tom.olimpuslabirint.util.PrefManager


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MenuFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private var binding: FragmentMenuBinding? = null
    private val prefManager: PrefManager by lazy { PrefManager.instance }


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
        binding = FragmentMenuBinding.inflate(inflater, container, false)

        binding?.btnStart?.setOnClickListener {
            setCurrentFragment(LevelFragment())
        }

        binding?.btnTableRecord?.setOnClickListener {
            setCurrentFragment(RecordBoardFragment())
        }

        binding?.btnWorkout?.setOnClickListener {
            setCurrentFragment(CardSlotFragment())
        }

        binding?.tvText?.text = prefManager.balance.toString()

        return binding?.root

    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MenuFragment().apply {
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
}