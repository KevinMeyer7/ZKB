package com.example.loginapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.loginapp.models.User;

import java.util.Calendar;
import java.util.Date;

public class RegistrationViewModel extends ViewModel {
    private MutableLiveData<User> user;

    public RegistrationViewModel() {
        user = new MutableLiveData<>();
    }

    public LiveData<User> getUser() {
        return user;
    }

    public void register(String name, String email, Date birthDate) {
        if (isValidName(name) && isValidEmail(email) && isValidBirthDate(birthDate)) {
            user.setValue(new User(name, email, birthDate));
        }
    }

    private boolean isValidName(String name) {
        return name != null && !name.isEmpty();
    }

    private boolean isValidEmail(String email) {
        // Hier wird die E-Mail-Validierungsmethode implementiert
        // Sie können hier eine umfassendere Methode einfügen
        return email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidBirthDate(Date birthDate) {
        // Überprüfen Sie, ob das Geburtsdatum zwischen dem 1. Januar 1900 und dem 31. Dezember 2021 liegt
        Calendar start = Calendar.getInstance();
        start.set(1900, Calendar.JANUARY, 1);
        Calendar end = Calendar.getInstance();
        end.set(2021, Calendar.DECEMBER, 31);
        return birthDate != null && birthDate.after(start.getTime()) && birthDate.before(end.getTime());
    }
}
