package com.r3dtech.life.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;


import com.r3dtech.life.R;

import java.util.List;

public abstract class ExpandableListViewFragment<T> extends Fragment{
    private List<T> itemList;

    public static <T extends ExpandableListViewFragment> ExpandableListViewFragment newInstance(Class<T> implementation, List itemList) {
        try {
            ExpandableListViewFragment res = implementation.newInstance();
            res.itemList = itemList;
            return res;
        } catch (java.lang.InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Couldn't create instance of class "+implementation.getName());
        }
    }

    protected int getHeaderLayoutResource() {
        return R.layout.header_empty;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expandablelistview, container, false);
        ExpandableListView listView = view.findViewById(R.id.expandable_list_view);
        listView.setAdapter(getAdapter());
        ViewStub stub = view.findViewById(R.id.header);
        stub.setLayoutResource(getHeaderLayoutResource());
        initHeader(stub.inflate());
        return view;
    }

    protected abstract ExpandableListAdapter getAdapter();

    public void initHeader(View header) {

    }

    protected List<T> getItemList() {
        return itemList;
    }
}
