package com.lanyoumobility.mobility_webview.db.dao;


import com.lanyoumobility.mobility_webview.db.ImageInfo;

import org.litepal.LitePal;

import java.util.List;

/**
 *
 *
 */
public class ImageInfoDao {

    private static ImageInfoDao instance;
    /**
     * @Description: 获取 ImageInfoDao 操作类实体
     * @return VoucherDao
     * @author 陈明佳
     * @date 2020/7/16 13:54
     */
    public static ImageInfoDao getInstance() {
        if (instance == null) {
            synchronized (ImageInfoDao.class) {
                if (instance == null) {
                    instance = new ImageInfoDao();
                }
            }
        }
        return instance;
    }


    /**
     * @Description: 保存或者更新数据(同步数据的时候使用)
     * @param imageInfo
     * @return boolean
     * @author 陈明佳
     * @date  2020/7/16 13:54
     */
    public boolean saveVoucher(ImageInfo imageInfo){
        if(imageInfo==null){
            return false;
        }
        return  imageInfo.save();
    }

    /**
     * @Description: 删除单个代步券
     * @return int
     * @author 陈明佳
     * @date  2020/7/16 13:54
     */
    public int deleteVoucher(ImageInfo imageInfo){
        if(imageInfo==null){
            return 0;
        }
        return LitePal.deleteAll(ImageInfo.class, "id= ?", String.valueOf(imageInfo.getId()));
    }


    /**
     * @Description: 查询所有的代步券
     * @return List<Voucher>
     * @author 陈明佳
     * @date 2020/3/27 15:11
     */
    public List<ImageInfo> findAllImageInfos(){
        return LitePal.findAll(ImageInfo.class);
    }


    /**
     * @Description: 删除所有的岩芯指标
     * @author 陈明佳
     * @date 2020/3/27 15:11
     */
    public int deleteAllVoucher(){
        return LitePal.deleteAll(ImageInfo.class);
    }

}
