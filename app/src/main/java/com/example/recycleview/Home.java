package com.example.recycleview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {

    ImageButton lawangsewu, wakatobi, tanahlot, bromo, sentani, pulauk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        lawangsewu  = (ImageButton) findViewById(R.id.semarang);
        wakatobi    = (ImageButton) findViewById(R.id.wakatobipic);
        tanahlot    = (ImageButton) findViewById(R.id.tanahlotpic);
        bromo       = (ImageButton) findViewById(R.id.bromopic);
        sentani     = (ImageButton) findViewById(R.id.sentanipic);
        pulauk      = (ImageButton) findViewById(R.id.pulaukpic);

        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView
                .OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        return true;
                    case R.id.admin:
                        startActivity(new Intent(getApplicationContext(),
                                MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.travelling:
                        startActivity(new Intent(getApplicationContext(),
                                Traveller.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        lawangsewu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this, smg.class));
            }
        });

        sentani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this, Papua.class));
            }
        });
        pulauk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this, Ntt.class));
            }
        });
        bromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this, Malang.class));
            }
        });
        tanahlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this, Bali.class));
            }
        });
    }
}