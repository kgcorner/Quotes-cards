package com.kgcorner.vachan.viewers.selectedtopics;

public interface CardOnTopicPresenter {
    void fetchQuotes(String topic);
    void setView(CardOnTopicView view);
}
