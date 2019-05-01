package com.kgcorner.vachan.viewers.quotes;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kgcorner.vachan.R;
import com.kgcorner.vachan.viewers.favouritecard.FavouriteCardsActivity;
import com.kgcorner.vachan.viewers.info.InfoActivity;
import com.kgcorner.vachan.viewers.quotes.fragments.quotes.QuotesListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class QuotesActivity extends AppCompatActivity implements
        QuotesListFragment.OnFragmentInteractionListener, Drawer.FragmentDrawerListener {

    @BindView(R.id.tabTitleHolder)
    TabLayout tabTitleHolder;

    @BindView(R.id.tabHolder)
    ViewPager tabHolder;

    private Unbinder unbinder;

    private Drawer drawerFragment;

    private QuotesListFragment quotesListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        unbinder = ButterKnife.bind(this);
        populateBottomBar();
        drawerFragment = (Drawer) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_navigation_drawer);

        quotesListFragment = QuotesListFragment.getInstance();

        drawerFragment.setUp(R.id.fragment_navigation_drawer,
                (DrawerLayout) findViewById(R.id.quote_card_home));

        drawerFragment.setDrawerListener(this);
    }

    private void populateBottomBar() {
        setTabs();
        setAdapter();
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

    public void toggleDrawer() {
        if (!this.drawerFragment.getmDrawerLayout().isDrawerOpen(Gravity.START)) {
            this.drawerFragment.getmDrawerLayout().openDrawer(Gravity.START);
        } else {
            this.drawerFragment.getmDrawerLayout().closeDrawer(Gravity.START);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        switch (position) {
            case 0:
                showFavorite();
                break;
            case 1:
                launchPlayStore();
                break;
            case 2:
                showCredits();
                break;
            case 3:
                showTerms();
                break;
        }
    }

    private void showTerms() {
        Bundle bundle = new Bundle();
        bundle.putString("page", "terms");
        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void showCredits() {
        Bundle bundle = new Bundle();
        bundle.putString("page", "credits");
        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void launchPlayStore() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String packageName= getString(R.string.packagename);
        intent.setData(Uri.parse("market://details?id="+packageName));
        startActivity(intent);
    }

    private void showFavorite() {
        Intent intent = new Intent(this, FavouriteCardsActivity.class);
        startActivity(intent);
    }
}