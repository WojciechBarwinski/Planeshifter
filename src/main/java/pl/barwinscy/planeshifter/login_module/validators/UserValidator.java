package pl.barwinscy.planeshifter.login_module.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.barwinscy.planeshifter.login_module.dtos.PasswordDto;
import pl.barwinscy.planeshifter.login_module.dtos.UserDto;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return UserDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDto userDto = (UserDto) o;
        validateUser(errors, userDto);
    }

    private void validateUser(Errors errors, UserDto user){
        String userName = user.getUsername();
        if (userName.isEmpty()){
            errors.rejectValue("username", "user.validation.emptyUsername", "No username");
        }
        if(userName.length() < 4){
            errors.rejectValue("username", "user.validation.tooShort", "Username is too short, minimum 4 characters");
        }
        validatePassword(errors, user.getPassword());
    }

    private void validatePassword(Errors errors, PasswordDto password) {
        if (!password.getPasswordOne().equals(password.getPasswordTwo())) {
            errors.rejectValue("password.passwordOne", "password.validation.differentPass", "Passwords are different");
        } else {
            if (!password.getPasswordOne().matches("^.{5,}$")) {
                errors.rejectValue("password.passwordOne", "password.validation.toShort", "Password must have minimum 5 characters");
            }
            if (!password.getPasswordOne().matches("\\d+")) {
                errors.rejectValue("password.passwordOne", "password.validation.missDigit", "Password must contain minimum 1 digit");
            }
            if (!password.getPasswordOne().matches("[a-zA-Z]+")) {
                errors.rejectValue("password.passwordOne", "password.validation.missCharacter", "Password must contain minimum 1 character");
            }
        }
    }
}
