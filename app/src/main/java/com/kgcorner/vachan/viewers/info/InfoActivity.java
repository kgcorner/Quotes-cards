package com.kgcorner.vachan.viewers.info;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

import com.kgcorner.vachan.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class InfoActivity extends AppCompatActivity {

    @BindView(R.id.webView)
    WebView webView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private Unbinder unbinder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_activities);
        unbinder = ButterKnife.bind(this);
        Bundle extras = getIntent().getExtras();
        if(extras == null || extras.getString("page").equals("credits")) {
            webView.loadUrl("file:///android_asset/credit.html");
            setTitle("Credits");
        }
        else {
            webView.loadUrl("file:///android_asset/terms.html");
            setTitle("Terms of use");
        }
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
