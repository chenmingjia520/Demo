package com.lanyoumobility.mobility_webview.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.webkit.WebChromeClient;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanyoumobility.mobility_webview.R;
import com.lanyoumobility.mobility_webview.base.BaseActivity;
import com.lanyoumobility.mobility_webview.db.TuDingProject;
import com.lanyoumobility.mobility_webview.db.TuDingProjectGroup;
import com.lanyoumobility.mobility_webview.db.dao.TuDingProjectDao;
import com.lanyoumobility.mobility_webview.db.dao.TuDingProjectGroupDao;
import com.lanyoumobility.mobility_webview.presenter.base.BaseOldPresenter;
import com.lanyoumobility.mobility_webview.ui.adapter.MyGridAdapter;
import com.lanyoumobility.mobility_webview.utils.Config;
import com.lanyoumobility.mobility_webview.utils.FilesUtils;
import com.lanyoumobility.mobility_webview.utils.L;
import com.lanyoumobility.mobility_webview.utils.StringUtils;
import com.lanyoumobility.mobility_webview.utils.ThreadManager;
import com.lanyoumobility.mobility_webview.widget.NewProjectWindow;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ImportProjectActivity extends BaseActivity {

    private final String TAG = "ImportProjectActivity";
    @BindView(R.id.gv_tack_images)
    GridView gv_tack_images;

    @BindView(R.id.tv_import)
    TextView tv_import;
    @BindView(R.id.iv_back)
    ImageView iv_back;

    @BindView(R.id.et_likename)
    EditText et_likename;

    @Override
    protected BaseOldPresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_importproject;
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0x01:
                    myGridAdapter.notifyDataSetChanged(projectGroupList);
                    break;
                case 0x96://toast
                    L.i(TAG,"：：：：：：：：：：：：：：：：：：：：：：：0x96");
                    toShowProjectGroup(showType,projectGroupId);
                    break;
                case 0x97://toast
                    String message = (String) msg.obj;
                    if(!StringUtils.isEmpty(message)){
                        showToast(message);
                    }
                    break;
            }
        }
    };

    private MyGridAdapter myGridAdapter;
    private List projectGroupList = new ArrayList<>();
    @Override
    protected void initView() {
        super.initView();
        myGridAdapter = new MyGridAdapter(ImportProjectActivity.this,projectGroupList);
        gv_tack_images.setAdapter(myGridAdapter);
        gv_tack_images.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object object  =  projectGroupList.get(i);
                if(object instanceof  TuDingProjectGroup){
                    TuDingProjectGroup projectGroup = (TuDingProjectGroup) object;
                    toShowProjectGroup(1,projectGroup.getProjectGroupId());
                }else if(object instanceof  TuDingProject){
                    TuDingProject project = (TuDingProject) object;
                    toActivity(MainActivity.class);
                }
            }
        });
        toShowProjectGroup(0,"");
        et_likename.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(handler.hasMessages(0x96)){
                    handler.removeMessages(0x96);
                }
                handler.sendEmptyMessageDelayed(0x96,1000);
            }
        });
    }
    @OnClick({R.id.iv_back,R.id.tv_import})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                toShowProjectGroup(0,"");
                break;
            case R.id.tv_import:
                if(showType==0){
                    toImportProject("");
                }else{
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                    startActivityForResult(intent, RESULT_CANCELED);
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RESULT_CANCELED:
                    Uri uri = data.getData();
                    String fileName = "avator_"+System.currentTimeMillis()+".jpg";
                    String portraitPath = FilesUtils.saveFile(ImportProjectActivity.this,Config.PATHS_IMG+File.separator+projectGroupId, fileName,uri);
                    if(!StringUtils.isEmpty(portraitPath)){
                        toImportProject(portraitPath);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private int showType = 0;
    private String projectGroupId = "";
    /**
     * 0项目组 ，1项目
     * @param showType
     */
    private void toShowProjectGroup(int showType,String projectGroupId){
        this.projectGroupId = projectGroupId;
        this.showType = showType;

        if(showType==0){
            iv_back.setVisibility(View.INVISIBLE);
            tv_import.setText("新建项目");
        }else{
            iv_back.setVisibility(View.VISIBLE);
            tv_import.setText("点击导入");
        }
        ThreadManager.getPool().execute(new Runnable() {
            @Override
            public void run() {
                projectGroupList.clear();
                String likeName = et_likename.getText().toString().trim();
                if(showType==0){
                    List<TuDingProjectGroup>  groupList = TuDingProjectGroupDao.getInstance().likeTuDingProjectGroupsByName(likeName);
                    if(groupList!=null){
                        projectGroupList.addAll(groupList);
                    }
                }else{


                    List<TuDingProject>  projectList = TuDingProjectDao.getInstance().likeTuDingProjectByProjectGroupId(projectGroupId,likeName);
                    if(projectList!=null){
                        projectGroupList.addAll(projectList);
                    }
                }
                handler.sendEmptyMessage(0x01);
            }
        });
    }

    private NewProjectWindow newProjectWindow;
    private void toImportProject(String  portraitPath) {
        newProjectWindow = new NewProjectWindow(this);
        newProjectWindow.initView(portraitPath);
        newProjectWindow.setOnCloseClickListener(new NewProjectWindow.OnCloseClickListener() {
            @Override
            public void onclick(String project) {
                ThreadManager.getPool().execute(new Runnable() {
                    @Override
                    public void run() {
                        if(StringUtils.isEmpty(portraitPath)){
                            boolean success =  TuDingProjectGroupDao.getInstance().saveTuDingProjectGroup(project);
                            if(success){
                                List<TuDingProjectGroup>  groupList = TuDingProjectGroupDao.getInstance().findAllTuDingProjectGroups();
                                if(groupList!=null){
                                    projectGroupList.clear();
                                    projectGroupList.addAll(groupList);
                                    handler.sendEmptyMessage(0x01);
                                }
                            }else{
                                Message message = Message.obtain();
                                message.what = 0x97;
                                message.obj = "请确认项目名称是否已存在";
                                handler.sendMessage(message);
                            }
                        }else{
                            String newFilePath = FilesUtils.renameToFile(new File(portraitPath),project);
                            if(StringUtils.isEmpty(newFilePath)){
                                Message message = Message.obtain();
                                message.what = 0x97;
                                message.obj = "文件转化失败";
                                handler.sendMessage(message);
                                return;
                            }
                            L.i(TAG,"projectGroupId：：：：：："+projectGroupId);
                            L.i(TAG,"newFilePath：：：：：："+newFilePath);
                            L.i(TAG,"project：：：：：："+project);
                            boolean success =  TuDingProjectDao.getInstance().saveTuDingProject(projectGroupId,project,newFilePath);
                            L.i(TAG,"添加项目：：：：：："+success);
                            if(success){
                                List<TuDingProject>  projectList = TuDingProjectDao.getInstance().findTuDingProjectByGroupId(projectGroupId);
                                if(projectList!=null){
                                    L.i(TAG,"projectList：：：：：："+projectList.size());
                                    projectGroupList.clear();
                                    projectGroupList.addAll(projectList);
                                    handler.sendEmptyMessage(0x01);
                                }
                            }else{
                                Message message = Message.obtain();
                                message.what = 0x97;
                                message.obj = "请确认项目名称是否已存在";
                                handler.sendMessage(message);
                            }
                        }
                    }
                });
            }
        });
        newProjectWindow.show();
    }

    @Override
    protected void initData() {
        super.initData();
    }
}
