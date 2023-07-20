package com.example.loginapp.models;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Date;

public class UserTest {
    @Test
    public void user_isCorrect() throws Exception {
        // Arrange
        String expectedName = "John Doe";
        String expectedEmail = "john.doe@example.com";
        Date expectedBirthDate = new Date(80, 1, 1); // February 1, 1980

        // Act
        User user = new User(expectedName, expectedEmail, expectedBirthDate);

        // Assert
        assertEquals(expectedName, user.getName());
        assertEquals(expectedEmail, user.getEmail());
        assertEquals(expectedBirthDate, user.getBirthDate());
    }
}
