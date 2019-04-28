package com.kgcorner.vachan.viewers.quotes.images;

import android.content.Intent;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.kgcorner.sdk.models.Image;
import com.kgcorner.vachan.BaseApplication;
import com.kgcorner.vachan.R;
import com.kgcorner.vachan.viewers.share.ShareActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ImageChooser extends AppCompatActivity implements ImageView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static final String TOPIC = "topics";

    @BindView(R.id.imagesContainer)
    RecyclerView imagesContainer;

    @BindView(R.id.imgLoader)
    android.widget.ImageView imgLoader;

    private Unbinder unbinder;

    @Inject
    ImagePresenter presenter;

    private static  final String DEFAULT_TOPIC = "LIFE";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_chooser);
        ((BaseApplication)getApplication()).getAppComponent().inject(this);
        unbinder = ButterKnife.bind(this);
        presenter.setView(this);
        Bundle topicInfo = getIntent().getExtras();
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if(topicInfo != null) {
            String topics = topicInfo.getString(TOPIC);
            showLoader();
            if(topics != null && topics.trim().length() >0) {
                presenter.fetchImages(topics);
            } else {
                Toast.makeText(this, "No topic choosen, fetching default list",
                        Toast.LENGTH_SHORT).show();
                presenter.fetchImages(DEFAULT_TOPIC);
            }
        }
    }

    @Override
    public void loadImage(List<Image> images) {
        ImageAdapter adapter = new ImageAdapter(images, this);
        imagesContainer.setLayoutManager(new GridLayoutManager(this, 1));
        imagesContainer.setAdapter(adapter);
        hideLoader();
    }

    @Override
    public void showError(Throwable e) {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.parent),
                e.getLocalizedMessage(), BaseTransientBottomBar.LENGTH_SHORT);
        snackbar.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onImageChoose(Image image) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ShareActivity.CHOSEN_IMAGE, image);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
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
