package com.rajinder.noticeboard.Utils;

import android.widget.EditText;

import java.util.regex.Pattern;

public class Validation {

    // Regular Expression
    // you can change the expression based on your need
    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PHONE_REGEX = "\\d{3}\\d{7}";

    // Error Messages
    private static final String REQUIRED_MSG = "required";
    private static final String EMAIL_MSG = "invalid email";
    private static final String PHONE_MSG = "Please enter 10 digit number.";
    private static final String CONFIRM_PASSWORD = "not match password";
    private static final String WORK_EXPERIENCE = "can not be greater than 45";
    private static final String PASSWORD = "Password must be at least 5 characters long";

    // call this method when you need to check email validation
    public static boolean isEmailAddress(EditText editText, boolean required) {
        return isValid(editText, EMAIL_REGEX, EMAIL_MSG, required);
    }

    // call this method when you need to check phone number validation
    public static boolean isPhoneNumber(EditText editText, boolean required) {
        return isValid(editText, PHONE_REGEX, PHONE_MSG, required);
    }

    // return true if the input field is valid, based on the parameter passed
    public static boolean isValid(EditText editText, String regex, String errMsg, boolean required) {

        String text = editText.getText().toString().trim();
        // clearing the error, if it was previously set by some other values
        editText.setError(null);

        // text required and editText is blank, so return false
        if (required && !hasText(editText))
            return false;

        // pattern doesn't match so returning false
        if (required && !Pattern.matches(regex, text)) {
            editText.setError(errMsg);
            return false;
        }

        return true;
    }

    // check the input field has any text or not
    // return true if it contains text otherwise false
    public static boolean hasText(EditText editText) {

        String text = editText.getText().toString().trim();
        editText.setError(null);

        // length 0 means there is no text
        if (text.length() == 0) {
            editText.setError(REQUIRED_MSG);
            return false;
        }

        return true;
    }

    public static boolean isConfirmPassword(EditText editTextPassword, EditText editTextConfirmPasswords) {

        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPasswords.getText().toString().trim();

        if (!password.equals(confirmPassword)) {
            editTextConfirmPasswords.setError(CONFIRM_PASSWORD);
            return false;
        }

        return true;
    }


    public static boolean isWorkExperience(EditText signupWorkexp,boolean required)
    {
        String text = signupWorkexp.getText().toString().trim();
        // clearing the error, if it was previously set by some other values
        signupWorkexp.setError(null);

        // text required and editText is blank, so return false
        if (required && !hasText(signupWorkexp))
            return false;

        if(Integer.parseInt(signupWorkexp.getText().toString())>50)
        {  signupWorkexp.setError(WORK_EXPERIENCE);
            return false;}

        return true;
    }

    public static boolean isValidPassword(EditText pass,boolean required)
    {
        String text = pass.getText().toString().trim();
        // clearing the error, if it was previously set by some other values
        pass.setError(null);

        // text required and editText is blank, so return false
        if (required && !hasText(pass))
            return false;

        if(pass.getText().toString().length()<5)
        {  pass.setError(PASSWORD);
            return false;}

        return true;
    }


}
