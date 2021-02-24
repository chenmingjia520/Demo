package com.lanyoumobility.mobility_webview.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lanyoumobility.mobility_webview.R;
import com.lanyoumobility.mobility_webview.db.TuDingProject;
import com.lanyoumobility.mobility_webview.db.TuDingProjectGroup;

import java.util.List;

public class MyGridAdapter extends BaseAdapter {

    private final String TAG = "MyGridAdapter";
    private Context context;
    private List stringList;


    public MyGridAdapter(Context context, List stringList){
        this.context = context;
        this.stringList = stringList;
    }

    public void notifyDataSetChanged(List list){
        this.stringList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return stringList.size();
    }

    @Override
    public Object getItem(int i) {
        return stringList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
//            view =  View.inflate(context,R.layout.item_grid_view,null);
            view = LayoutInflater.from(context).inflate(R.layout.item_grid_view,viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.iv_item = view.findViewById(R.id.iv_item);
            viewHolder.tv_item = view.findViewById(R.id.tv_item);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Object object =  stringList.get(i);
        if(object instanceof TuDingProjectGroup){
            TuDingProjectGroup projectGroup = (TuDingProjectGroup) object;
            String projectGroupName =   projectGroup.getProjectGroupName();
            viewHolder.tv_item.setText(projectGroupName);
            Glide.with(context).load(R.drawable.iv_folder).override(100,100) .into(viewHolder.iv_item);
//            Glide.with(context).load(str).centerCrop().error(R.drawable.iv_gridview_item_error_bg).into(viewHolder.iv_item);
        }else if(object instanceof TuDingProject){
            TuDingProject project = (TuDingProject) object;
            viewHolder.tv_item.setText(project.getProjectName());
            Glide.with(context).load(project.getProjectPath()).override(100,100).error(R.drawable.iv_gridview_item_error_bg).into(viewHolder.iv_item);
        }


        return view;
    }


    public class ViewHolder{
        ImageView iv_item;
        TextView tv_item;
    }
}

