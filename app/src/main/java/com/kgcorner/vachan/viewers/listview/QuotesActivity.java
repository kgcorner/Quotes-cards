package com.kgcorner.vachan.viewers.listview;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kgcorner.vachan.R;
import com.kgcorner.vachan.viewers.listview.fragments.quotes.QuotesListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class QuotesActivity extends AppCompatActivity implements QuotesListFragment.OnFragmentInteractionListener {

    @BindView(R.id.tabTitleHolder)
    TabLayout tabTitleHolder;

    @BindView(R.id.tabHolder)
    ViewPager tabHolder;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        unbinder = ButterKnife.bind(this);
        populateBottomBar();
    }

    private void populateBottomBar() {
        setTabs();
        setAdapter();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private void setTabs() {
        this.tabTitleHolder.addTab(this.tabTitleHolder.newTab().setCustomView(createQuotesTab()));
        this.tabTitleHolder.addTab(this.tabTitleHolder.newTab().setCustomView(createFavoriteTab()));
    }

    private View createFavoriteTab() {
        View view = getLayoutInflater().inflate(R.layout.tab_with_icon_and_text, null);
        ImageView icon = view.findViewById(R.id.icon);
        icon.setImageResource(R.drawable.star);
        TextView txtTabTitle = view.findViewById(R.id.txtTabTitle);
        txtTabTitle.setText(getApplicationContext().getString(R.string.favorite_tab_name));
        return view;
    }

    private View createQuotesTab() {
        View view = getLayoutInflater().inflate(R.layout.tab_with_icon_and_text, null);
        ImageView icon = view.findViewById(R.id.icon);
        icon.setImageResource(R.drawable.quotations);
        TextView txtTabTitle = view.findViewById(R.id.txtTabTitle);
        txtTabTitle.setText(getApplicationContext().getString(R.string.quotes_tab_name));
        return view;
    }

    private void setAdapter() {
        QuotesPagerAdapter adapter = new QuotesPagerAdapter(getSupportFragmentManager(),
                this.tabTitleHolder.getTabCount());
        this.tabHolder.setAdapter(adapter);
        this.tabHolder.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener
                (this.tabTitleHolder));
        this.tabTitleHolder.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabHolder.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
