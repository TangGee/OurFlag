package com.mdove.android.uilib.tablayout.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.mdove.android.uilib.R

/**
 * Created by MDove on 2019/4/22.
 */
class MDoveFeedTabView @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : RelativeLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.layout_tab, this)
    }
}