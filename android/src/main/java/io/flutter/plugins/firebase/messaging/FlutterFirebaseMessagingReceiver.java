// Copyright 2020 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package io.flutter.plugins.firebase.messaging;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import model.AmbData;
import model.AmbResult;
import model.BookingDetailsModel;
import android.widget.Toast;

public class FlutterFirebaseMessagingReceiver extends BroadcastReceiver {
  private static final String TAG = "FLTFireMsgReceiver";
  static HashMap<String, RemoteMessage> notifications = new HashMap<>();

  @Override
  public void onReceive(Context context, Intent intent) {
    Log.d(TAG, "broadcast received for message");
    if (ContextHolder.getApplicationContext() == null) {
      ContextHolder.setApplicationContext(context.getApplicationContext());
    }

    if (intent.getExtras() == null) {
      Log.d(
          TAG,
          "broadcast received but intent contained no extras to process RemoteMessage. Operation cancelled.");
      return;
    }

    RemoteMessage remoteMessage = new RemoteMessage(intent.getExtras());

    // Store the RemoteMessage if the message contains a notification payload.
    if (remoteMessage.getNotification() != null) {
      notifications.put(remoteMessage.getMessageId(), remoteMessage);
      FlutterFirebaseMessagingStore.getInstance().storeFirebaseMessage(remoteMessage);
    }

    //  |-> ---------------------
    //      App in Foreground
    //   ------------------------
    Log.d("TAG",remoteMessage.getData().toString());
    if(remoteMessage.getData().get("type").equals("poster")){
      showAmbulanceReminder(context,remoteMessage.getData().get("recordId"),"nocookie");
    }else{
      Toast.makeText(context, "Type not equal to poster", Toast.LENGTH_SHORT).show();
    }

    if (FlutterFirebaseMessagingUtils.isApplicationForeground(context)) {
      Intent onMessageIntent = new Intent(FlutterFirebaseMessagingUtils.ACTION_REMOTE_MESSAGE);
      onMessageIntent.putExtra(FlutterFirebaseMessagingUtils.EXTRA_REMOTE_MESSAGE, remoteMessage);
      LocalBroadcastManager.getInstance(context).sendBroadcast(onMessageIntent);
      return;
    }

    //  |-> ---------------------
    //    App in Background/Quit
    //   ------------------------
    Intent onBackgroundMessageIntent =
        new Intent(context, FlutterFirebaseMessagingBackgroundService.class);
    onBackgroundMessageIntent.putExtra(
        FlutterFirebaseMessagingUtils.EXTRA_REMOTE_MESSAGE, remoteMessage);
    FlutterFirebaseMessagingBackgroundService.enqueueMessageProcessing(
        context, onBackgroundMessageIntent);
  }

