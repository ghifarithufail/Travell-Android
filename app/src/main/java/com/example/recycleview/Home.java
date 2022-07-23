package com.example.recycleview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Home extends AppCompatActivity {

    ImageButton lawangsewu, wakatobi, tanahlot, bromo, sentani, pulauk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        lawangsewu = (ImageButton) findViewById(R.id.semarang);
        wakatobi = (ImageButton) findViewById(R.id.wakatobipic);
        tanahlot = (ImageButton) findViewById(R.id.tanahlotpic);
        bromo = (ImageButton) findViewById(R.id.bromopic);
        sentani = (ImageButton) findViewById(R.id.sentanipic);
        pulauk = (ImageButton) findViewById(R.id.pulaukpic);

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