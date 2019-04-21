package com.kgcorner.vachan.viewers.selectedtopics;

import com.kgcorner.sdk.models.Quote;
import com.kgcorner.vachan.viewers.BaseView;

import java.util.List;

public interface CardOnTopicView extends BaseView {
    void loadQuotes(List<Quote> quotes);
}
