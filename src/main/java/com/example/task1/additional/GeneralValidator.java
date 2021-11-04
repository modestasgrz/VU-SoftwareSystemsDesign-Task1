package com.example.task1.additional;

import com.example.task1.model.User;
import com.example.task1.validators.EmailValidator;
import com.example.task1.validators.PasswordValidator;
import com.example.task1.validators.PhoneValidator;

public class GeneralValidator {

    //Singleton
    private GeneralValidator() {}
    public static GeneralValidator getInstance() {
        if (instance == null) {
            instance = new GeneralValidator();
            return instance;
        }
        else return instance;
    }
    private static GeneralValidator instance;

    public boolean validateUser(User user) {
         return EmailValidator.checkEmail(user.getEmail()) &&
                PasswordValidator.checkPassword(user.getPassword(), user.getPassword().length()) &&
                PhoneValidator.checkPhoneFormat(user.getPhoneNumber());
    }
}
