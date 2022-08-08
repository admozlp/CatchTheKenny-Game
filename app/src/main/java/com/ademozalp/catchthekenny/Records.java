package com.ademozalp.catchthekenny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class Records extends AppCompatActivity
{
    MainActivity ob = new MainActivity();
    TextView basictxt;
    TextView mediumtxt;
    TextView hardtxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        basictxt = findViewById(R.id.basictxt);
        mediumtxt = findViewById(R.id.mediumtxt);
        hardtxt = findViewById(R.id.hardtxt);

        int a = ob.a;
        int b = ob.b;
        int c = ob.c;

        basictxt.setText("Basic : " + a);
        mediumtxt.setText("Medium : " + b);
        hardtxt.setText("Hard : " + c);
    }
}