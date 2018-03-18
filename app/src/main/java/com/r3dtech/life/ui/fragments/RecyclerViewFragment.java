package com.r3dtech.life.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;


import com.r3dtech.life.R;
import com.r3dtech.life.ui.Utils;

import java.util.List;

public abstract class RecyclerViewFragment<T> extends Fragment{
    private List<T> itemList;

    public static <T extends RecyclerViewFragment> RecyclerViewFragment newInstance(Class<T> implementation, List itemList) {
        try {
            RecyclerViewFragment res = implementation.newInstance();
            res.itemList = itemList;
            return res;
        } catch (java.lang.InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Couldn't create instance of class "+implementation.getName());
        }
    }

    protected int getLayoutResource() {
        return R.layout.header_empty;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        Utils.initRecyclerView(getContext(), recyclerView, getAdapter(itemList));
        ViewStub stub = view.findViewById(R.id.header);
        stub.setLayoutResource(getLayoutResource());
        initHeader(stub.inflate());
        return view;
    }

    protected abstract RecyclerView.Adapter getAdapter(List<T> itemList);

    public void initHeader(View header) {

    }

    protected List<T> getItemList() {
        return itemList;
    }
}
