package com.r3dtech.life.ui.adapters;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.r3dtech.life.R;
import com.r3dtech.life.logic.quests.quests.MainQuest;
import com.r3dtech.life.ui.custom_views.MainMissionView;
import com.r3dtech.life.ui.custom_views.PlainBossView;
import com.r3dtech.life.ui.custom_views.QuestView;

import java.util.List;

public class MainQuestsAdapter extends BaseExpandableListAdapter{
    private List<MainQuest> questList;
    private Context context;
    private static final int childPaddingLeft = 64;

    public MainQuestsAdapter(List<MainQuest> questList, Context context) {
        this.questList = questList;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return questList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return questList.get(groupPosition).getMissions().size()+1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return questList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        MainQuest quest = questList.get(groupPosition);
        if (childPosition < quest.getMissions().size()) {
            return questList.get(groupPosition).getMissions().get(childPosition);
        }
        return quest.getBoss();
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
        if (isLastChild) {
            convertView = new PlainBossView(context);
            ((PlainBossView) convertView).setMission(questList.get(groupPosition).getBoss());
        }
        else {
            convertView = new MainMissionView(context);
            ((MainMissionView) convertView).setMission(questList.get(groupPosition).getMissions().get(childPosition));
        }
        convertView.setPadding(childPaddingLeft, 0, 0, 0);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
