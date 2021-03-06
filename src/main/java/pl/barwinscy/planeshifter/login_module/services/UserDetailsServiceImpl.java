package pl.barwinscy.planeshifter.login_module.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.barwinscy.planeshifter.login_module.CustomError;
import pl.barwinscy.planeshifter.login_module.UserMapper;
import pl.barwinscy.planeshifter.login_module.dtos.PasswordDto;
import pl.barwinscy.planeshifter.login_module.dtos.UserDto;
import pl.barwinscy.planeshifter.login_module.entities.User;
import pl.barwinscy.planeshifter.login_module.exceptions.ResourceNotFoundException;
import pl.barwinscy.planeshifter.login_module.exceptions.UsernameIsTakenException;
import pl.barwinscy.planeshifter.login_module.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    public UserDetailsServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userRepository.findByUsername(userName).orElseThrow(() ->
                new UsernameNotFoundException(String.format("Username %s not found", userName)));
    }

    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return userMapper.mapToUserDto(user);
    }

    public UserDto getUserByName(String userName) {
        User user = userRepository.findByUsername(userName).orElseThrow(ResourceNotFoundException::new);
        return userMapper.mapToUserDto(user);
    }

    public List<UserDto> getAllUsers() {
        List<User> all = userRepository.findAll();
        return all.stream()
                .map(userMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    public UserDto changePassword(String userName, PasswordDto password) {
        User user = userRepository.findByUsername(userName).orElseThrow(ResourceNotFoundException::new);
        user.setPassword(passwordEncoder.encode(password.getPasswordOne()));
        userRepository.save(user);
        return userMapper.mapToUserDto(user);
    }

    public UserDto createUser(UserDto userDto) {
        if (userRepository.findByUsername(userDto.getUsername()).orElse(null) != null) {
            throw new UsernameIsTakenException(new CustomError("UserDto", "user.validation.usernameIsTaken", "This username is already taken"));
        }
        User user = userRepository.save(userMapper.mapToUser(userDto));
        return userMapper.mapToUserDto(user);
    }

    public UserDto updateUser(UserDto userDto) {
        User user = userRepository.findByUsername(userDto.getUsername()).get();
        user.setPassword(passwordEncoder.encode(userDto.getPassword().getPasswordOne()));
        user.setRole(userDto.getRole());
        userRepository.save(user);
        return userMapper.mapToUserDto(user);
    }

    public void deleteUser(String username) {
        userRepository.deleteById(userRepository.findByUsername(username).get().getId());
    }

    public void deleteUserByAdmin(String userId) {
        userRepository.deleteById(Long.valueOf(userId));
    }
}
