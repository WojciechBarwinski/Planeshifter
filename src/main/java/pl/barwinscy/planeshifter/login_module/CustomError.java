package pl.barwinscy.planeshifter.login_module;


import lombok.Data;

@Data
public class CustomError {

    private String objectName;
    private String errorCode;
    private String massage;

    public CustomError(String objectName, String errorCode, String massage) {
        this.objectName = objectName;
        this.errorCode = errorCode;
        this.massage = massage;
    }
}
