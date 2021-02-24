package com.lanyoumobility.mobility_webview.widget;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lanyoumobility.mobility_webview.R;
import com.lanyoumobility.mobility_webview.utils.L;
import com.lanyoumobility.mobility_webview.utils.StringUtils;
import com.lanyoumobility.mobility_webview.utils.ToastUtil;
import com.lanyoumobility.mobility_webview.view.ClearableEditText;

import java.util.List;


/**
 * Created by wang
 * on 2019/2/20.
 */

public class NewProjectWindow extends CustomPopupWindow {

    private Context context;

    public NewProjectWindow(Activity context){
        super(context);
        this.context = context;
        setPopLayoutId(R.layout.dialog_new_project);
        setPopAnimStyleId(R.style.showBottomPopAnim);
        setPopHeight(WindowManager.LayoutParams.MATCH_PARENT);
        setPopBackgroundAlpha(1.0f);
        create();
    }

    private ClearableEditText et_project_name;

    public void initView(String filePath) {
        View view = getContentView();
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.tv_cancel:
                        dismiss();
                        break;
                    case R.id.tv_ok:
                        String projectName = et_project_name.getText().toString().trim();
                        if(StringUtils.isEmpty(projectName)){
                            ToastUtil.showToast(context,"请输入名称");
                            return;
                        }
                        if(mOnCloseClickListener!=null){
                            mOnCloseClickListener.onclick(projectName);
                        }
                        dismiss();
                        break;
                    default:
                }
            }
        };
        et_project_name = view.findViewById(R.id.et_project_name);
        view.findViewById(R.id.tv_cancel).setOnClickListener(listener);
        view.findViewById(R.id.tv_ok).setOnClickListener(listener);
        L.i("ImportProjectActivity","filePath:::::::::::::"+filePath);
        if(!StringUtils.isEmpty(filePath)){
            ImageView iv_item = view.findViewById(R.id.iv_item);
            Glide.with(context).load(filePath).diskCacheStrategy(DiskCacheStrategy.NONE).override(100,100).error(R.drawable.iv_gridview_item_error_bg).into(iv_item);
//            GlideApp.with(this).load("http://ww4.sinaimg.cn/large/610dc034gw1f96kp6faayj20u00jywg9.jpg").into(iv_item);
        }


    }


    private OnCloseClickListener mOnCloseClickListener;

    public void setOnCloseClickListener(OnCloseClickListener mOnCloseClickListener) {
        this.mOnCloseClickListener = mOnCloseClickListener;
    }


    /**
     * 关闭弹窗的监听回调
     */
    public interface OnCloseClickListener {
        /**
         * 关闭弹窗的回调方法
         **/
        void onclick(String project);
    }







}
