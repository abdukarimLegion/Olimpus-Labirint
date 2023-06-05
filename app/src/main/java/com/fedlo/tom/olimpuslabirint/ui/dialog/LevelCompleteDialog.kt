package com.fedlo.tom.olimpuslabirint.ui.dialog

import android.app.Dialog
import android.content.ContentValues.TAG
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.fedlo.tom.olimpuslabirint.databinding.FragmentLevelCompleteDialogBinding
import com.fedlo.tom.olimpuslabirint.util.PrefManager

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class LevelCompleteDialog : DialogFragment() {

    private var param1: String? = null
    private var param2: String? = null

    private var binding: FragmentLevelCompleteDialogBinding? = null

    private val prefManager: PrefManager by lazy { PrefManager.instance }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLevelCompleteDialogBinding.inflate(inflater, container, false)

        binding?.tvCoin?.text = param2
        binding?.tvScore?.text = "Your score : ${param1} sec"
        binding?.btnMenu?.setOnClickListener {
            if (onClick != null) {
                onClick?.onClick(binding?.btnMenu?.text.toString())
            }


            parentFragmentManager.popBackStack()
            dismiss()
        }


//        var balance = prefManager.balance
//        var bet = prefManager.bet
//
//        balance += (bet * 1.2).toInt()
//
//        prefManager.saveBalance(balance)
////        binding?.tvCoin?.text = "${bet*1.2}"
//
//        var level = prefManager.level
//        prefManager.saveLevel(++level)
//        Log.i(TAG, "onCreateView: level $level ga saqlandi")
        return binding?.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LevelCompleteDialog().apply {
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

    private var onClick: OnClick? = null

    interface OnClick {
        fun onClick(str: String)
    }

    fun setOnClick(onClick: OnClick) {
        this.onClick = onClick
    }
}