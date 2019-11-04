package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;

import com.example.myapplication.customviews.CVCopyHotItem;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private CVCopyHotItem copyHotItem1;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = getApplicationContext();

        copyHotItem1 = findViewById(R.id.cvHot1);

        copyHotItem1.setText("ERSEL");


    }


}
