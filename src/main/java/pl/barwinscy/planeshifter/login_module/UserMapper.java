package pl.barwinscy.planeshifter.login_module;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.barwinscy.planeshifter.login_module.dtos.PasswordDto;
import pl.barwinscy.planeshifter.login_module.dtos.UserDto;
import pl.barwinscy.planeshifter.login_module.entities.User;
import pl.barwinscy.planeshifter.login_module.enums.UserRole;

@Component
public class UserMapper {

    private PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User mapToUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword().getPasswordOne()));
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.setRole(assignRoleToUserAccount(userDto.getRole()));
        return user;
    }

    public UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setPassword(mapPasswordDto(user.getPassword()));
        userDto.setRole(user.getRole());
        return userDto;
    }

    private UserRole assignRoleToUserAccount(UserRole userRole) {
        switch (userRole) {
            case ADMIN:
                return UserRole.ADMIN;
            case USER_PREMIUM:
                return UserRole.USER_PREMIUM;
        }
        return UserRole.USER;
    }

    private PasswordDto mapPasswordDto(String password){
        PasswordDto passwordDto = new PasswordDto();
        passwordDto.setPasswordOne(password);
        passwordDto.setPasswordTwo(password);
        return passwordDto;
    }
}
