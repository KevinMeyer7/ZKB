package com.example.loginapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.loginapp.models.User;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public boolean isValidName(String name) {
        return name != null && !name.isEmpty();
    }

    public boolean isValidEmail(String email) {
        /* Built-in, correct version that includes all cases:
        return email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches(); */

        // Manual regex. Breakdown: one or more chars + @ +
        // one or more chars + . + 2 or more chars until eol.
        if (email == null) {
            return false;
        }

        String regex = "^[\\w\\.-]+@[\\w\\.-]+\\.[\\w\\.-]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.find();
    }

    public boolean isValidBirthDate(Date birthDate) {
        // Birth date has to be between Jan 1st 1900 and Dec 31st 2021.
        Calendar start = Calendar.getInstance();
        start.set(1900, Calendar.JANUARY, 1);
        Calendar end = Calendar.getInstance();
        end.set(2021, Calendar.DECEMBER, 31);
        return birthDate != null && birthDate.after(start.getTime()) && birthDate.before(end.getTime());
    }
}
