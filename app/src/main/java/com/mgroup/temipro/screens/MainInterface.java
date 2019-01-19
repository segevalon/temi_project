package com.mgroup.temipro.screens;

import com.mgroup.temipro.screens.base.BasePresenter;

public interface MainInterface {

    interface View {

        void switchDetailsFragment(int position);

        void switchListFragment();

        void showMessage(String msg);
    }

    interface Presenter extends BasePresenter<View> {
        void showMessage( String msg);

        void setMyPhone(String mPhoneNumber);
    }

}
