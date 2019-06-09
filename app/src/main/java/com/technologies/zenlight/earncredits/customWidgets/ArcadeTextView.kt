package com.technologies.zenlight.earncredits.customWidgets

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.technologies.zenlight.earncredits.R

class ArcadeTextView : TextView {

    constructor(context: Context) : super(context) {
        setFontType()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs){
        setFontType()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        setFontType()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int,
                defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes){
        setFontType()
    }

    private fun setFontType(){
        val typeface = ResourcesCompat.getFont(context,R.font.arcade_font)
        setTypeface(typeface)
    }
}