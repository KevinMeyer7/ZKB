package com.example.loginapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.loginapp.R;
import com.example.loginapp.models.User;
import com.example.loginapp.viewmodels.RegistrationViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class RegistrationActivity extends AppCompatActivity {
    private RegistrationViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        EditText name = findViewById(R.id.name);
        EditText email = findViewById(R.id.email);
        DatePicker birthDate = findViewById(R.id.birth_date);
        Button registerButton = findViewById(R.id.register);

        viewModel = new ViewModelProvider(this).get(RegistrationViewModel.class);

        registerButton.setOnClickListener(view -> {
            String nameStr = name.getText().toString();
            String emailStr = email.getText().toString();
            Calendar calendar = Calendar.getInstance();
            calendar.set(birthDate.getYear(), birthDate.getMonth(), birthDate.getDayOfMonth());
            Date birthDateDate = calendar.getTime();

            if (!viewModel.isValidName(nameStr) || !viewModel.isValidEmail(emailStr) || !viewModel.isValidBirthDate(birthDateDate)) {
                Toast.makeText(RegistrationActivity.this, "Validation failed. Please check your inputs.", Toast.LENGTH_SHORT).show();
                return;
            }

            viewModel.register(nameStr, emailStr, birthDateDate);
        });

        viewModel.getUser().observe(this, user -> {
            if (user != null) {
                saveUserData(user);
                Intent intent = new Intent(RegistrationActivity.this, ConfirmationActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }

    private void saveUserData(User user) {
        SharedPreferences prefs = getSharedPreferences("UserDetails", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("name", user.getName());
        editor.putString("email", user.getEmail());

        // Save date as day.month.year.
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.GERMAN);
        String birthDateString = sdf.format(user.getBirthDate());

        editor.putString("birthDate", birthDateString);  // We only store primitive data types, so we convert date to long
        editor.apply();
    }
}