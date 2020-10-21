package pl.barwinscy.planeshifter.login_module.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.barwinscy.planeshifter.login_module.UserMapperV2;
import pl.barwinscy.planeshifter.login_module.dtos.UserDto;
import pl.barwinscy.planeshifter.login_module.entities.User;
import pl.barwinscy.planeshifter.login_module.exceptions.PasswordNotMatchedException;
import pl.barwinscy.planeshifter.login_module.exceptions.ResourceNotFoundException;
import pl.barwinscy.planeshifter.login_module.repositories.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;
    private UserMapperV2 userMapper;
    private PasswordEncoder passwordEncoder;

    public UserDetailsServiceImpl(UserRepository userRepository, UserMapperV2 userMapper, PasswordEncoder passwordEncoder) {
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
        return userMapper.mapUserToUserDto(user);
    }

    public UserDto getUserByName(String userName) {
        User user = userRepository.findByUsername(userName).orElseThrow(ResourceNotFoundException::new);
        return userMapper.mapUserToUserDto(user);
    }

    public List<UserDto> getAllUsers() {
        List<User> all = userRepository.findAll();
        return all.stream()
                .map(x -> userMapper.mapUserToUserDto(x))
                .collect(Collectors.toList());
    }

    public UserDto changePassword(String userName, List<String> password) {
        User user = userRepository.findByUsername(userName).orElseThrow(ResourceNotFoundException::new);
        if (password.get(0).equals(password.get(1))) {
            user.setPassword(passwordEncoder.encode(password.get(0)));
        } else {
            Map<String, String> errorsMap = new HashMap<>();
            errorsMap.put("Blad 1", "haslo cos tam");
            errorsMap.put("blad 2", "haslo sie nie zgadza");

            throw new PasswordNotMatchedException(errorsMap);
        }
        userRepository.save(user);
        return userMapper.mapUserToUserDto(user);
    }
}
