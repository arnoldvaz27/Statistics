package com.example.differentandroidcodes;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

@SuppressLint("SetTextI18n")
public class MainActivity extends AppCompatActivity {


    TextView tvOpen, tvForceClosed, tvNormalClose;
    PieChart pieChart;
    String appOpened, finalAppOpened, appClosed, finalAppClosed, finalForceAppClosed, appOpen;
    int i, j, k;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(R.color.color3));
        setContentView(R.layout.activity_main);

        SharedPreferences getShared2 = getSharedPreferences("statistics", MODE_PRIVATE);
        appOpen = getShared2.getString("appOpened", null);

        if (appOpen != null) {
            i = Integer.parseInt(appOpen);
            i = i + 1;
            appOpen = Integer.toString(i);
            SharedPreferences shard = getSharedPreferences("statistics", MODE_PRIVATE);
            final SharedPreferences.Editor editor = shard.edit();
            editor.putString("appOpened", appOpen);
            editor.apply();
        } else {
            SharedPreferences shard = getSharedPreferences("statistics", MODE_PRIVATE);
            final SharedPreferences.Editor editor = shard.edit();
            editor.putString("appClosed", "0");
            editor.putString("appOpened", "1");
            editor.apply();
        }


        SharedPreferences getShared = getSharedPreferences("statistics", MODE_PRIVATE);
        appOpened = getShared.getString("appOpened", null);
        appClosed = getShared.getString("appClosed", null);

        if (appOpened != null || appClosed != null) {
            finalAppOpened = appOpened;
            finalAppClosed = appClosed;
            i = Integer.parseInt(finalAppOpened);
            j = Integer.parseInt(finalAppClosed);
        } else {
            assert false;
            i = Integer.parseInt(appOpened);
            j = 0;
        }
        k = (i - j) - 1;
        finalForceAppClosed = Integer.toString(k);


        tvOpen = findViewById(R.id.AppOpen);
        tvForceClosed = findViewById(R.id.ForceClosing);
        tvNormalClose = findViewById(R.id.NormalClosing);
        pieChart = findViewById(R.id.pieChart);

        setData();

    }


    private void setData() {

        // Set the percentage of language used
        tvOpen.setText(finalAppOpened);
        tvForceClosed.setText(finalForceAppClosed);
        tvNormalClose.setText(finalAppClosed);

        // Set the data and color to the pie chart
        pieChart.addPieSlice(
                new PieModel(
                        "App Opened",
                        Integer.parseInt(finalAppOpened),
                        Color.parseColor("#FFA726")));
        pieChart.addPieSlice(
                new PieModel(
                        "Python",
                        Integer.parseInt(finalForceAppClosed),
                        Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(
                new PieModel(
                        "C++",
                        Integer.parseInt(finalAppClosed),
                        Color.parseColor("#EF5350")));

        // To animate the pie chart
        pieChart.startAnimation();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        String appClosed;
        int i;
        SharedPreferences getShared = getSharedPreferences("statistics", MODE_PRIVATE);
        appClosed = getShared.getString("appClosed", null);

        if (appClosed != null) {
            i = Integer.parseInt(appClosed);
            i = i + 1;
            appClosed = Integer.toString(i);
            SharedPreferences shard = getSharedPreferences("statistics", MODE_PRIVATE);
            final SharedPreferences.Editor editor = shard.edit();
            editor.putString("appClosed", appClosed);
            editor.apply();
        } else {
            SharedPreferences shard = getSharedPreferences("statistics", MODE_PRIVATE);

            final SharedPreferences.Editor editor = shard.edit();
            editor.putString("appClosed", "1");
            editor.apply();
        }
        finishAffinity();
    }
}