package com.example.loginapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.loginapp.R;
import com.example.loginapp.viewmodels.RegistrationViewModel;

import java.util.Calendar;
import java.util.Date;

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

            viewModel.register(nameStr, emailStr, birthDateDate);
        });

        viewModel.getUser().observe(this, user -> {
            if (user != null) {
                Intent intent = new Intent(RegistrationActivity.this, ConfirmationActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
}