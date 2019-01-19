package com.mgroup.temipro.screens.fragments.list;


import android.widget.ProgressBar;

import com.mgroup.temipro.model.Contact;
import com.mgroup.temipro.screens.base.BasePresenter;

import java.util.ArrayList;

public interface ListInterface {

    interface View{

        void setDataToList(ArrayList<Contact> contacts);

        void hideProgress();
    }

    interface Presenter extends BasePresenter<View> {

        void getData(boolean force);

        void setDataToList();

        void hideProgress();
    }

}
