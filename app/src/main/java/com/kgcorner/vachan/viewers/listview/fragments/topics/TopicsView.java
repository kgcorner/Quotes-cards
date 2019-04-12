package com.kgcorner.vachan.viewers.listview.fragments.topics;

import com.kgcorner.sdk.models.Topic;
import com.kgcorner.vachan.viewers.BaseView;

import java.util.List;

public interface TopicsView extends BaseView {
    void loadTopics(List<Topic> topics);
}
