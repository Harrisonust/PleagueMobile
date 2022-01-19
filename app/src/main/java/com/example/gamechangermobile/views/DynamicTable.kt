package com.example.gamechangermobile.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.gamechangermobile.R

class DynamicTable(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs), HorizontalScroll.ScrollViewListener, VerticalScroll.ScrollViewListener {
    private var fixedRelativeLayout: RelativeLayout? = null
    private var headerRelativeLayout: RelativeLayout? = null
    private var columnRelativeLayout: RelativeLayout? = null

    private var headerTableLayout: TableLayout? = null
    private var columnTableLayout: TableLayout? = null
    private var contentTableLayout: TableLayout? = null

    private var headerHorizontalScrollView: HorizontalScroll? = null
    private var contentHorizontalScrollView: HorizontalScroll? = null

    private var columnVerticalScrollView: VerticalScroll? = null
    private var contentVerticalScrollView: VerticalScroll? = null

    init {
        inflate(context, R.layout.dynamic_table_layout, this)
        initViews()
        initScrollViewListeners()

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.DynamicTable,
            0, 0)?.apply {
            try {
                val shadow = getInteger(R.styleable.DynamicTable_shadow, 0)
                val headerCardView: CardView = findViewById(R.id.header_card_view)
                headerCardView.cardElevation = shadow.toFloat()
                val columnCardView: CardView = findViewById(R.id.column_card_view)
                columnCardView.cardElevation = shadow.toFloat()

            } finally {
                recycle()
            }

            // init default dimensions
            //initDimensions(100, 200)
        }
    }

    fun renderTable(
        headers: List<String>,
        contents: List<List<String>>,
        headerHeight: Int,
        columnWidth: Int,
        headerLayoutName: String,
        headerTextViewName: String,
        columnLayoutName: String,
        columnTextViewName: String,
        columnImageViewName: String,
        contentLayoutName: String,
        contentTextViewName: String
    ) {
        fixedRelativeLayout?.let { it.layoutParams.height = headerHeight }
        fixedRelativeLayout?.let { it.layoutParams.width = columnWidth }
        headerRelativeLayout?.let { it.layoutParams.height = headerHeight }
        columnRelativeLayout?.let { it.layoutParams.width = columnWidth }

        val headerViewId = resources.getIdentifier(headerLayoutName, "layout", context.packageName)
        val headerTextId = resources.getIdentifier(headerTextViewName, "id", context.packageName)
        val tableRow = TableRow(context)
        for (i in headers.indices) {
            if (i == 0) {
                val view = LayoutInflater.from(context).inflate(headerViewId, fixedRelativeLayout, false)
                val textView: TextView = view.findViewById(headerTextId)
                textView.text = headers[i]
                view.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                fixedRelativeLayout?.addView(view)

            }
            else {
                renderCell(headers[i], headerViewId, headerTextId, tableRow)
            }
        }
        headerTableLayout?.addView(tableRow)


        val columnViewId = resources.getIdentifier(columnLayoutName, "layout", context.packageName)
        val columnTextId = resources.getIdentifier(columnTextViewName, "id", context.packageName)
        val columnImageId = resources.getIdentifier(columnImageViewName, "id", context.packageName)
        val contentViewId = resources.getIdentifier(contentLayoutName, "layout", context.packageName)
        val contentTextId = resources.getIdentifier(contentTextViewName, "id", context.packageName)

        for (content in contents) {
            val columnTableRow = TableRow(context)
            renderCell(content[1], content[0], columnViewId, columnTextId, columnImageId, columnTableRow)
            columnTableLayout?.addView(columnTableRow)

            val contentTableRow = TableRow(context)
            for (i in 2 until content.size) {
                renderCell(content[i], contentViewId, contentTextId, contentTableRow)
            }
            contentTableLayout?.addView(contentTableRow)
        }

    }

    fun renderCell(text: String, viewId: Int, textId: Int, tableRow: TableRow) {
        val view = LayoutInflater.from(context).inflate(viewId, tableRow, false)
        val textView: TextView = view.findViewById(textId)
        textView.text = text
        tableRow.addView(view)
    }

    fun renderCell(text: String, image: String, viewId: Int, textId: Int, imageId: Int, tableRow: TableRow) {
        val view = LayoutInflater.from(context).inflate(viewId, tableRow, false)
        val textView: TextView = view.findViewById(textId)
        textView.text = text
        val imageResource = resources.getIdentifier(image, "drawable", context.packageName)
        val imageView: ImageView = view.findViewById(imageId)
        imageView.setImageResource(imageResource)
        tableRow.addView(view)
    }


    private fun initViews() {
        fixedRelativeLayout = findViewById(R.id.fixed_section)
        headerRelativeLayout = findViewById(R.id.header_section)
        columnRelativeLayout = findViewById(R.id.column_section)

        headerTableLayout = findViewById(R.id.header_table_layout)
        columnTableLayout = findViewById(R.id.column_table_layout)
        contentTableLayout = findViewById(R.id.content_table_layout)

        headerHorizontalScrollView = findViewById(R.id.header_scrollview)
        columnVerticalScrollView = findViewById(R.id.column_scrollview)
        contentVerticalScrollView = findViewById(R.id.content_vertical_scrollview)
        contentHorizontalScrollView = findViewById(R.id.content_horizontal_scrollview)
    }

    private fun initScrollViewListeners() {
        headerHorizontalScrollView?.let {
            it.setScrollViewListener(this)
            it.isHorizontalScrollBarEnabled = false
        }
        contentHorizontalScrollView?.let {
            it.setScrollViewListener(this)
            it.isHorizontalScrollBarEnabled = false
        }
        columnVerticalScrollView?.let {
            it.setScrollViewListener(this)
            it.isVerticalScrollBarEnabled = false
        }
        contentVerticalScrollView?.let {
            it.setScrollViewListener(this)
            it.isVerticalScrollBarEnabled = false
        }
    }

    override fun onScrollChanged(
        scrollView: HorizontalScroll?,
        x: Int,
        y: Int,
        oldx: Int,
        oldy: Int
    ) {
        if(scrollView == headerHorizontalScrollView){
            contentHorizontalScrollView?.scrollTo(x,y);
        }
        else if(scrollView == contentHorizontalScrollView){
            headerHorizontalScrollView?.scrollTo(x, y);
        }
    }

    override fun onScrollChanged(
        scrollView: VerticalScroll?,
        x: Int,
        y: Int,
        oldx: Int,
        oldy: Int
    ) {
        if (scrollView == columnVerticalScrollView) {
            contentVerticalScrollView?.scrollTo(x, y)
        } else if (scrollView == contentVerticalScrollView) {
            columnVerticalScrollView?.scrollTo(x, y)
        }
    }
}
