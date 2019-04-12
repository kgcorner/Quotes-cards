package com.kgcorner.vachan.viewers.listview.fragments.topics;

import com.kgcorner.sdk.models.Topic;

import java.util.List;

import io.reactivex.Observable;

public interface TopicInteractor {

    Observable<List<Topic>> fetchTopics();
}
