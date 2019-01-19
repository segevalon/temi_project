package com.mgroup.temipro.screens;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import com.mgroup.temipro.R;
import com.mgroup.temipro.model.Model;

public class MainPresenter implements MainInterface.Presenter {

    private static MainPresenter presenter_instance = null;
    private MainInterface.View view;
    private Model model;

    public static MainPresenter getInstance() {
        if (presenter_instance == null)
            presenter_instance = new MainPresenter();

        return presenter_instance;
    }

    @Override
    public void onAttach(MainInterface.View view) {
        this.view = view;
        model = Model.getInstance();

    }


    @Override
    public void onDetach() {
        view = null;
    }

    @Override
    public void showMessage(String msg) {
        view.showMessage(msg);
    }

    @Override
    public void setMyPhone(String mPhoneNumber) {
        model.setMyPhone(mPhoneNumber);
    }
}
