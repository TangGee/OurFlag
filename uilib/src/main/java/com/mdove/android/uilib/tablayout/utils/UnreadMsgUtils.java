package com.mdove.android.uilib.tablayout.utils;


import android.view.View;
import android.widget.RelativeLayout;


import com.mdove.android.uilib.R;
import com.mdove.android.uilib.tablayout.widget.MsgView;
import com.mdove.android.uilib.utils.UIUtils;

import androidx.constraintlayout.widget.ConstraintLayout;


/**
 * 未读消息提示View,显示小红点或者带有数字的红点:
 * 数字一位,圆
 * 数字两位,圆角矩形,圆角是高度的一半
 * 数字超过两位,显示99+
 */
public class UnreadMsgUtils {
    public static void showWithConstraintLayout(MsgView msgView, int num) {
        if (msgView == null) {
            return;
        }
        ConstraintLayout.LayoutParams lp = (ConstraintLayout.LayoutParams) msgView.getLayoutParams();
        msgView.setVisibility(View.VISIBLE);
        if (num <= 0) {//圆点,设置默认宽高
            msgView.setStrokeWidth(0);
            msgView.setText("");

            int px4 = (int) UIUtils.dip2Px(msgView.getContext(), 4);
            lp.width = px4;
            lp.height = px4;
            msgView.setLayoutParams(lp);
        } else {
            int px18 = (int) UIUtils.dip2Px(msgView.getContext(), 18);
            lp.height = px18;
            if (num > 0 && num < 10) {//圆
                lp.width = px18;
                msgView.setText(num + "");
            } else if (num > 9 && num < 100) {//圆角矩形,圆角是高度的一半,设置默认padding
                lp.width = ConstraintLayout.LayoutParams.WRAP_CONTENT;
                int px4 = (int) UIUtils.dip2Px(msgView.getContext(), 4);
                msgView.setPadding(px4, 0, px4, 0);
                msgView.setText(num + "");
            } else {//数字超过两位,显示99+
                lp.width = ConstraintLayout.LayoutParams.WRAP_CONTENT;
                int px4 = (int) UIUtils.dip2Px(msgView.getContext(), 4);
                msgView.setPadding(px4, 0, px4, 0);
                msgView.setText("99+");
            }
            msgView.setLayoutParams(lp);
        }
    }
    public static void showForBuzz(MsgView msgView, int num,boolean not10Plus) {
        if (msgView == null) {
            return;
        }
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) msgView.getLayoutParams();
        msgView.setVisibility(View.VISIBLE);
        if (num <= 0) {//圆点,设置默认宽高
            msgView.setStrokeWidth(0);
            msgView.setText("");
            int px4 = (int) UIUtils.dip2Px(msgView.getContext(), 4);
            lp.width = px4;
            lp.height = px4;
            lp.addRule(RelativeLayout.RIGHT_OF, R.id.tv_tab_title);
            lp.addRule(RelativeLayout.ABOVE, R.id.tv_tab_title);
            lp.leftMargin=4;
            msgView.setLayoutParams(lp);
        } else {
            int px18 = (int) UIUtils.dip2Px(msgView.getContext(), 18);
            lp.height = px18;
            if (num > 0 && num < 10) {//圆
                lp.width = px18;
                msgView.setText(num + "");
            } else if (num > 9 && num < 100) {//圆角矩形,圆角是高度的一半,设置默认padding
                lp.width = RelativeLayout.LayoutParams.WRAP_CONTENT;
                int px4 = (int) UIUtils.dip2Px(msgView.getContext(), 4);
                msgView.setPadding(px4, 0, px4, 0);
                if(not10Plus){
                    msgView.setText(num + "");
                }else {
                    msgView.setText(num + "+");
                }
            } else {//数字超过两位,显示99+
                lp.width = RelativeLayout.LayoutParams.WRAP_CONTENT;
                int px4 = (int) UIUtils.dip2Px(msgView.getContext(), 4);
                msgView.setPadding(px4, 0, px4, 0);
                msgView.setText("99+");
            }
            msgView.setLayoutParams(lp);
        }
    }
    public static void show(MsgView msgView, int num,boolean not10Plus) {
        if (msgView == null) {
            return;
        }
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) msgView.getLayoutParams();
        msgView.setVisibility(View.VISIBLE);
        if (num <= 0) {//圆点,设置默认宽高
            msgView.setStrokeWidth(0);
            msgView.setText("");

            int px4 = (int) UIUtils.dip2Px(msgView.getContext(), 4);
            lp.width = px4;
            lp.height = px4;
            msgView.setLayoutParams(lp);
        } else {
            int px18 = (int) UIUtils.dip2Px(msgView.getContext(), 18);
            lp.height = px18;
            if (num > 0 && num < 10) {//圆
                lp.width = px18;
                msgView.setText(num + "");
            } else if (num > 9 && num < 100) {//圆角矩形,圆角是高度的一半,设置默认padding
                lp.width = RelativeLayout.LayoutParams.WRAP_CONTENT;
                int px4 = (int) UIUtils.dip2Px(msgView.getContext(), 4);
                msgView.setPadding(px4, 0, px4, 0);
                if(not10Plus){
                    msgView.setText(num + "");
                }else {
                    msgView.setText(num + "+");
                }
            } else {//数字超过两位,显示99+
                lp.width = RelativeLayout.LayoutParams.WRAP_CONTENT;
                int px4 = (int) UIUtils.dip2Px(msgView.getContext(), 4);
                msgView.setPadding(px4, 0, px4, 0);
                msgView.setText("99+");
            }
            msgView.setLayoutParams(lp);
        }
    }

    public static void setSize(MsgView rtv, int size) {
        if (rtv == null) {
            return;
        }
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) rtv.getLayoutParams();
        lp.width = size;
        lp.height = size;
        rtv.setLayoutParams(lp);
    }
}
