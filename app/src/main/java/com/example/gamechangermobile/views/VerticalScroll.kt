package com.example.gamechangermobile.views

import android.content.Context
import android.util.AttributeSet
import android.widget.ScrollView

class VerticalScroll : ScrollView {
    private var scrollViewListener: ScrollViewListener? = null

    interface ScrollViewListener {
        fun onScrollChanged(scrollView: VerticalScroll?, x: Int, y: Int, oldx: Int, oldy: Int)
    }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    fun setScrollViewListener(scrollViewListener: ScrollViewListener?) {
        this.scrollViewListener = scrollViewListener
    }

    override fun onScrollChanged(x: Int, y: Int, oldx: Int, oldy: Int) {
        super.onScrollChanged(x, y, oldx, oldy)
        if (scrollViewListener != null) {
            scrollViewListener!!.onScrollChanged(this, x, y, oldx, oldy)
        }
    }
}