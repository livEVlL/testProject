package com.example.apple.testproject;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

import static android.R.attr.data;

public class MyService extends Service {
    private UIHandler uiHandler;
    private String info;

    public MyService() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
        uiHandler = new UIHandler();

        new Thread(){
            @Override
            public void run() {
                try {
                    MultipartUtility mu = new MultipartUtility("https://android-test-db-ppking2897.c9users.io/DataBase/AccountQuery02.php?accountId=ppking2897", "UTF-8");
                    List<String> ret = mu.finish();

                    parseJSON(ret.toString());

                    try {
                        Thread.sleep(1000);
                        Intent it = new Intent();
                        it.putExtra("ppking",info);
                        it.setAction("test");
                        sendBroadcast(it);

                    }catch (Exception e){
                        Log.v("ppking","Error :" + e);
                    }


                } catch (Exception e) {
                    Log.v("ppking", "DB Error:" + e.toString());
                }

            }
        }.start();




    }


    private void parseJSON(String json){
        LinkedList accountInfo = new LinkedList<>();
        try{

            JSONObject jsonObject = new JSONArray(json).getJSONObject(0);

            String stringNo1 = jsonObject.getString("Account");
            accountInfo.add(stringNo1);
            String stringNo2 = jsonObject.getString("SeatIdNumber");
            accountInfo.add(stringNo2);
            String stringNo3 = jsonObject.getString("Checkout");
            accountInfo.add(stringNo3);

//            Log.v("ppking" , " ::"+accountInfo.get(2));

            Message mesg = new Message();
            Bundle data = new Bundle();
            data.putCharSequence("data0",accountInfo.get(0).toString());
            data.putCharSequence("data1",accountInfo.get(1).toString());
            data.putCharSequence("data2",accountInfo.get(2).toString());
            mesg.setData(data);
            mesg.what=0;
            uiHandler.sendMessage(mesg);


        }catch (Exception e){
            Log.v("ppking", "Error : " + e.toString());
        }
    }

    private class UIHandler extends android.os.Handler{

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0 :
//                    textView.setText("Accound : "+msg.getData().getCharSequence("data0")+"\n");
//                    textView.append("SeatIdNumber : "+msg.getData().getCharSequence("data1")+"\n");
//                    textView.append("Checkout : $"+msg.getData().getCharSequence("data2")+"\n");

                    info = "Checkout : "+msg.getData().getCharSequence("data2")+"\n";
//                    Log.v("ppking","MyService:::" +info);
                    break;
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }
}
