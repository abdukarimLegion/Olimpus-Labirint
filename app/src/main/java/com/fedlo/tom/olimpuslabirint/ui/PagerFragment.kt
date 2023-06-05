package com.fedlo.tom.olimpuslabirint.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fedlo.tom.olimpuslabirint.databinding.FragmentPagerBinding
import com.fedlo.tom.olimpuslabirint.model.PagerModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class PagerFragment : Fragment() {
    private var param1: PagerModel? = null
    private var param2: String? = null
    private var _binding: FragmentPagerBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1) as PagerModel
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPagerBinding.inflate(inflater, container, false)


        binding.sarlavha.text = param1?.title
        binding.matn.text = param1?.text
        binding.imageView.setImageResource(param1?.image!!)


        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: PagerModel) = PagerFragment().apply {
            arguments = Bundle().apply {
                putSerializable(ARG_PARAM1, param1)

            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}