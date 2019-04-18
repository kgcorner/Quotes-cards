package com.kgcorner.vachan.viewers.quotes;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.kgcorner.vachan.viewers.quotes.fragments.quotes.QuotesListFragment;
import com.kgcorner.vachan.viewers.quotes.fragments.topics.TopicsFragment;

/**
 * Adapter for Populating tabs on Home Screen
 * Created by kumar on 4/2/18.
 */

public class QuotesPagerAdapter extends FragmentStatePagerAdapter {

    private final int TABS_COUNT;
    private QuotesListFragment quotesListFragment;
    private TopicsFragment topicsFragment;
    public QuotesPagerAdapter(FragmentManager fm, int tabCounts) {
        super(fm);
        this.TABS_COUNT = tabCounts;
        quotesListFragment = QuotesListFragment.getInstance();
        topicsFragment = new TopicsFragment();
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0) {
            return this.quotesListFragment;
        } else {
            return topicsFragment;
        }
    }

    @Override
    public int getCount() {
        return this.TABS_COUNT;
    }
}
