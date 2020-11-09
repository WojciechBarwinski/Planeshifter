package pl.barwinscy.planeshifter.login_module.exceptions;

import pl.barwinscy.planeshifter.login_module.CustomError;

public class UsernameIsTakenException extends RuntimeException{

    private CustomError error;

    public UsernameIsTakenException(CustomError error) {
        this.error = error;
    }

    public CustomError getError() {
        return error;
    }
}
