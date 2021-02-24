package com.lanyoumobility.mobility_webview.db.dao;


import com.lanyoumobility.mobility_webview.db.TuDingProject;
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
public class TuDingProjectDao {
    private static TuDingProjectDao instance;
    /**
     * @Description: 获取 TuDingProjectDao 操作类实体
     * @return TuDingProjectDao
     * @author 陈明佳
     * @date 2020/7/16 13:54
     */
    public static TuDingProjectDao getInstance() {
        if (instance == null) {
            synchronized (TuDingProjectDao.class) {
                if (instance == null) {
                    instance = new TuDingProjectDao();
                }
            }
        }
        return instance;
    }



    /**
     * @Description: 保存或者更新数据(同步数据的时候使用)
     * @param
     * @return boolean
     * @author 陈明佳
     * @date  2020/7/16 13:54
     */
    public boolean saveTuDingProject(String projectGroupId,String projectName,String portraitPath){
        List<TuDingProject> projectList = findTuDingProjectByName(projectGroupId,projectName);
        if(projectList!=null&&projectList.size()>0){
            return  false;
        }
        TuDingProject tuDingProject = new TuDingProject();
        tuDingProject.setCreateDate(DateTimeUtils.definitionDate2(new Date()));
        tuDingProject.setModifyDate(DateTimeUtils.definitionDate2(new Date()));
        tuDingProject.setCreateUserId("123456789");
        tuDingProject.setProjectName(projectName);
        tuDingProject.setProjectId(UUIDUtils.getUUID());
        tuDingProject.setProjectGroupId(projectGroupId);
        tuDingProject.setProjectPath(portraitPath);
        return  tuDingProject.save();
    }

    /**
     * @Description: 保存或者更新数据(同步数据的时候使用)
     * @param voucher
     * @return boolean
     * @author 陈明佳
     * @date  2020/7/16 13:54
     */
    public boolean saveTuDingProject(TuDingProject voucher){
        if(voucher==null){
            return false;
        }
        return  voucher.saveOrUpdate("projectId=?", String.valueOf(voucher.getProjectId()));
    }





    /**
     * @Description: 保存或者更新数据
     * @param voucherList
     * @return int 保存成功个数
     * @author 陈明佳
     * @date  2020/7/16 13:54
     */
    public int saveTuDingProject(List<TuDingProject> voucherList){
        int index = 0;
        for(TuDingProject voucher : voucherList){
            if(saveTuDingProject(voucher)){
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
    public int  deleteTuDingProject(TuDingProject voucher){
        if(voucher==null){
            return 0;
        }

        return LitePal.deleteAll(TuDingProject.class, "projectId= ?", String.valueOf(voucher.getProjectId()));
    }
    /**
     * @Description: 查询所有的
     * @return List<TuDingProject>
     * @author 陈明佳
     * @date 2020/3/27 15:11
     */
    public List<TuDingProject> findTuDingProjectByProjectGroupId(String projectGroupId){
        return LitePal.where("projectGroupId = ?", projectGroupId).find(TuDingProject.class);
    }


    /**
     * @Description: 查询所有的
     * @return List<TuDingProject>
     * @author 陈明佳
     * @date 2020/3/27 15:11
     */
    public List<TuDingProject> likeTuDingProjectByProjectGroupId(String projectGroupId,String projectName){
        if(StringUtils.isEmpty(projectName)){
            return  LitePal.where("projectGroupId = ?", projectGroupId).find(TuDingProject.class);
        }

//        LitePal.where("projectGroupName like ?","%" + projectGroupName + "%").find(TuDingProjectGroup.class);
        return LitePal.where("projectGroupId = ? and projectName like ?", projectGroupId,"%" + projectName + "%").find(TuDingProject.class);
    }
    /**
     * @Description:
     * @return List<TuDingProject>
     * @author 陈明佳
     * @date 2020/3/27 15:11
     */
    public List<TuDingProject> findTuDingProjectByName(String projectGroupId,String projectName){
        return  LitePal.where("projectGroupId = ? and projectName = ?", projectGroupId, projectName).find(TuDingProject.class);
    }
    /**
     * @Description:
     * @return List<TuDingProject>
     * @author 陈明佳
     * @date 2020/3/27 15:11
     */
    public List<TuDingProject> findTuDingProjectByGroupId(String projectGroupId){
        return  LitePal.where("projectGroupId = ?", projectGroupId).find(TuDingProject.class);
    }


    /**
     * @Description: 查询所有的
     * @return List<TuDingProject>
     * @author 陈明佳
     * @date 2020/3/27 15:11
     */
    public List<TuDingProject> findAllTuDingProject(){
        return LitePal.findAll(TuDingProject.class);
    }


    /**
     * @Description: 删除所有的
     * @author 陈明佳
     * @date 2020/3/27 15:11
     */
    public int deleteAllTuDingProject(){
        return LitePal.deleteAll(TuDingProject.class);
    }
}
