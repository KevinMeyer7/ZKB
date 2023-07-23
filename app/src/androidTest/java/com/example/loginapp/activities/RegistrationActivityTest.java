package com.example.loginapp.activities;

import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.loginapp.R;
import com.example.loginapp.activities.RegistrationActivity;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import android.widget.DatePicker;

// E2E test with correct invocation of the ConfirmationActivity.
@RunWith(AndroidJUnit4.class)
public class RegistrationActivityTest {
    @Rule
    public ActivityScenarioRule<RegistrationActivity> activityRule = new ActivityScenarioRule<>(RegistrationActivity.class);

    @Test
    public void testRegistration() {
        onView(withId(R.id.name)).perform(typeText("Test User"));
        onView(withId(R.id.email)).perform(typeText("testuser@example.com"), closeSoftKeyboard());

        // Open DatePicker Dialog by clicking on it
        onView(withId(R.id.birth_date)).perform(click());

        // Get a date object with the desired date
        Date birthDate = new GregorianCalendar(1990, Calendar.JANUARY, 1).getTime();

        // Set DatePicker to the desired date
        onView(withClassName(is(equalTo(DatePicker.class.getName()))))
                .perform(PickerActions.setDate(
                        birthDate.getYear() + 1900,  // DatePicker uses year as (year - 1900)
                        birthDate.getMonth() + 1,    // Months are zero-based in Date, but not in DatePicker
                        birthDate.getDate()));

        // Perform registration
        onView(withId(R.id.register)).perform(click());
        // Assuming you go to ConfirmationActivity and there's a TextView with id "confirmation_message"
        onView(withId(R.id.confirmation_message)).check(matches(withText("Thank you for registering, Test User!")));
    }
}