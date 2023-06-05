package com.fedlo.tom.olimpuslabirint.ui.workout

import android.content.ContentValues
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment

import com.fedlo.tom.olimpuslabirint.R
import com.fedlo.tom.olimpuslabirint.databinding.FragmentCardSlotBinding
import com.fedlo.tom.olimpuslabirint.util.PrefManager
import com.fedlo.tom.olimpuslabirint.util.Spin
import java.util.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CardSlotFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private var isClickedbtn = true

    private val prefManager: PrefManager by lazy { PrefManager.instance }
    private var balance = 0

    private var _binding: FragmentCardSlotBinding? = null
    private val binding get() = _binding!!
    private var tikiladigan_coin = 0

    val RANDOM = Random()
    private var isStarted = false
    private var wheel1: Spin? = null
    private var wheel2: Spin? = null
    private var wheel3: Spin? = null
    private var wheel4: Spin? = null

    private var wheel6: Spin? = null
    private var wheel7: Spin? = null
    private var wheel8: Spin? = null
    private var wheel9: Spin? = null

    private var wheel11: Spin? = null
    private var wheel12: Spin? = null
    private var wheel13: Spin? = null
    private var wheel14: Spin? = null


    private val imgs =
        intArrayOf(
            R.drawable.slot1,
            R.drawable.slot2,
            R.drawable.slot3,
            R.drawable.slot4,
            R.drawable.slot5

            )


    fun randomLong(lower: Long, upper: Long): Long {
        return lower + (RANDOM.nextLong() * (upper - lower))
    }

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



        _binding = FragmentCardSlotBinding.inflate(inflater, container, false)
//        binding.tvTikiladiganMoney.text = tikiladigan_coin.toString()

        balance = prefManager.balance
        binding.tvMoney.text = "Balance: ${balance.toString()}"

        binding.btnPlay.setOnClickListener {
            if (isClickedbtn) {
                if (binding.tvTikiladiganMoney.text != "0") {
                    val bet = binding.tvTikiladiganMoney.text.toString().toInt()
                    prefManager.saveBet(bet)
                    spin()
                    isClickedbtn = false
                    stopSpin()
                } else {
                    Toast.makeText(requireContext(), "Please, bet before spin!", Toast.LENGTH_SHORT)
                        .show()
                }

            }
        }

        binding.btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.btnMinus.setOnClickListener {
            if (tikiladigan_coin!=0&& tikiladigan_coin > 0){
                tikiladigan_coin-=10
                binding.tvTikiladiganMoney.text = tikiladigan_coin.toString()
            }
        }

        binding.btnPlus.setOnClickListener {

            tikiladigan_coin+=10
            binding.tvTikiladiganMoney.text = tikiladigan_coin.toString()

        }

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                parentFragmentManager.popBackStack()
                Log.i(ContentValues.TAG, "handleOnBackPressed: pressed done")
                // in here you can do logic when backPress is clicked
                wheel1?.stopWheel()
                wheel2?.stopWheel()
                wheel3?.stopWheel()
                wheel4?.stopWheel()
//                wheel5?.stopWheel()
                wheel6?.stopWheel()
                wheel7?.stopWheel()
                wheel8?.stopWheel()
                wheel9?.stopWheel()
//                wheel10?.stopWheel()
                wheel11?.stopWheel()
                wheel12?.stopWheel()
                wheel13?.stopWheel()
                wheel14?.stopWheel()
