package com.fedlo.tom.base.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.fedlo.tom.base.data.Profile
import java.util.*


val Number.toPx get() = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this.toFloat(),
    Resources.getSystem().displayMetrics
)

fun Fragment.dpToPx(dp: Float): Float {
    return dp * (requireContext().resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}
fun Fragment.dpToPxInt(dp: Int): Int {
    return dp* (requireContext().resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)
}

fun EditText.actionDone(callback: (() -> Unit)? = null) {
    val action = EditorInfo.IME_ACTION_DONE
//    this.multilineIme(action)
    this.imeOptions = action
    setOnEditorActionListener { _, actionId, _ ->
        if (action == actionId) {
            callback?.invoke()
            true
        }
        false
    }
}

fun Fragment.pxToDp(px: Float): Float {
    return px / (requireContext().resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}
fun drawableToBitmap(drawable: Drawable): Bitmap {
    if (drawable is BitmapDrawable) {
        if (drawable.bitmap != null) {
            return drawable.bitmap
        }
    }
    var bitmap: Bitmap = if (drawable.intrinsicWidth <= 0 || drawable.intrinsicHeight <= 0) {
        Bitmap.createBitmap(
            1,
            1,
            Bitmap.Config.ARGB_8888
        ) // Single color bitmap will be created of 1x1 pixel
    } else {
        Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
    }
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight())
    drawable.draw(canvas)
    return bitmap
}

fun setLocale(activity: Activity, languageCode: String) {
    val locale = Locale(languageCode)
    Locale.setDefault(locale)
    val resources = activity.resources
    val config: Configuration = resources.configuration
    config.setLocale(locale)
    resources.updateConfiguration(config, resources.displayMetrics)
}

fun Context.changeLocale(isRus:Boolean){
    if (isRus)
        changeLocales("ru")
    else
        changeLocales("kk")
}
fun Context.changeLocales(lang:String="kk"){
    val config = resources.configuration
//    val lang = "en" // your language code
    val locale = Locale(lang)
    Locale.setDefault(locale)
    config.setLocale(locale)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        createConfigurationContext(config)
    resources.updateConfiguration(config, resources.displayMetrics)
}

fun Fragment.showKeyBoard(editText: EditText) {
    val im: InputMethodManager by lazy { activity?.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager }
    editText.requestFocus()
    im.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Activity.hideKeyboard() {
    this.currentFocus?.let { view ->
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun Fragment.hideKeyboard() {
    requireActivity().hideKeyboard()
}
fun TextView.makeLinks(vararg links: Pair<String, View.OnClickListener>) {
    val spannableString = SpannableString(this.text)
    var startIndexOfLink = -1
    for (link in links) {
        val clickableSpan = object : ClickableSpan() {
            override fun updateDrawState(textPaint: TextPaint) {
                // use this to change the link color
                textPaint.color = textPaint.linkColor
                // toggle below value to enable/disable
                // the underline shown below the clickable text
                textPaint.isUnderlineText = true
            }

            override fun onClick(view: View) {
                Selection.setSelection((view as TextView).text as Spannable, 0)
                view.invalidate()
                link.second.onClick(view)
            }
        }
        startIndexOfLink = this.text.toString().indexOf(link.first, startIndexOfLink + 1)
//      if(startIndexOfLink == -1) continue // todo if you want to verify your texts contains links text
        spannableString.setSpan(
            clickableSpan, startIndexOfLink, startIndexOfLink + link.first.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
    this.movementMethod =
        LinkMovementMethod.getInstance() // without LinkMovementMethod, link can not click
    this.setText(spannableString, TextView.BufferType.SPANNABLE)
}
val isNext = MutableLiveData<Boolean>()
var spammers= mutableListOf<Int>()

fun isSpammers(list:List<Profile>):List<Profile>{
    val newList = mutableSetOf<Profile>()
    list.forEach {
        if (!spammers.contains(it.imageUrl))
            newList.add(it)
    }
    return newList.toMutableList()
}
fun Context.showSpamReport(cards: Profile){
    val builder = AlertDialog.Builder(this)
    builder.create()
    builder.setTitle("Other Users Reported:")
    reportedScammmer.forEach {
        if (it.second.imageUrl==cards.imageUrl){
            builder.setMessage(it.first)
        }else{
            Toast.makeText(this,"Other Users Reported to this account", Toast.LENGTH_SHORT).show()
        }
    }
    builder.show()


}

fun isCArdSpammed(cards: Profile):Boolean{
    var isspammed=false
    reportedScammmer.forEach {
        if (it.second.imageUrl==cards.imageUrl)
            isspammed=true
    }
    return isspammed
}
fun isCardBlocked(cards: Profile):Boolean{
    var isspammed=false
    reportedScammmer.forEach {
        if (it.second.imageUrl == cards.imageUrl && (it.first.contains("Block")||it.first.contains("Remove and don't show again")))
            isspammed=true
    }
    return isspammed
}
//fun matchToProfile(match: Match):Profile{
//   return Profile().apply {
//        name=match.name
//        imageUrl=match.picture.toString()
//        location=match.location.toString()
//    }
//}
val reportedScammmer = mutableListOf<Pair<String, Profile>>()
@SuppressLint("SuspiciousIndentation")
fun Context.showScammerDialog(cards: Profile, block:()->Unit){
    val builder = AlertDialog.Builder(this)
    builder.create()
    builder.setTitle("Report")

    val arrayAdapter =
        ArrayAdapter<String>(this, android.R.layout.simple_list_item_1)
//            arrayAdapter.add("Dream Girls")






    arrayAdapter.add("Underage user")
    arrayAdapter.add("Scammer")
    arrayAdapter.add("Remove")

    arrayAdapter.add("Calls for breaking the law")
    arrayAdapter.add("Copyright infringer")
    arrayAdapter.add("Block")
    arrayAdapter.add("Sexual content")
    arrayAdapter.add("Other ")





    builder.setAdapter(arrayAdapter) { p0, p1 ->
        try {
            if ( arrayAdapter.getItem(p1)?.contains("Remove")==true||arrayAdapter.getItem(p1)?.contains("Delete")==true){
                spammers.add(cards.imageUrl)
                isNext.value = true
            }
            var added=false
                reportedScammmer.forEach {
                    if (it.second.imageUrl==cards.imageUrl){
                        val rep=it.first
                        reportedScammmer.remove(it)
                        reportedScammmer.add(Pair(rep+"\n"+arrayAdapter.getItem(p1)?:"",cards))
                        added=true
                    }
                }
            if (!added)
                reportedScammmer.add(Pair(arrayAdapter.getItem(p1)?:"",cards))
            block()
            Toast.makeText(this,"Your complaint has been sent", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
        }


    }
    builder.show()
}