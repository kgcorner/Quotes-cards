package com.kgcorner.vachan.viewers;

public interface BaseView {
    void showError(Throwable e);
    void showLoader();
    void hideLoader();
}
