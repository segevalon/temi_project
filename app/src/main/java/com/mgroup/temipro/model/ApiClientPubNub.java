package com.mgroup.temipro.model;

import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.models.consumer.PNPublishResult;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.history.PNHistoryItemResult;
import com.pubnub.api.models.consumer.history.PNHistoryResult;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClientPubNub implements ApiInterfacePubNub {

    public static ApiClientPubNub apiClientPubNub;
    private final String TAG = "PubNub";
    private final String CHANNEL_NAME = "temi_channel";
    private HashMap<String, String> lastMessageHash = new HashMap<>();
    private PubNub pubnub;
    private Model model;


    public static ApiClientPubNub getApiClient() {
        if (apiClientPubNub == null) {
            apiClientPubNub = new ApiClientPubNub();

        }
        return apiClientPubNub;
    }


    @Override
    public void init() {
        Log.d(TAG, "init()");
        model = Model.getInstance();
        PNConfiguration pnConfiguration = new PNConfiguration();
        pnConfiguration.setSubscribeKey("sub-c-7f1abde2-1b24-11e9-b735-ca3a04aa6aa9");
        pnConfiguration.setPublishKey("pub-c-a05e4ed0-28f4-4fbc-afd2-da9d824a13b2");
        pubnub = new PubNub(pnConfiguration);
        pubnub.subscribe()
                .channels(Arrays.asList(CHANNEL_NAME)) // subscribe to channels
                .execute();

        pubnub.addListener(new SubscribeCallback() {
            @Override
            public void status(PubNub pubnub, PNStatus status) {
            }

            @Override
            public void message(PubNub pubnub, PNMessageResult message) {


                try {

                    JSONObject obj = new JSONObject(message.getMessage().toString());
                    Log.d(TAG, "phoneSrc = " + obj.get("phoneSrc"));
                    Log.d(TAG, "content = " + obj.get("content"));
                    Log.d(TAG, "time = " + obj.get("time"));
                    Log.d(TAG, "phoneDest = " + obj.get("phoneDest"));

                    if (obj.get("phoneDest").toString().equals(model.getMyPhone())) {
                        model.incomingMessage(obj.get("content").toString());
                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void presence(PubNub pubnub, PNPresenceEventResult presence) {

            }
        });
    }

    @Override
    public void sendMessage(String phoneSrc, String phoneDest, String messageContent, String time) {
        Log.d(TAG, "sendMessage()");
        JsonObject message = new JsonObject();
        message.addProperty("content", messageContent);
        message.addProperty("phoneSrc", phoneSrc);
        message.addProperty("time", time);
        message.addProperty("phoneDest", phoneDest);
        pubnub.publish()
                .message(message)
                .channel(CHANNEL_NAME)
                .async(new PNCallback<PNPublishResult>() {
                    @Override
                    public void onResponse(PNPublishResult result, PNStatus status) {
                        if (!status.isError()) {
                            Log.d(TAG, "pub timetoken: " + result.getTimetoken());

                        }
                        Log.d(TAG, "pub status code: " + status.getStatusCode());

                    }
                });
    }

    @Override
    public void getHistory() {
        Log.d(TAG, "getHistory()");


        pubnub.history()
                .channel(CHANNEL_NAME)
                .count(100)
                .async(new PNCallback<PNHistoryResult>() {
                    @Override
                    public void onResponse(PNHistoryResult result, PNStatus status) {

                        for (PNHistoryItemResult message : result.getMessages()) {
                            Log.d(TAG, message.getEntry().toString());
                            Log.d(TAG, String.valueOf(message.getTimetoken()));
                            try {

                                JSONObject obj = new JSONObject(message.getEntry().toString());

                                String phoneSrc = obj.get("phoneSrc").toString();
                                String content = obj.get("content").toString();
                                String time = obj.get("time").toString();
                                String phoneDest = obj.get("phoneDest").toString();

                                Log.d(TAG, "phoneSrc = " + phoneSrc);
                                Log.d(TAG, "content = " + content);
                                Log.d(TAG, "time = " + time);
                                Log.d(TAG, "phoneDest = " + phoneDest);
                                Log.d(TAG, "----------------------------------------------");

                                if (lastMessageHash.get(phoneSrc) != null) {
                                    if (Long.valueOf(lastMessageHash.get(phoneSrc)) < Long.valueOf(time)) {
                                        lastMessageHash.put(phoneSrc, time);
                                    }
                                } else {
                                    lastMessageHash.put(phoneSrc, time);
                                }


                            } catch (Exception e) {

                            }
                        }

                    }
                });
    }

    public String getLastMessageTime(String phone) {

        return lastMessageHash.get(phone);
    }

    @Override
    public void stop() {
        Log.d(TAG, "stop()");
        pubnub.unsubscribe()
                .channels(Arrays.asList(CHANNEL_NAME))
                .execute();
        pubnub.destroy();
    }
}

