# ZKB
# Android Registration App

This is a simple registration app built for Android. It consists of two screens: a registration screen, and a confirmation screen. The app follows the Model-View-ViewModel (MVVM) architecture and is written in Java.

## Visuals

Disclaimer: please use the 1080p version in order to avoid YouTube compression.
Screen recording for basic usage of app: https://drive.google.com/file/d/1QbOdBKbtqVunc_0tOzrtObhhwoysmy-H/view?usp=sharing 
Screen recording of failure - Toast message: https://drive.google.com/file/d/1dbRwxjuUP9dWwU1F-PQ9c3wE74LTcBlR/view?usp=sharing

## Features

- **Registration Screen**: This screen includes fields for name, email, and birth date. It includes validations for each field and a "Register" button to submit the form.

- **Confirmation Screen**: Upon successful registration, the user is taken to this screen, which displays a static "Thank you for registering" message, along with the user's entered information.

## Improvement

- **Better UI for the date picker**: Use DialogFragment for DatePicker (popup dialog) or custom date picker.
- **Validation logic**: More advanced validation logic should be separated into a module - when not using built-in libraries.
- **Fragment usage instead of activities**: Architecturally, invoking fragments instead of activities can improve the overall usage - stack of fragments with one host activity vs. invoking multiple activities.
- **Miscellanous**: Save to backend instead of local shared preferences, provide navigation between activities, etc.



     


