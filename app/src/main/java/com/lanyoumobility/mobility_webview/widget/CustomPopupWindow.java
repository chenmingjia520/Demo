package com.lanyoumobility.mobility_webview.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;


/**
 * className: CustomPopupWindow
 * description: 弹窗
 * author: hong
 * datetime: 2016/3/31 0031 上午 10:00
 */
public class CustomPopupWindow extends PopupWindow {

    protected Activity context;
    private View container; //decorView
    private View anchor;
    private int layoutId;
    private View mContentView; //附加的view  的 根view
    private int width = WindowManager.LayoutParams.MATCH_PARENT;
    private int height = WindowManager.LayoutParams.MATCH_PARENT;
    private Drawable drawable = new ColorDrawable(Color.TRANSPARENT);
    private int animStyleId = -1;
    private boolean touchable = true;
    private int gravity = Gravity.BOTTOM;
    private int xOffset;
    private int yOffset;
    private float alpha = 1.0f;

    private boolean isCreated;

    public CustomPopupWindow(Activity context) {
        this.context = context;
        this.container = context.getWindow().getDecorView();
    }


    public View getContentView() {
        return mContentView;
    }

    @Override
    public void dismiss() {
        setWindowBackgroundAlpha(context, 1.0f);
        super.dismiss();
    }


    /**
     * 设置父容器
     *
     * @param container
     * @return
     */
    public CustomPopupWindow setPopContainer(View container) {
        this.container = container;
        return this;
    }

    /**
     * 设置锚点控件
     *
     * @param anchor
     * @return
     */
    public CustomPopupWindow setPopAnchor(View anchor) {
        this.anchor = anchor;
        return this;
    }

    /**
     * 设置layoutID，需在{@link #create()}和{@link #show()}之前调用
     *
     * @param layoutId
     * @return
     */
    public CustomPopupWindow setPopLayoutId(int layoutId) {
        this.layoutId = layoutId;
        return this;
    }

    public CustomPopupWindow setPopWidth(int width) {
        this.width = width;
        return this;
    }

    public CustomPopupWindow setPopHeight(int height) {
        this.height = height;
        return this;
    }

    public CustomPopupWindow setBgDrawable(Drawable drawable) {
        this.drawable = drawable;
        return this;
    }

    public CustomPopupWindow setPopOutsideTouchable(boolean touchable) {
        this.touchable = touchable;
        return this;
    }

    public CustomPopupWindow setPopGravity(int gravity) {
        this.gravity = gravity;
        return this;
    }

    public CustomPopupWindow setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        return this;
    }

    public CustomPopupWindow setPopAnimStyleId(int animStyleId) {
        this.animStyleId = animStyleId;
        return this;
    }

    public CustomPopupWindow setPopBackgroundAlpha(float alpha) {
        this.alpha = alpha;
        return this;
    }

    public CustomPopupWindow setPopDismissListener(OnDismissListener listener) {
        this.setOnDismissListener(listener);
        return this;
    }


    public CustomPopupWindow create() {
        if (!isCreated) {
            mContentView = View.inflate(context, layoutId, null);
            this.setContentView(mContentView);
            this.setWidth(width);
            this.setHeight(height);
            this.setBackgroundDrawable(drawable);
            this.setAnimationStyle(animStyleId);
            this.setFocusable(true);
            this.setOutsideTouchable(touchable);
            isCreated = true;
        }
        return this;
    }

    public CustomPopupWindow create2() {
        if (!isCreated) {
            mContentView = View.inflate(context, layoutId, null);
            this.setContentView(mContentView);
            this.setWidth(width);
            this.setHeight( fullHeight() );
            this.setBackgroundDrawable(drawable);
            this.setAnimationStyle(animStyleId);
            this.setFocusable(true);
            this.setOutsideTouchable(touchable);
            isCreated = true;
        }
        return this;
    }



    private int fullHeight() {
        // 设置弹出窗体的宽和高,  高为屏幕的高+顶部状态栏的高
        int fullHeight = context.getResources().getDisplayMetrics().heightPixels;//屏幕的高
        return fullHeight + getStatusBarHeight(context);
    }

    /**
     * 获取状态栏高度
     */
    private int getStatusBarHeight(Context context) {
        // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

    /**
     * 显示窗口，如果调用了{@link #setPopAnchor(View)}设置锚点，则会相对于锚点控件显示窗口，无需设置Gravity，否则相对父容器显示
     */
    public void show() {
        if (isCreated) {
            setWindowBackgroundAlpha(context, alpha);
            if (null != anchor) {
                this.showAsDropDown(anchor, xOffset, yOffset);
            } else {
                context.getWindow().getDecorView().post(new Runnable() {
                    @Override
                    public void run() {
                        CustomPopupWindow.this.showAtLocation(container, gravity, xOffset, yOffset);
                    }
                });
            }
        } else {
            create();
            show();
        }
    }

    /**
     * 获取popupWindow的宽高，需在{@link #create()}之后调用
     *
     * @return popupWindow的宽和高
     */
    public int[] getContentViewSize() {
        mContentView.measure(0, 0);
        return new int[]{mContentView.getMeasuredWidth(), mContentView.getMeasuredHeight()};
    }

    /**
     * 设置背景透明度
     *
     * @param activity
     * @param bgAlpha
     */
    protected void setWindowBackgroundAlpha(Activity activity, float bgAlpha) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.alpha = bgAlpha;
        window.setAttributes(attributes);
    }


}
