package com.kgcorner.vachan.viewers.quotes;

import android.support.design.widget.TabLayout;
import android.support.test.rule.ActivityTestRule;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.kgcorner.sdk.VachanService;
import com.kgcorner.vachan.R;
import com.kgcorner.vachan.services.ServicesModule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;
import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static android.support.test.espresso.action.ViewActions.*;



public class QuotesActivityTest {

    @Rule
    public ActivityTestRule<QuotesActivity> quotesActivityActivityTestRule
            = new ActivityTestRule<>(QuotesActivity.class);
    private QuotesActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = quotesActivityActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch() {
        //Check tab title
        String firstTabText = activity.getResources().getString(R.string.quotes_tab_name);
        String secondTabText = activity.getResources().getString(R.string.favorite_tab_name);

        TabLayout tabTitleHolder = activity.findViewById(R.id.tabTitleHolder);

        int count = tabTitleHolder.getTabCount();
        assertEquals("tab count is not matching", 2, count);
        final TabLayout.Tab firstTab = tabTitleHolder.getTabAt(0);
        TextView firstTextView = firstTab.getCustomView().findViewById(R.id.txtTabTitle);
        assertNotNull(firstTextView);
        assertEquals("text of first tab is not matching",
                firstTabText, firstTextView.getText().toString());

        final TabLayout.Tab secondTab = tabTitleHolder.getTabAt(1);
        TextView secondTextView = secondTab.getCustomView().findViewById(R.id.txtTabTitle);
        assertNotNull(firstTextView);
        assertEquals("text of first tab is not matching",
                secondTabText, secondTextView.getText().toString());

        //Check view pager
        ViewPager tabHolder = activity.findViewById(R.id.tabHolder);
        assertNotNull(tabHolder);
        PagerAdapter adapter = tabHolder.getAdapter();
        assertNotNull(adapter);
        assertEquals("view pager doesn't contain 2 items", 2, adapter.getCount());
    }

    @Test
    public void testOnQuoteClick() {
        TabLayout tabTitleHolder = activity.findViewById(R.id.tabTitleHolder);
        ViewPager tabHolder = activity.findViewById(R.id.tabHolder);
        String firstTabText = activity.getResources().getString(R.string.quotes_tab_name);
        final TabLayout.Tab firstTab = tabTitleHolder.getTabAt(0);
        onView(withText(firstTabText)).perform(click());

        int currentItem = tabHolder.getCurrentItem();
        assertEquals("on tapping first item, first item is not visible in view pager",
                0, currentItem);
    }

    @Test
    public void testOnTopicClick() {
        TabLayout tabTitleHolder = activity.findViewById(R.id.tabTitleHolder);
        ViewPager tabHolder = activity.findViewById(R.id.tabHolder);
        String secondTabText = activity.getResources().getString(R.string.favorite_tab_name);
        onView(withText(secondTabText)).perform(click());

        int currentItem = tabHolder.getCurrentItem();
        assertEquals("on tapping second item, second item is not visible in view pager",
                1, currentItem);
    }
}