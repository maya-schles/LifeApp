package com.r3dtech.life.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.r3dtech.life.R;
import com.r3dtech.life.logic.quests.missions.SideMission;
import com.r3dtech.life.logic.quests.quests.SideQuest;
import com.r3dtech.life.ui.custom_views.DoableMissionView;
import com.r3dtech.life.ui.custom_views.QuestView;

import java.util.List;

public class SideQuestViewAdapter extends BaseExpandableListAdapter{
    private List<SideQuest> questList;
    private Context context;

    public SideQuestViewAdapter(List<SideQuest> questList, Context context) {
        this.questList = questList;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return questList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return questList.get(groupPosition).getMissions().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return questList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return questList.get(groupPosition).getMissions().get(childPosition);
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
            convertView = new QuestView(context);
        }
        ((QuestView) convertView).setQuest(questList.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = new DoableMissionView(context);
        }
        ((DoableMissionView) convertView).setMission(questList.get(groupPosition).getMissions().get(childPosition));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
