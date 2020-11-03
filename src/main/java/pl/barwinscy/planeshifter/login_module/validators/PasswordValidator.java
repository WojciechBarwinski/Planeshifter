package pl.barwinscy.planeshifter.login_module.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.barwinscy.planeshifter.login_module.dtos.PasswordDto;

@Component
public class PasswordValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return PasswordDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PasswordDto password = (PasswordDto) o;
        validatePassword(errors, password);
    }

    private void validatePassword(Errors errors, PasswordDto password) {
        if (!password.getPasswordOne().equals(password.getPasswordTwo())) {
            errors.rejectValue("passwordOne", "password.validation.differentPass", "Passwords are different");
        } else {
            if (!password.getPasswordOne().matches("^.{5,}$")) {
                errors.rejectValue("passwordOne", "password.validation.toShort", "Password must have minimum 5 characters");
            }
            if (!password.getPasswordOne().matches("\\d+")) {
                errors.rejectValue("passwordOne", "password.validation.missDigit", "Password must contain minimum 1 digit");
            }
            if (!password.getPasswordOne().matches("[a-zA-Z]+")) {
                errors.rejectValue("passwordOne", "password.validation.missCharacter", "Password must contain minimum 1 character");
            }
        }
    }
}
