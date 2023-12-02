package com.kurt.olsadayesekapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("sonuc","MainActivity başarılı");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}