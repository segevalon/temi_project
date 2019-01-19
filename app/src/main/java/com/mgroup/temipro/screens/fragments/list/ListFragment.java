package com.mgroup.temipro.screens.fragments.list;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.mgroup.temipro.R;
import com.mgroup.temipro.model.Contact;
import com.mgroup.temipro.screens.MainActivity;
import com.mgroup.temipro.screens.fragments.list.adapters.MyAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;




public class ListFragment extends Fragment implements ListInterface.View, MyAdapter.ItemClickListener {
    private final String TAG = "ListFragment";
    private MyAdapter adapter;
    private ListPresenter presenter;
    View view;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.progress_list)
    ProgressBar progress;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;


    public ListFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);

        presenter = ListPresenter.getInstance();
        presenter.onAttach(this);
        presenter.getData(false);
        presenter.setDataToList();

        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        Log.i(TAG, "onRefresh SwipeRefreshLayout");
                        progress.setVisibility(View.VISIBLE);
                        presenter.getData(true);
                        presenter.setDataToList();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }
        );

        return view;
    }

    @Override
    public void onItemClick(View view, int position) {
        Log.d("ssss", String.valueOf(position));
        ((MainActivity)getActivity()).switchDetailsFragment(position);
    }

    @Override
    public void setDataToList(ArrayList<Contact> contacts) {
        Log.d(TAG,"setDataToList");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MyAdapter(getContext(), contacts);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void hideProgress() {
        progress.setVisibility(View.INVISIBLE);
    }


}
