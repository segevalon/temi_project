package com.mgroup.temipro.screens.fragments.details;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.mgroup.temipro.model.Contact;
import com.mgroup.temipro.model.Model;

public class DetailsPresenter implements DetailsInterface.Presenter {
    private static DetailsPresenter presenter_instance = null;
    private DetailsInterface.View view;
    private Model model;

    public static DetailsPresenter getInstance() {
        if (presenter_instance == null)
            presenter_instance = new DetailsPresenter();

        return presenter_instance;
    }

    @Override
    public void onAttach(DetailsInterface.View view) {
        this.view = view;
        model = Model.getInstance();
    }

    @Override
    public void setDataToFields(int position, ImageView image) {
        Contact contact = model.getContactsArray().get(position);
        view.setDataToFields(contact);
        model.setImageFromURL(contact.getAvatarUrl(), image);
    }

    @Override
    public void sendMessage(String phoneDest, String msg) {
        Log.d("Details_Presenter", "sendMessage");
        model.sendMessage(phoneDest, msg);
    }


    @Override
    public void onDetach() {
        view = null;
    }
}
