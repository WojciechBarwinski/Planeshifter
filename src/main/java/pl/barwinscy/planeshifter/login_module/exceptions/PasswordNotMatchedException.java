package pl.barwinscy.planeshifter.login_module.exceptions;

import java.util.Map;

public class PasswordNotMatchedException extends RuntimeException {

    private Map<String, String> errorsMap;

    public PasswordNotMatchedException(Map<String, String> errorsMap) {
        this.errorsMap = errorsMap;
    }

    public Map<String, String> getErrorsMap() {
        return errorsMap;
    }
}
