package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edtWeight, edtFeet, edtInch;
    private TextView tvResult;
    private Button btnClick;
    private LinearLayoutCompat llMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        initElements();
        initListeners();
        initAction();
    }

    public void initElements() {
        edtWeight = findViewById(R.id.weight);
        edtFeet = findViewById(R.id.feet);
        edtInch = findViewById(R.id.inch);
        btnClick = findViewById(R.id.click);
        tvResult = findViewById(R.id.result);
        llMain = findViewById(R.id.llmain);
    }

    public void initListeners() {
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcBmi();
            }
        });
    }

    public void initAction() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "Check Your Body Health", Toast.LENGTH_SHORT).show();
            }
        },500);

    }

    public void calcBmi() {

        double wt = Integer.parseInt(edtWeight.getText().toString());
        double ft = Integer.parseInt(edtFeet.getText().toString());
        double inch = Integer.parseInt(edtInch.getText().toString());

        double totalInch = (ft * 12) + inch;
        double totalCm = totalInch * 2.53;
        double totalMetre = totalCm / 100;
        double calcBmi = wt / (totalMetre * totalMetre);

        if (calcBmi <= 18) {
            tvResult.setText(getResources().getString(R.string.underweight_txt));
            llMain.setBackgroundColor(getResources().getColor(R.color.light_pink));
            restart();
        } else if (calcBmi <= 25) {
            tvResult.setText(getResources().getString(R.string.normal_txt));
            llMain.setBackgroundColor(getResources().getColor(R.color.blue_valvet));
            restart();
        } else if (calcBmi <= 40) {
            tvResult.setText(getResources().getString(R.string.overweight_txt));
            llMain.setBackgroundColor(getResources().getColor(R.color.sky_blue));
            restart();
        } else {
            tvResult.setText(getResources().getString(R.string.obese_txt));
            llMain.setBackgroundColor(getResources().getColor(R.color.light_red));
            restart();
        }
    }

    public void restart() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                edtWeight.setText("");
                edtFeet.setText("");
                edtInch.setText("");
                tvResult.setText(getResources().getString(R.string.result_txt));
                llMain.setBackgroundColor(getResources().getColor(R.color.white));
            }
        }, 2000);
    }
}