//                wheel15?.stopWheel()

            }
        })



        return binding.root}

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CardSlotFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun stopSpin() {
        object : CountDownTimer(4000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                println()
            }

            override fun onFinish() {

                stopAllSpin()
                checkPrice()
                isClickedbtn = true

            }

        }.start()
    }

    private fun checkPrice() {
        if (wheel1!!.currentIndex == wheel2!!.currentIndex && wheel2!!.currentIndex == wheel3!!.currentIndex &&
            wheel3!!.currentIndex == wheel4!!.currentIndex && wheel4!!.currentIndex == wheel4!!.currentIndex ) {
//                    binding.msg.setText("You win the big prize")
            Toast.makeText(
                requireContext(),
                "You won 2xBet ${prefManager.bet * 2}",
                Toast.LENGTH_SHORT
            ).show()

            balance += prefManager.bet * 2
            binding.tvMoney.text = balance.toString()
            prefManager.saveBalance(balance)
        } else if (
            wheel6!!.currentIndex == wheel7!!.currentIndex && wheel7!!.currentIndex == wheel8!!.currentIndex &&
            wheel8!!.currentIndex == wheel9!!.currentIndex && wheel9!!.currentIndex == wheel9!!.currentIndex ){
//                    binding.msg.setText("Little Prize")
            Toast.makeText(
                requireContext(),
                "You won 2xBet ${prefManager.bet * 2}",
                Toast.LENGTH_SHORT
            ).show()

            balance += prefManager.bet * 2
            binding.tvMoney.text = balance.toString()
            prefManager.saveBalance(balance)

        } else if (
            wheel11!!.currentIndex == wheel12!!.currentIndex && wheel12!!.currentIndex == wheel13!!.currentIndex &&
            wheel13!!.currentIndex == wheel14!!.currentIndex && wheel14!!.currentIndex == wheel14!!.currentIndex ){
//                    binding.msg.setText("Little Prize")
            Toast.makeText(
                requireContext(),
                "You won 2xBet ${prefManager.bet * 2}",
                Toast.LENGTH_SHORT
            ).show()

            balance += prefManager.bet * 2
            binding.tvMoney.text = balance.toString()
            prefManager.saveBalance(balance)

        } else {
//                    binding.msg.setText("You lose")
            Toast.makeText(
                requireContext(),
                "You lose ${prefManager.bet} bet",
                Toast.LENGTH_SHORT
            ).show()
            balance -= prefManager.bet
            binding.tvMoney.text = balance.toString()
            prefManager.saveBalance(balance)


        }


        isStarted = false
    }

    private fun stopAllSpin() {
        wheel1?.stopWheel()
        wheel2?.stopWheel()
        wheel3?.stopWheel()
        wheel4?.stopWheel()
//        wheel5?.stopWheel()
        wheel6?.stopWheel()
        wheel7?.stopWheel()
        wheel8?.stopWheel()
        wheel9?.stopWheel()
//        wheel10?.stopWheel()
        wheel11?.stopWheel()
        wheel12?.stopWheel()
        wheel13?.stopWheel()
        wheel14?.stopWheel()
//        wheel15?.stopWheel()

    }

    private fun spin() {
        wheel1 = Spin(object : Spin.WheelListener {
            override fun newImage(img: Int) {
                requireActivity().runOnUiThread {
//                            kotlin.run {
                    binding.img1.setImageResource(img)
//                            }
                }
            }
        }, 220, 300, imgs)

        wheel1!!.start()

        wheel2 = Spin(object : Spin.WheelListener {
            override fun newImage(img: Int) {
                requireActivity().runOnUiThread {
//                            kotlin.run {
                    binding.img2.setImageResource(img)
//                            }
                }
            }
        }, 200, 300, imgs)


        wheel2!!.start()

        wheel3 = Spin(object : Spin.WheelListener {
            override fun newImage(img: Int) {
                requireActivity().runOnUiThread {
//                            kotlin.run {
                    binding.img3.setImageResource(img)
//                            }
                }
            }
        }, (170..200).random().toLong(), 300, imgs)


        wheel3!!.start()

        wheel4 = Spin(object : Spin.WheelListener {
            override fun newImage(img: Int) {
                requireActivity().runOnUiThread {
//                            kotlin.run {
                    binding.img4.setImageResource(img)
//                            }
                }
            }
        }, (170..200).random().toLong(), 300, imgs)


        wheel4!!.start()





        wheel6 = Spin(object : Spin.WheelListener {
            override fun newImage(img: Int) {
                requireActivity().runOnUiThread {
//                            kotlin.run {
                    binding.img6.setImageResource(img)
//                            }
                }
            }
        }, (170..200).random().toLong(), 300, imgs)


        wheel6!!.start()


        wheel7 = Spin(object : Spin.WheelListener {
            override fun newImage(img: Int) {
                requireActivity().runOnUiThread {
//                            kotlin.run {
                    binding.img7.setImageResource(img)
//                            }
                }
            }
        }, (170..200).random().toLong(), 300, imgs)


        wheel7!!.start()


        wheel8 = Spin(object : Spin.WheelListener {
            override fun newImage(img: Int) {
                requireActivity().runOnUiThread {
//                            kotlin.run {
                    binding.img8.setImageResource(img)
//                            }
                }
            }
        }, (170..200).random().toLong(), 300, imgs)


        wheel8!!.start()


        wheel9 = Spin(object : Spin.WheelListener {
            override fun newImage(img: Int) {
                requireActivity().runOnUiThread {
//                            kotlin.run {
                    binding.img9.setImageResource(img)
//                            }
                }
            }
        }, (170..200).random().toLong(), 300, imgs)


        wheel9!!.start()




        wheel11 = Spin(object : Spin.WheelListener {
            override fun newImage(img: Int) {
                requireActivity().runOnUiThread {
//                            kotlin.run {
                    binding.img11.setImageResource(img)
//                            }
                }
            }
        }, (170..200).random().toLong(), 300, imgs)


        wheel11!!.start()

        wheel12 = Spin(object : Spin.WheelListener {
            override fun newImage(img: Int) {
                requireActivity().runOnUiThread {
//                            kotlin.run {
                    binding.img12.setImageResource(img)
//                            }
                }
            }
        }, (170..200).random().toLong(), 300, imgs)


        wheel12!!.start()

        wheel13 = Spin(object : Spin.WheelListener {
            override fun newImage(img: Int) {
                requireActivity().runOnUiThread {
//                            kotlin.run {
                    binding.img13.setImageResource(img)
//                            }
                }
            }
        }, (170..200).random().toLong(), 300, imgs)


        wheel13!!.start()

        wheel14 = Spin(object : Spin.WheelListener {
            override fun newImage(img: Int) {
                requireActivity().runOnUiThread {
//                            kotlin.run {
                    binding.img14.setImageResource(img)
//                            }
                }
            }
        }, (170..200).random().toLong(), 300, imgs)




        wheel14!!.start()


//                binding.btn.setText("Stop")
//                binding.msg.setText("")
        isStarted = true

    }

    fun IntRange.random() =
        Random().nextInt((endInclusive + 1) - start) + start

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }}