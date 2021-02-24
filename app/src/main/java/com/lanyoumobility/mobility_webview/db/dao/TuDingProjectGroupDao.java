package com.lanyoumobility.mobility_webview.db.dao;



import com.lanyoumobility.mobility_webview.db.TuDingProjectGroup;
import com.lanyoumobility.mobility_webview.utils.DateTimeUtils;
import com.lanyoumobility.mobility_webview.utils.StringUtils;
import com.lanyoumobility.mobility_webview.utils.UUIDUtils;

import org.litepal.LitePal;

import java.util.Date;
import java.util.List;


/**
 *代步券表的操作类
 */
public class TuDingProjectGroupDao {
    private static TuDingProjectGroupDao instance;
    /**
     * @Description: 获取 TuDingProjectGroupDao 操作类实体
     * @return TuDingProjectGroupDao
     * @author 陈明佳
     * @date 2020/7/16 13:54
     */
    public static TuDingProjectGroupDao getInstance() {
        if (instance == null) {
            synchronized (TuDingProjectGroupDao.class) {
                if (instance == null) {
                    instance = new TuDingProjectGroupDao();
                }
            }
        }
        return instance;
    }


    /**
     * @Description: 保存或者更新数据(同步数据的时候使用)
     * @param projectGroupName
     * @return boolean
     * @author 陈明佳
     * @date  2020/7/16 13:54
     */
    public boolean saveTuDingProjectGroup(String projectGroupName){
        List<TuDingProjectGroup> projectGroupList = findTuDingProjectGroupsByName(projectGroupName);
        if(projectGroupList!=null&&projectGroupList.size()>0){
            return  false;
        }
        TuDingProjectGroup tuDingProjectGroup = new TuDingProjectGroup();
        tuDingProjectGroup.setCreateDate(DateTimeUtils.definitionDate2(new Date()));
        tuDingProjectGroup.setModifyDate(DateTimeUtils.definitionDate2(new Date()));
        tuDingProjectGroup.setCreateUserId("123456789");
        tuDingProjectGroup.setProjectGroupName(projectGroupName);
        tuDingProjectGroup.setProjectGroupId(UUIDUtils.getUUID());

        return  tuDingProjectGroup.save();
    }




    /**
     * @Description: 保存或者更新数据(同步数据的时候使用)
     * @param projectGroup
     * @return boolean
     * @author 陈明佳
     * @date  2020/7/16 13:54
     */
    public boolean saveTuDingProjectGroup(TuDingProjectGroup projectGroup){
        if(projectGroup==null){
            return false;
        }
        return  projectGroup.saveOrUpdate("projectGroupId=?", projectGroup.getProjectGroupId());
    }

    /**
     * @Description: 保存或者更新数据
     * @param projectGroupList
     * @return int 保存成功个数
     * @author 陈明佳
     * @date  2020/7/16 13:54
     */
    public int saveTuDingProjectGroups(List<TuDingProjectGroup> projectGroupList){
        int index = 0;
        for(TuDingProjectGroup projectGroup : projectGroupList){
            if(saveTuDingProjectGroup(projectGroup)){
                index++;
            }
        }
        return index;
    }

    /**
     * @Description: 删除单个
     * @return int
     * @author 陈明佳
     * @date  2020/7/16 13:54
     */
    public int  deleteTuDingProjectGroup(TuDingProjectGroup projectGroup){
        if(projectGroup==null){
            return 0;
        }

        return LitePal.deleteAll(TuDingProjectGroup.class, "projectGroupId= ?", String.valueOf(projectGroup.getProjectGroupId()));
    }


    /**
     * @Description: 查询所有的
     * @return List<TuDingProjectGroup>
     * @author 陈明佳
     * @date 2020/3/27 15:11
     */
    public List<TuDingProjectGroup> findAllTuDingProjectGroups(){
        return LitePal.findAll(TuDingProjectGroup.class);
    }


    /**
     * @Description: 查询所有的
     * @return List<TuDingProjectGroup>
     * @author 陈明佳
     * @date 2020/3/27 15:11
     */
    public List<TuDingProjectGroup> findTuDingProjectGroupsByName(String projectGroupName){
        return  LitePal.where("projectGroupName = ?", projectGroupName).find(TuDingProjectGroup.class);
    }


    /**
     * @Description: 模糊查询
     * @return List<TuDingProjectGroup>
     * @author 陈明佳
     * @date 2020/3/27 15:11
     */
    public List<TuDingProjectGroup> likeTuDingProjectGroupsByName(String projectGroupName){
        if(StringUtils.isEmpty(projectGroupName)){
            return LitePal.findAll(TuDingProjectGroup.class);
        }
        return  LitePal.where("projectGroupName like ?","%" + projectGroupName + "%").find(TuDingProjectGroup.class);
    }


    /**
     * @Description: 删除所有的
     * @author 陈明佳
     * @date 2020/3/27 15:11
     */
    public int deleteAllTuDingProjectGroup(){
        return LitePal.deleteAll(TuDingProjectGroup.class);
    }
}
