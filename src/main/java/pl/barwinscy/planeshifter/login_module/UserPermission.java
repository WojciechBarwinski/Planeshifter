package pl.barwinscy.planeshifter.login_module;

public enum  UserPermission {
    PLAYER("normal game"),
    PREMIUM_PLAYER("premium game"),
    ADMIN_PERMISSION("administrator"),
    ACCESS_H2("h2 access");

    final private String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
