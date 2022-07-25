package com.example.recycleview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    Button btnLogin,btnBack;
    FirebaseAuth mAuth;
    TextView email, pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.login);
        email    = findViewById(R.id.email);
        pw       = findViewById(R.id.pass);
        btnBack  = findViewById(R.id.back);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),
                        Home.class));
            }
        });

        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(view -> {
            loginUser();
        });

    }

    private void loginUser() {
        String Email = email.getText().toString();
        String Pw = pw.getText().toString();

        if (TextUtils.isEmpty(Email)) {
            email.setError("Email cannot be empty");
            email.requestFocus();
        } else if (TextUtils.isEmpty(Pw)) {
            pw.setError("Password cannot be empty");
            pw.requestFocus();
        } else {
            mAuth.signInWithEmailAndPassword(Email, Pw).addOnCompleteListener(
                    this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Admin logged in succesfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, Data.class));
                            } else {
                                Toast.makeText(MainActivity.this, "log in error: " + task.getException().
                                        getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}
