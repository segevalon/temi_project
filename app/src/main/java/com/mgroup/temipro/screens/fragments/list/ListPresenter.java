package com.mgroup.temipro.screens.fragments.list;

import com.mgroup.temipro.model.Model;

public class ListPresenter implements ListInterface.Presenter {
    private static ListPresenter presenter_instance = null;
    private ListInterface.View view;
    private Model model;

    public static ListPresenter getInstance() {
        if (presenter_instance == null)
            presenter_instance = new ListPresenter();

        return presenter_instance;
    }

    @Override
    public void onAttach(ListInterface.View view) {
        this.view = view;
        model = Model.getInstance();
    }

    @Override
    public void getData(boolean force) {
        if (model.getContactsArray() == null || model.getContactsArray().isEmpty() || force) {
            model.getData();
        }else{
            view.hideProgress();
        }
    }

    @Override
    public void setDataToList() {
        view.setDataToList(model.getContactsArray());
    }



    @Override
    public void hideProgress() {
        view.hideProgress();
    }


    @Override
    public void onDetach() {
        view = null;
    }
}
