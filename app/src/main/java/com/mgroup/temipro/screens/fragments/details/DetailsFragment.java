package com.mgroup.temipro.screens.fragments.details;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mgroup.temipro.R;
import com.mgroup.temipro.model.Contact;
import com.mgroup.temipro.screens.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailsFragment extends Fragment implements DetailsInterface.View, View.OnClickListener {
    private static String ARG_POSITION;
    private final String TAG = "DetailsFragment";
    DetailsPresenter presenter;
    View view;
    @BindView(R.id.contact_image)
    ImageView image;
    @BindView(R.id.contact_name_text)
    TextView name;
    @BindView(R.id.contact_phone_text)
    TextView phone;
    @BindView(R.id.contact_gender_text)
    TextView gender;
    @BindView(R.id.contact_email_text)
    TextView email;
    @BindView(R.id.contact_address_text)
    TextView address;
    @BindView(R.id.send_message_btn)
    Button button;
    @BindView(R.id.progress_details)
    ProgressBar progress;

    int position;
    Contact tempContact;


    public static DetailsFragment newInstance(int position) {
        DetailsFragment detailsFragment = new DetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_POSITION, position);
        detailsFragment.setArguments(bundle);
        return detailsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            position = getArguments().getInt(ARG_POSITION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(this, view);
        button.setOnClickListener(this);
        presenter = DetailsPresenter.getInstance();
        presenter.onAttach(this);
        presenter.setDataToFields(position, image);
        progress.setVisibility(View.INVISIBLE);

        return view;
    }


    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.contacts:
                ((MainActivity) getActivity()).switchListFragment();
                break;

            default:
                break;
        }

        return false;
    }


    @Override
    public void setDataToFields(Contact contact) {
        Log.d(TAG, "setDataToList");
        tempContact = contact;
        Log.d(TAG, "id = " + contact.getID());
        Log.d(TAG, "name = " + contact.getName());
        Log.d(TAG, "phone = " + contact.getPhone());
        Log.d(TAG, "gender = " + contact.getGender());
        Log.d(TAG, "email = " + contact.getEmail());
        Log.d(TAG, "address = " + contact.getAddress());
        Log.d(TAG, "avatar = " + contact.getAvatarUrl());


        name.setText(contact.getName());
        phone.setText(String.valueOf(contact.getPhone()));
        gender.setText(contact.getGender());
        email.setText(contact.getEmail());
        address.setText(contact.getAddress());
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
           default:
               Log.d(TAG, "sendMessage");
                presenter.sendMessage(tempContact.getPhone(), "hi " + tempContact.getName());

        }
    }
}