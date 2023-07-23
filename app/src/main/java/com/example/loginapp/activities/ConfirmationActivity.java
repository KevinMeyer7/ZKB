package com.example.loginapp.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginapp.R;
import com.example.loginapp.models.User;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class ConfirmationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        setTitle("Bestätigung");

        // Empfangen des User-Objekts von der vorherigen Aktivität
        User user = (User) getIntent().getParcelableExtra("user");

        TextView userName = findViewById(R.id.user_name);
        TextView userEmail = findViewById(R.id.user_email);
        TextView userBirthDate = findViewById(R.id.user_birth_date);

        // Setzen der Benutzerdaten in die TextViews
        userName.setText(user.getName());
        userEmail.setText(user.getEmail());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String formattedDate = sdf.format(user.getBirthDate());
        userBirthDate.setText(formattedDate);
    }
}