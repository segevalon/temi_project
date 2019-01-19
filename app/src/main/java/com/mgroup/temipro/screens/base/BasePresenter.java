package com.mgroup.temipro.screens.base;

import android.view.View;

public interface BasePresenter<T> {
    public void onAttach(T view);
    public void onDetach();
}
