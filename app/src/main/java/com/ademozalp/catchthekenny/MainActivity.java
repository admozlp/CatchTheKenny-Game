package com.ademozalp.catchthekenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity
{
    TextView txtcounter;
    TextView txttimer;
    ImageView imageView;
    SharedPreferences sp;
    static int a,b,c;
    int n,x,y,w,h;
    int choose;

    public void intialize()
    {
        sp = this.getSharedPreferences("com.ademozalp.catchthekenny",Context.MODE_PRIVATE);
        int storedbasic = sp.getInt("basic",-1);
        storedbasic = sp.getInt("basic",-1);
        a = storedbasic;
        int storedmedium = sp.getInt("medium",-1);
        storedmedium = sp.getInt("medium",-1);
        b= storedmedium;
        int storedhard = sp.getInt("hard",-1);
        storedhard = sp.getInt("hard",-1);
        c = storedhard;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intialize();
        n = 0;
        txtcounter = findViewById(R.id.txt2);
        imageView = findViewById(R.id.imageView);
        txttimer = findViewById(R.id.txt1);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        h = displayMetrics.heightPixels;
        w = displayMetrics.widthPixels;
    }


    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();
        choose = intent.getIntExtra("level",0);
        int level = level(choose);
        game(w,level);
    }

    public void game(int w, int level)
    {
        imageView.setEnabled(true);
        new CountDownTimer(20000, level)
        {
            @Override
            public void onTick(long l)
            {
                txttimer.setText("Time : " + l/1000);

                x= ThreadLocalRandom.current().nextInt(15,w-260);
                y = ThreadLocalRandom.current().nextInt(250,1250);
                imageView.setX(x);
                imageView.setY(y);
            }
            @Override
            public void onFinish()
            {
                txttimer.setText("Time finished..");
                imageView.setEnabled(false);
                if(level == 900)
                {
                    int storedbasic = sp.getInt("basic",-1);
                    if(storedbasic < n)
                    {
                        sp.edit().putInt("basic",n).apply();
                        storedbasic = sp.getInt("basic",-1);
                        a = storedbasic;
                    }

                }
                else if(level == 500)
                {
                    int storedmedium = sp.getInt("medium",-1);
                    if(storedmedium < n)
                    {
                        sp.edit().putInt("medium",n).apply();
                        storedmedium = sp.getInt("medium",-1);
                        b= storedmedium;
                    }
                }
                else
                {
                    int storedhard = sp.getInt("hard",-1);
                    if(storedhard < n)
                    {
                        sp.edit().putInt("hard",n).apply();
                        storedhard = sp.getInt("hard",-1);
                        c = storedhard;
                    }
                }

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart");
                alert.setMessage("Are you want again play?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        txttimer.setText("Time : ");
                        txtcounter.setText("Score : ");
                        n = 0;
                        game(w,level);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        finish();
                    }
                });
                alert.show();
            }
        }.start();
    }

    public void count(View view)
    {
        n++;
        txtcounter.setText("Count : " + n);
    }

    public int level(int choose)
    {
        if(choose == 1)
            return 900;
        else if(choose == 2)
            return 500;
        else
            return 100;
    }
}