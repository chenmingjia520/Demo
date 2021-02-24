package com.lanyoumobility.mobility_webview.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lanyoumobility.mobility_webview.R;


import java.util.List;



/**
 * Created by wang
 * on 2019/2/20.
 */

public class ActivitiesWindow extends CustomPopupWindow {

    private ConvenientBanner convenientBanner;
    private List list;

    public ActivitiesWindow(Activity context) {
        super(context);
        setPopLayoutId(R.layout.dialog_message_activities);
        setPopAnimStyleId(R.style.showBottomPopAnim);
        setPopHeight(WindowManager.LayoutParams.MATCH_PARENT);
        setPopBackgroundAlpha(1.0f);
        create();
        initView();
    }

    private void initView() {
        View view = getContentView();
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                switch (v.getId()) {

                    case R.id.close:
                        if (mOnCloseClickListener != null) {
                            mOnCloseClickListener.onclick();
                        }
                        break;
                    default:
                }
            }
        };

        view.findViewById(R.id.close).setOnClickListener(listener);
        convenientBanner = view.findViewById(R.id.convenientBanner);
    }

    public void setConvenientData(List list){
        this.list = list;
        convenientBanner.setPages(new CBViewHolderCreator<NetImageHolderView>() {
            @Override
            public NetImageHolderView createHolder() {
                return new NetImageHolderView();
            }
        },list);

        convenientBanner.setOnPageChangeListener(cbPageChangeListener);
        convenientBanner.setOnItemClickListener(cbOnItemClickListener);

        if (list.size() < 2) {
            convenientBanner.setCanLoop(false);
        } else {
            convenientBanner.setCanLoop(true);
        }

        convenientBanner.startTurning(3000);

    }



    private class NetImageHolderView implements Holder<Object> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, final int position,Object object ) {
//            if(object instanceof BannerListBean.DataBean){
//                BannerListBean.DataBean recordsBean = (BannerListBean.DataBean) object;
//                Glide.with(context)
//                        .load(recordsBean.getPictureUrl())  //获取图片路径
//                        .placeholder(cn.ptaxi.yueyun.ridesharing.R.mipmap.ic_rentcar_home_bg)
//                        .error(cn.ptaxi.yueyun.ridesharing.R.mipmap.ic_rentcar_home_bg)
//                        .apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(DensityUtils.dpTopx(context,5), 0)))
//                        .into(imageView);
//            }else if(object instanceof RentCarLunBoImgBean.DataBean.Advert){
//                RentCarLunBoImgBean.DataBean.Advert advert = (RentCarLunBoImgBean.DataBean.Advert) object;
//                Glide.with(context)
//                        .load(advert.pictureUrl)  //获取图片路径
//                        .placeholder(cn.ptaxi.yueyun.ridesharing.R.mipmap.ic_rentcar_home_bg)
//                        .error(cn.ptaxi.yueyun.ridesharing.R.mipmap.ic_rentcar_home_bg)
//                        .apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(DensityUtils.dpTopx(context,5), 0)))
//                        .into(imageView);
//            }

        }
    }

    /**
     * 关闭弹窗的监听回调
     */
    public interface OnCloseClickListener {
        /**
         * 关闭弹窗的回调方法
         **/
        void onclick();
    }

    private OnCloseClickListener mOnCloseClickListener;

    public void setOnCloseClickListener(OnCloseClickListener mOnCloseClickListener) {
        this.mOnCloseClickListener = mOnCloseClickListener;
    }

    private ViewPager.OnPageChangeListener cbPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
    private OnItemClickListener cbOnItemClickListener  = new OnItemClickListener() {
        @Override
        public void onItemClick(int position) {
            dismiss();
            Object object = list.get(position);
//            if(object instanceof BannerListBean.DataBean){
//                BannerListBean.DataBean recordsBean = (BannerListBean.DataBean) object;
//
//                Intent intent =  new Intent(context, RentCarWebActivity.class);
//                intent.putExtra("Title",recordsBean.getTitle());
//                intent.putExtra("content",(String) recordsBean.getDescribe());
//                intent.putExtra("URL",recordsBean.getRedirectUrl());
//                context.startActivity(intent);
//            }else if(object instanceof RentCarLunBoImgBean.DataBean.Advert){
//                RentCarLunBoImgBean.DataBean.Advert advert = (RentCarLunBoImgBean.DataBean.Advert) object;
//                String url = advert.redirectUrl;
//                if(!StringUtils.isEmpty(url)){
//                    Intent intent = new Intent(context, RentCarWebActivity.class);
//                    intent.putExtra("Title", advert.title);
//                    intent.putExtra("URL", url);
//                    context.startActivity(intent);
//                }
//            }

            //Toast.makeText(context, "当前点击的是第"+(position+1)+"张图片", Toast.LENGTH_SHORT).show();
        }
    };


}
