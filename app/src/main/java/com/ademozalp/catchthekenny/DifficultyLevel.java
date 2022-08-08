package com.ademozalp.catchthekenny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class DifficultyLevel extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    Intent intent;
    private Spinner spinner;
    private static final String[] paths = {"CHOOSE","BASİC", "MEDİUM", "HARD"};
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty_level);

        intent = new Intent(DifficultyLevel.this,MainActivity.class);
        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(DifficultyLevel.this, android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
    {
        switch (i)
        {
            case 1:
                intent.putExtra("level",1);
                startActivity(intent);
                break;
            case 2:
                intent.putExtra("level",2);
                startActivity(intent);
                break;
            case 3:
                intent.putExtra("level",3);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView)
    {

    }

    public void records(View view)
    {
        Intent intent = new Intent(DifficultyLevel.this,Records.class);
        startActivity(intent);
    }
}