package com.mdove.android.uilib.listener;

import android.view.View;
import android.view.ViewConfiguration;

/**
 * Created by MDove on 2019/4/22.
 */
public abstract class DebounceOnClickListener implements View.OnClickListener {
    public static final long DEFAULT_DELAY = ViewConfiguration.getDoubleTapTimeout();
    private static boolean enabled = true;
    private long delay = DEFAULT_DELAY;
    private static final Runnable ENABLE_AGAIN = () -> DebounceOnClickListener.enabled = true;

    public DebounceOnClickListener() {
    }

    public DebounceOnClickListener(long delay) {
        this.delay = delay;
    }

    @Override
    public final void onClick(View v) {
        if (enabled) {
            enabled = false;
            v.postDelayed(ENABLE_AGAIN, delay);
            this.doClick(v);
        }

    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public abstract void doClick(View var1);
}