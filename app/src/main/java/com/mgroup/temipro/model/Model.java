package com.mgroup.temipro.model;

import android.util.Log;
import android.widget.ImageView;


import com.mgroup.temipro.screens.MainPresenter;
import com.mgroup.temipro.screens.fragments.list.ListPresenter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Callback;
import retrofit2.Response;

public class Model {

    private final String TAG = "Model";
    private ApiInterfaceRetrofit apiInterfaceRetrofit;
    private ApiClientPubNub apiClientPubNub;
    private static Model model_instance = null;
    private ArrayList<Contact> contactsArray = new ArrayList<>();
    String myPhone = "segev";

    public static Model getInstance() {
        if (model_instance == null) {
            model_instance = new Model();
        }
        return model_instance;
    }

    public ArrayList<Contact> getContactsArray() {
        Log.d(TAG, "getContactsArray");
        return contactsArray;
    }

    public void setContactsArray(ArrayList<Contact> contactsArray) {
        Log.d(TAG, "setContactsArray");
        this.contactsArray = contactsArray;
    }


    public void getData() {
        Log.d(TAG, "getData");
        apiClientPubNub = ApiClientPubNub.getApiClient();
        apiClientPubNub.init();
        apiClientPubNub.getHistory();
        apiInterfaceRetrofit = ApiClientRetrofit.getApiClient().create(ApiInterfaceRetrofit.class);

        InfoRetrofit infoRetrofit = new InfoRetrofit("public", "mock_json_example_001");
        retrofit2.Call<ArrayList<Contact>> call = apiInterfaceRetrofit.getContacts(infoRetrofit);


        call.enqueue(new Callback() {
            @Override
            public void onResponse(retrofit2.Call call, Response response) {
                Log.d(TAG, "response = " + response.toString());
                Log.d(TAG, "response = " + response.body().toString());
                contactsArray = (ArrayList<Contact>) response.body();

                for (int i = 0; i < contactsArray.size(); i++) {
                    Contact contact = contactsArray.get(i);
                    contact.setName(contact.getFirstName() + " " + contact.getLastName());
                    Log.d(TAG, "getName = " + contact.getFirstName() + " " + contact.getLastName());
                }

                ListPresenter.getInstance().setDataToList();
                ListPresenter.getInstance().hideProgress();
            }

            @Override
            public void onFailure(retrofit2.Call call, Throwable t) {
                Log.d(TAG, t.toString());
            }
        });
    }

    public void setImageFromURL(String url, ImageView image) {
        Picasso.get().load(url).into(image);
    }


    public void incomingMessage(String msg) {
        MainPresenter.getInstance().showMessage(msg);
    }

    public void sendMessage(String phoneDest, String msg) {
        Log.d("Details_Presenter", "sendMessage");
        apiClientPubNub.sendMessage(myPhone, phoneDest, msg, getTime());
    }

    public String getTime() {
        long tsLong = System.currentTimeMillis() / 1000;
        Log.d(TAG, "getTime() = " + Long.toString(tsLong));
        return Long.toString(tsLong);
    }

    public String getMyPhone() {

        return myPhone;
    }

    public void setMyPhone(String mPhoneNumber) {
        myPhone = mPhoneNumber;
    }
}


