package com.example.loginapp.models;

import com.example.loginapp.viewmodels.RegistrationViewModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@RunWith(JUnit4.class)
public class RegistrationViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private RegistrationViewModel viewModel;

    @Before
    public void setUp() {
        // Initialize the view model
        viewModel = new RegistrationViewModel();
    }

    @Test
    public void testRegister_InvalidEmail_ShouldNotSaveUser() {
        // Given an invalid email
        String invalidEmail = "invalidemail";

        // When register is called
        viewModel.register("Name", invalidEmail, new Date());

        // Then the user should not be saved
        Assert.assertNull(viewModel.getUser().getValue());
    }

    @Test
    public void testRegister_ValidInput_ShouldSaveUser() {
        // Given a valid email
        String validEmail = "test@zkb.com";

        // When register is called
        Calendar calendar = new GregorianCalendar(1996, Calendar.JULY, 22);
        Date validDate = calendar.getTime();
        viewModel.register("Name", validEmail, validDate);

        // Then the user should be saved
        User user = viewModel.getUser().getValue();
        Assert.assertNotNull(user);
        Assert.assertEquals("Name", user.getName());
        Assert.assertEquals(validEmail, user.getEmail());
    }

    @Test
    public void testRegister_InvalidDate_ShouldNotSaveUser() {
        // Given a valid email
        String validEmail = "test@zkb.com";
        // Given an invalid date
        Date invalidDate = new Date();  // or any other value that your code considers invalid

        // When register is called
        viewModel.register("Name", validEmail, invalidDate);

        // Then the user should not be saved
        Assert.assertNull(viewModel.getUser().getValue());
    }

    @Test
    public void testRegister_InvalidEmail_ValidDate_ShouldNotSaveUser() {
        // Given an invalid email
        String invalidEmail = "invalidEmail";
        // Given a valid date
        Calendar calendar = new GregorianCalendar(1996, Calendar.JULY, 22);
        Date validDate = calendar.getTime();

        // When register is called
        viewModel.register("Name", invalidEmail, validDate);

        // Then the user should not be saved
        Assert.assertNull(viewModel.getUser().getValue());
    }
}