package pl.barwinscy.planeshifter.registration_module;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.barwinscy.planeshifter.login_module.UserRole;
import pl.barwinscy.planeshifter.login_module.entities.User;
import pl.barwinscy.planeshifter.registration_module.dto.UserDto;

@Component
public class UserMapper {

    private PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User mapToUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.setRole(assignRoleToUserAccount(userDto.getRole()));
        return user;
    }

    private UserRole assignRoleToUserAccount(UserRole userRole) {
        switch (userRole) {
            case ADMIN:
                return UserRole.ADMIN;
            case USER:
                return UserRole.USER;
            case USER_PREMIUM:
                return UserRole.USER_PREMIUM;
        }
        return UserRole.USER;
    }
}
