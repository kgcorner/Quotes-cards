package com.kgcorner.vachan.viewers.quotes.fragments.topics;

import com.kgcorner.sdk.models.Topic;
import com.kgcorner.vachan.viewers.BaseView;

import java.util.List;

public interface TopicsView extends BaseView {
    void loadTopics(List<Topic> topics);
}
