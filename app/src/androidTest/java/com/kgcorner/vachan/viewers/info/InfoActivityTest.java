package com.kgcorner.vachan.viewers.info;

import android.content.Intent;
import android.os.Bundle;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;

import com.kgcorner.vachan.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

public class InfoActivityTest {

    @Rule
    public ActivityTestRule<InfoActivity> infoActivitiesActivityTestRule =
            new ActivityTestRule<>(InfoActivity.class, true, false);

    private InfoActivity activity;

    @Test
    public void onLaunchForTerms() {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("page", "terms");
        intent.putExtras(bundle);
        activity = infoActivitiesActivityTestRule.launchActivity(intent);
        WebView webView = activity.findViewById(R.id.webView);
        Toolbar toolbar = activity.findViewById(R.id.toolbar);
        assertNotNull(webView);
        assertNotNull(toolbar);
        assertEquals("toolbar title is not matching",
                "Terms of use", toolbar.getTitle());
    }

    @Test
    public void onLaunchForCredit() {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("page", "credits");
        intent.putExtras(bundle);
        activity = infoActivitiesActivityTestRule.launchActivity(intent);
        WebView webView = activity.findViewById(R.id.webView);
        Toolbar toolbar = activity.findViewById(R.id.toolbar);
        assertNotNull(webView);
        assertNotNull(toolbar);
        assertEquals("toolbar title is not matching", "Credits", toolbar.getTitle());
        webView.post(new Runnable() {
            @Override
            public void run() {
                assertEquals("url is not matching",
                        "file:///android_asset/credit.html", webView.getUrl());
            }
        });
    }

    @Test
    public void onLaunchWithoutIntent() {
        activity = infoActivitiesActivityTestRule.launchActivity(new Intent());
        WebView webView = activity.findViewById(R.id.webView);
        Toolbar toolbar = activity.findViewById(R.id.toolbar);
        assertNotNull(webView);
        assertNotNull(toolbar);
        assertEquals("toolbar title is not matching", "Credits", toolbar.getTitle());
        webView.post(new Runnable() {
            @Override
            public void run() {
                assertEquals("url is not matching",
                        "file:///android_asset/credit.html", webView.getUrl());
            }
        });
    }
}