  private static void showAmbulanceReminder(Context ctx, String recordId, String cookie) {


    SharedPreferences preferences = ctx.getSharedPreferences("CookiesPreference", Context.MODE_PRIVATE);
    String cookies = preferences.getString("savedCookies", "");

//    if (!cookie.equals("")) {
      final RequestQueue queue = Volley.newRequestQueue(ctx);
      final String url = "https://app.hospinity.com/admin/ambulance/order/details";


      try {
        JSONObject body = new JSONObject();
        body.put("recordId",recordId);
        JsonRequest<JSONObject> request = new JsonObjectRequest(Request.Method.POST,url, body, new Response.Listener<JSONObject>() {
          @Override
          public void onResponse(JSONObject response) {
            Log.d("response", response.toString());
            Toast.makeText(ctx, response.toString(), Toast.LENGTH_LONG).show();
            Toast.makeText(ctx, "Api Called Successfully", Toast.LENGTH_SHORT).show();
            System.out.println(response.toString());
            BookingDetailsModel res = new Gson().fromJson(response.toString(), BookingDetailsModel.class);
            AmbData data = res.getData();
            try {
              
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                        WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                        PixelFormat.TRANSPARENT);

                View inflater = LayoutInflater.from(ctx).inflate(R.layout.ambulance_reminder, null);
                WindowManager manager = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
                manager.addView(inflater, layoutParams);

                MediaPlayer player = MediaPlayer.create(ctx, R.raw.ringtone);

                player.start();


                TextView bookedBy = inflater.findViewById(R.id.textView2);
                TextView bookedOn = inflater.findViewById(R.id.textView5);
                TextView bookingTime = inflater.findViewById(R.id.textView6);
                TextView pickUp = inflater.findViewById(R.id.textView8);
                AmbResult result = data.getResult();


                long pickUpDateTime = result.getPickUpDateTime();

                bookedBy.setText(result.getUserName().toString());
                pickUp.setText(result.getFragmentedAddress().toString());
                Date date = new Date(pickUpDateTime*1000);
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS", Locale.getDefault());
                formatter.setTimeZone(TimeZone.getDefault());
                Log.d("PickUpAt",result.getPickUpDateTime()+"" );
                String format = formatter.format(date);
                bookedOn.setText(pickUpDateTime == 0 ? "N/A" : format.split(" ")[0]);
                bookingTime.setText(pickUpDateTime == 0 ? "N/A" : format.split(" ")[1]);
                Button acceptBooking = inflater.findViewById(R.id.button);
                Button cancelBooking = inflater.findViewById(R.id.button2);
                acceptBooking.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                    if (player.isPlaying()) {
                      player.stop();
                    }


                    manager.removeView(inflater);
                    cancelOrAcceptAmbulanceBooking(ctx, recordId, "enquiry", cookies);
                  }
                });

                cancelBooking.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                    if (player.isPlaying()) {
                      player.stop();
                    }

                    manager.removeView(inflater);

                    cancelOrAcceptAmbulanceBooking(ctx, recordId, "cancelled", cookies);


                  }

                });
              
            } catch (Exception e) {
              Log.e("Exception", e.getMessage());
               Toast.makeText(ctx, "Exception occured while showing Popup "+ e.getMessage(), Toast.LENGTH_LONG).show();
              
            }
          }

        }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            // Log.e("Api Call Error", error.getMessage());
          Toast.makeText(ctx, "Error Occured while calling api"+ error.getLocalizedMessage().toString(), Toast.LENGTH_SHORT).show();
          }
        }) {
          @Override
          public Map<String, String> getHeaders() throws AuthFailureError {
            Map<String, String> header = new HashMap<>();
            header.put("cookie", cookie);

            return header;
          }

          @Override
          public byte[] getBody() {
            try {
              String requestBody = body.toString();
              return requestBody.getBytes("utf-8") == null ? null : requestBody.getBytes("utf-8");
            } catch (UnsupportedEncodingException e) {
              return super.getBody();
            }
          }
        };
        queue.add(request);
      } catch (JSONException e) {
        throw new RuntimeException(e);
      }


//    } else {
//      Log.e("Cookie", "Cookie is not coming");
//    }

  }



  private  static  void cancelOrAcceptAmbulanceBooking(Context ctx, String recordId,String status, String cookies){
    String api = "https://app.hospinity.com/admin/ambulance/booking/update";
    final RequestQueue queue = Volley.newRequestQueue(ctx);
    try{
      JSONObject body = new JSONObject();
      body.put("status", status);
      body.put("recordId",recordId);

      JsonRequest<JSONObject> request = new JsonObjectRequest(Request.Method.PUT ,api, body,new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
          Log.d("AcceptReject",response.toString());
          Toast.makeText(ctx, "Thanks for your response, have a nice day", Toast.LENGTH_SHORT).show();
        }
      }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
          System.out.println(error.getMessage());
          // Log.e("Error",error.getMessage());
        }
      }){
        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
          Map<String, String> header = new HashMap<>();
          header.put("cookie", cookies);
          return header;
        }

        @Override
        public byte[] getBody() {
          try{
            String requestBody = body.toString();
            return requestBody.getBytes("utf-8") == null? null: requestBody.getBytes("utf-8");
          } catch (UnsupportedEncodingException e) {
            return super.getBody();
          }
        }
      };

      queue.add(request);

    } catch (JSONException e) {
      Log.e("Exception", e.getMessage());
    }

  }
}
