package com.kgcorner.vachan.viewers.quotes.fragments.topics;

import android.os.Bundle;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kgcorner.sdk.models.Topic;
import com.kgcorner.vachan.BaseApplication;
import com.kgcorner.vachan.R;
import com.kgcorner.vachan.viewers.quotes.viewholder.category.TopicsAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TopicsFragment extends Fragment implements  TopicsView{

    @BindView(R.id.categoryContainer)
    RecyclerView categoryContainer;
    private static final String TAG = "TopicsFragment";

    @BindView(R.id.imgLoader)
    ImageView imgLoader;


    private Unbinder unbinder;

    private TopicsAdapter adapter;
    private List<Topic> topics = new ArrayList<>();
    private View view = null;
    @Inject
    TopicsPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BaseApplication) getActivity().getApplication())
                .getAppComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.topics_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        this.adapter = new TopicsAdapter(topics);
        categoryContainer.setLayoutManager(new GridLayoutManager(getContext(), 2));
        categoryContainer.setAdapter(adapter);
        presenter.setView(this);
        showLoader();
        presenter.fetchTopics();
        return view;
    }

    @Override
    public void loadTopics(List<Topic> topics) {
        this.topics.addAll(topics);
        this.adapter.notifyDataSetChanged();
        hideLoader();
    }

    @Override
    public void showError(Throwable e) {
        Snackbar snackbar = Snackbar.make(view,
                e.getLocalizedMessage(), BaseTransientBottomBar.LENGTH_SHORT);
        snackbar.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.unbinder.unbind();
    }

    @Override
    public void showLoader() {
        imgLoader.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoader() {
        imgLoader.setVisibility(View.GONE);
    }
}
