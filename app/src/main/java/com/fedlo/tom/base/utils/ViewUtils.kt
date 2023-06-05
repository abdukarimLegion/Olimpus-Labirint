package com.fedlo.tom.base.utils

import android.app.Activity
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView




var toast: Toast? = null
fun Fragment.toast(message: String) {
    toast?.cancel()
    toast = Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT)
    toast?.show()
}

fun Fragment.toast(message: Int) {
    toast?.cancel()
    toast = Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT)
    toast?.show()
}

fun Activity.toast(message: Int) {
    toast?.cancel()
    toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
    toast?.show()
}

fun Activity.toast(message: String) {
    toast?.cancel()
    toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
    toast?.show()
}

fun maskAsMonth(edtAddCardMonth: EditText) {
    edtAddCardMonth.doOnTextChanged { text, start, before, count ->
        if (!edtAddCardMonth.text.isNullOrEmpty()) {
            if (edtAddCardMonth.text.length == 1)
                when (edtAddCardMonth.text.first().toString()) {
//                    "0" -> {
//                        edtAddCardMonth.setText("0")
//                    }
                    "2" -> {
                        edtAddCardMonth.setText("02")
                    }
                    "3" -> {
                        edtAddCardMonth.setText("03")
                    }
                    "4" -> {
                        edtAddCardMonth.setText("04")
                    }
                    "5" -> {
                        edtAddCardMonth.setText("05")
                    }
                    "6" -> {
                        edtAddCardMonth.setText("06")
                    }
                    "7" -> {
                        edtAddCardMonth.setText("07")
                    }
                    "8" -> {
                        edtAddCardMonth.setText("08")
                    }
                    "9" -> {
                        edtAddCardMonth.setText("09")
                    }
                }
            when (edtAddCardMonth.text.toString()) {
                "13" -> {
                    edtAddCardMonth.setText("1")
                }
                "14" -> {
                    edtAddCardMonth.setText("1")
                }
                "15" -> {
                    edtAddCardMonth.setText("1")
                }
                "16" -> {
                    edtAddCardMonth.setText("1")
                }
                "17" -> {
                    edtAddCardMonth.setText("1")
                }
                "18" -> {
                    edtAddCardMonth.setText("1")
                }
                "19" -> {
                    edtAddCardMonth.setText("1")
                }
                "00" -> {
                    edtAddCardMonth.setText("0")
                }
            }
            val text = edtAddCardMonth.text.toString()
            if (edtAddCardMonth.text.length == 3 && !text.contains("/"))
                edtAddCardMonth.setText(text.substring(0, 2) + "/" + text.substring(2, 3))
            edtAddCardMonth.setSelection(edtAddCardMonth.text.toString().length)
        }
    }
}

fun RecyclerView.scrollStart() {
    adapter?.registerAdapterDataObserver(object :
        RecyclerView.AdapterDataObserver() {
        override fun onChanged() {
            scrollToPosition(0)
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            scrollToPosition(0)
        }

        override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
            scrollToPosition(0)
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            scrollToPosition(0)
        }

        override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
            scrollToPosition(0)
        }

        override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
            scrollToPosition(0)
        }
    })
}