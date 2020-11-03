package pl.barwinscy.planeshifter.login_module.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.barwinscy.planeshifter.login_module.dtos.PasswordDto;

@Component
public class NewUserValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return PasswordDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PasswordDto password = (PasswordDto) o;
        validatePassword(errors, password);
    }

    private void validatePassword(Errors errors, PasswordDto password){
        if (!password.getPasswordOne().equals(password.getPasswordTwo())){
            errors.rejectValue("passwordOne", "passwordOne.differentPass", "validator v2"); //passwordDto.passwordOne   differentPass.passwordDto.passwordOne
        }
    }
}
