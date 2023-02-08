package com.ayan.razorpayassignment

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.BitmapFactory
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi

fun Context.dpToPx(dp: Int): Float {
    return (dp * resources.displayMetrics.density)
}

fun View.show(){
    visibility = View.VISIBLE
}
fun View.hide(){
    visibility = View.GONE
}

fun Context.getEditText(hint: String, key: String): EditText {
    val editText = EditText(this)
    val params = LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
    editText.layoutParams = params
    editText.hint = hint
    editText.id = key.hashCode()
    return editText
}

fun Context.getLabel(value: String, key: String): TextView {
    val textView = TextView(this)
    val params = LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
    params.setMargins(0,dpToPx(10).toInt(),0, dpToPx(5).toInt())
    textView.layoutParams = params
    textView.textSize = dpToPx(5)
    textView.text = value
    textView.id = key.hashCode()
    return textView;
}

fun Context.getTextView(value: String, key: String): TextView {
    val textView = TextView(this)
    val params = LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
    params.setMargins(0,dpToPx(1).toInt(),0, dpToPx(5).toInt())
    textView.layoutParams = params
    textView.textSize = dpToPx(7)
    textView.text = value
    textView.id = key.hashCode()
    return textView;
}

@RequiresApi(Build.VERSION_CODES.M)
fun Context.getButton(value: String): Button {
    val button = Button(this)
    val params = LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
    params.setMargins(0,dpToPx(10).toInt(),0, dpToPx(10).toInt())
    button.layoutParams = params
    button.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.teal_700, null))
    button.textSize = dpToPx(5)
    button.setTextColor(getColor(R.color.button_text))
    button.text = value
    button.id = value.hashCode()
    return button
}

fun Context.getHeading(value: String): TextView {
    val textView = TextView(this)
    val params = LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
    params.setMargins(0,dpToPx(10).toInt(),0, dpToPx(12).toInt())
    textView.layoutParams = params
    textView.textSize = dpToPx(8)
    textView.text = value
    textView.id = value.hashCode()
    return textView;
}

fun Context.getLogoImage(value: ByteArray): ImageView {
    val imageView = ImageView(this)
    val params = LinearLayout.LayoutParams(
        dpToPx(50).toInt(),
        dpToPx(50).toInt()
    )
    params.setMargins(0,dpToPx(10).toInt(),0, dpToPx(10).toInt())
    imageView.layoutParams = params
    imageView.scaleType = ImageView.ScaleType.FIT_CENTER
    imageView.id = value.hashCode()
    val bmp = BitmapFactory.decodeByteArray(value, 0, value.size)
    imageView.setImageBitmap(bmp)
    return imageView;
}