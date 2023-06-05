package com.fedlo.tom.olimpuslabirint.game

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.fedlo.tom.olimpuslabirint.databinding.FragmentGameBinding
import com.fedlo.tom.olimpuslabirint.ui.StartActivity
import com.fedlo.tom.olimpuslabirint.ui.dialog.LevelFailedDialog
import com.fedlo.tom.olimpuslabirint.util.PrefManager


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
lateinit var timer: CountDownTimer
var  timer_time : Int = 0

class GameFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private var binding: FragmentGameBinding?= null
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

        binding = FragmentGameBinding.inflate(inflater, container, false)


//        binding?.proggresBar?.max=100
//        binding?.proggresBar?.scaleY = 3F
//        val anim  = activity?.let {
//            ProgressBarAnimation(requireContext(), binding?.proggresBar!!, binding?.tvTextLoading!!, 100F,0F , it
//            )
//        }
////        prefManager.saveTime()
//        Log.i(TAG, "onCreateView: ${ binding?.tvTextLoading?.text?.toString()}")
//
//        anim?.duration = 60000
//
//        binding?.proggresBar?.animation = anim

          timer_time = 0
        timer = object : CountDownTimer(31000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                Log.v("Log_tag", "Tick of Progress$timer_time$millisUntilFinished")
                timer_time++
                binding?.tvTextLoading?.text = "${timer_time.toString()} sec"
//                mProgressBar.setProgress(i as Int * 100 / (5000 / 1000))
            }

            override fun onFinish() {
                //Do what you want
                timer_time++
                showFailedDialog()
//                mProgressBar.setProgress(100)
            }
        }
        timer.start()



        binding?.btnBack?.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding?.tvCoin?.text = prefManager.balance.toString()
        binding?.tepega?.setOnClickListener { binding?.view?.movePlayer(GameView.Direction.UP) }

        binding?.pastga?.setOnClickListener { binding?.view?.movePlayer(GameView.Direction.DOWN) }

        binding?.ongga?.setOnClickListener { binding?.view?.movePlayer(GameView.Direction.RIGHT) }

        binding?.chapga?.setOnClickListener { binding?.view?.movePlayer(GameView.Direction.LEFT) }



        return binding?.root
    }

    private fun showFailedDialog() {
        val coin = 30
        val newInstance = LevelFailedDialog.newInstance(timer_time.toString(), coin.toString())

        newInstance.show((context as AppCompatActivity).supportFragmentManager, "dialog1")
        newInstance.setCancelable(false)

        var balance = prefManager.getBalance()
        balance -= coin
        Log.i(TAG, "onCreateView: complete dialog $coin")
        prefManager.saveBalance(balance)

        newInstance.setOnClick(object : LevelFailedDialog.OnClick {
            override fun onClick(str: String) {
                val intent = Intent(context, StartActivity::class.java)
                startActivity(intent)
                (context as AppCompatActivity).finish()
            }
        })

    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GameFragment().apply {
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