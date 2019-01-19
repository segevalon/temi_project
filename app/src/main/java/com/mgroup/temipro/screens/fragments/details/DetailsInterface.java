package com.mgroup.temipro.screens.fragments.details;

import android.widget.ImageView;
import android.widget.ProgressBar;

import com.mgroup.temipro.model.Contact;
import com.mgroup.temipro.screens.base.BasePresenter;

public interface DetailsInterface {

    interface View{

        void setDataToFields(Contact contact);

    }

    interface Presenter extends BasePresenter<View> {

        void setDataToFields(int position, ImageView image);

        void sendMessage(String phoneDest, String msg);
    }

}
