package pl.barwinscy.planeshifter.login_module.exceptions;

import org.springframework.validation.Errors;
import pl.barwinscy.planeshifter.login_module.CustomError;

import java.util.ArrayList;
import java.util.List;

public class UserNotMatchedException extends RuntimeException {


    private List<CustomError> errorList;

    public UserNotMatchedException(Errors errors) {
        createErrorList(errors);
    }

    public List<CustomError> getErrorList() {
        return errorList;
    }

    private void createErrorList(Errors errors){
        errorList = new ArrayList<>();
        errors.getAllErrors()
                .forEach(x -> errorList.add(new CustomError(x.getObjectName(), x.getCode(), x.getDefaultMessage())));
    }
}
