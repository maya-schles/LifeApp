package com.r3dtech.life.ui.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.r3dtech.life.logic.quests.missions.Mission;
import com.r3dtech.life.ui.custom_views.DoableMissionView;

import java.util.List;

public class DoableMissionsAdapter extends BaseExpandableListAdapter{
    private List<Mission> missionList;
    private Context context;

    public DoableMissionsAdapter(List<Mission> missionList, Context context) {
        this.missionList = missionList;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return missionList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return missionList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = new DoableMissionView(context);
        }
        ((DoableMissionView) convertView).setMission(missionList.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
