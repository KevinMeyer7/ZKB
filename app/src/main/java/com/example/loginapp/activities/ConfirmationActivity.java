package com.example.loginapp.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginapp.R;
import com.example.loginapp.models.User;

public class ConfirmationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        // Empfangen des User-Objekts von der vorherigen Aktivität
        User user = (User) getIntent().getParcelableExtra("user");

        TextView userName = findViewById(R.id.name);
        TextView userEmail = findViewById(R.id.email);
        TextView userBirthDate = findViewById(R.id.birthDate);

        // Setzen der Benutzerdaten in die TextViews
        userName.setText(user.getName());
        userEmail.setText(user.getEmail());
        userBirthDate.setText(user.getBirthDate().toString());
    }
}