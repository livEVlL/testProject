package com.example.apple.testproject;


import android.app.Activity;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {
    private Service service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);






        Button btnlogin = (Button)findViewById(R.id.btn_login) ;
        btnlogin.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view){
                Intent it = new Intent(MainActivity.this,Three.class);
                startActivity(it);
                //changeLayout();
            }
        });

//        Button btnforgot = (Button)findViewById(R.id.btn_forgot) ;
//        btnforgot.setOnClickListener(new Button.OnClickListener(){
//            public void onClick(View v){
//                changeLayout2();
//            }
//        });

    }
    public void changeLayout(){
        Toast.makeText(this,"Login Success",Toast.LENGTH_SHORT).show();
        setContentView(R.layout.main);

    }

    public void changeLayout2(){
        //setContentView(R.layout.forgot);
    }


}