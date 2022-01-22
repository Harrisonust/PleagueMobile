package com.example.gamechangermobile.views

import android.content.Context
import android.util.AttributeSet
import android.widget.HorizontalScrollView

class HorizontalScroll : HorizontalScrollView {
    private var scrollViewListener: ScrollViewListener? = null

    interface ScrollViewListener {
        fun onScrollChanged(scrollView: HorizontalScroll?, x: Int, y: Int, oldx: Int, oldy: Int)
    }

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
    }

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