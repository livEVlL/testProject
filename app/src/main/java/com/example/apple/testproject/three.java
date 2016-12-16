package com.example.apple.testproject;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.os.Bundle;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;


//Activity繼承的是FragmentActivity，而不是舊版本的TabActivity
public class Three extends FragmentActivity
{
    private String info;
    private MyReceiver myreceiver;
    private Bundle bundle;
    private Intent it;
    private Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        timer = new Timer();
        timer.schedule(new MyTask(),0,1000);

        it = new Intent(this , MyService.class);

        myreceiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("test");
        this.registerReceiver(myreceiver,filter);


        //獲取TabHost控制元件
        FragmentTabHost mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        //設定Tab頁面的顯示區域，帶入Context、FragmentManager、Container ID
        mTabHost.setup(this, getSupportFragmentManager(), R.id.container);

        /**
         新增Tab結構說明 :
         首先帶入Tab分頁標籤的Tag資訊並可設定Tab標籤上顯示的文字與圖片，
         再來帶入Tab頁面要顯示連結的Fragment Class，最後可帶入Bundle資訊。
         **/

        //小黑人建立一個Tab，這個Tab的Tag設定為one，
        //並設定Tab上顯示的文字為第一堂課與icon圖片，Tab連結切換至
        //LessonOneFragment class，無夾帶Bundle資訊。

        mTabHost.addTab(mTabHost.newTabSpec("one")
                        .setIndicator("營業額概況"),money.class,null);

        //同上方Tab設定，不同處為帶入參數的差異
        mTabHost.addTab(mTabHost.newTabSpec("two")
                        .setIndicator("餐點熱門率"),food.class,null);

        //同上方Tab設定，不同處為帶入參數的差異
        mTabHost.addTab(mTabHost.newTabSpec("Three")
                .setIndicator("人數統計表"),people.class, null);
    }

    public class MyReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            bundle = intent.getExtras();

            info=bundle.getString("ppking");

        }
    }
    public class MyTask extends TimerTask{
        @Override
        public void run() {
            startService(it);
        }
    }

    /**
     方法權限設定為Public目的是可以讓Fragment取得內容 。
     */
    public String moneyText ()
    {
        //return "營業額概況\n- $$$$$$$$$$-";

        //以下if()是實驗
        if (info == null){
            startService(it);
            Log.v("ppking","1" +info);
        }

        return info;

    }
    //Tab - Lesson Two的文字內容
    public String foodText()
    {
        //return "餐點熱門率\n- ########## -";
//        Log.v("ppking","2" +info);
        return "2";
    }

    //Tab - Lesson Three的文字內容
    public String peopleText()
    {
//        return "人數統計表\n- %%%%%%%%%%% -";
//        Log.v("ppking","3" +info);
        return "3";
    }


}