package com.example.apple.testproject;

import android.content.res.Resources;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.os.Bundle;

//Activity繼承的是FragmentActivity，而不是舊版本的TabActivity
public class three extends FragmentActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

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
                        .setIndicator("營業額概況",getResources().getDrawable(R.drawable.money1))
                ,money.class,null);

        //同上方Tab設定，不同處為帶入參數的差異
        mTabHost.addTab(mTabHost.newTabSpec("two")
                        .setIndicator("餐點熱門率",getResources().getDrawable(R.drawable.food1))
                ,food.class,null);

        //同上方Tab設定，不同處為帶入參數的差異
        mTabHost.addTab(mTabHost.newTabSpec("three")
                .setIndicator("人數統計表",getResources().getDrawable(R.drawable.smallpeople,null)),people.class, null);





    }

    /**
     方法權限設定為Public目的是可以讓Fragment取得內容 。
     */

    //Tab - Lesson One的文字內容
    public String moneyText()
    {
        return "營業額概況\n- $$$$$$$$$$-";
    }

    //Tab - Lesson Two的文字內容
    public String foodText()
    {
        return "餐點熱門率\n- ########## -";
    }

    //Tab - Lesson Three的文字內容
    public String peopleText()
    {
        return "人數統計表\n- %%%%%%%%%%% -";
    }

}