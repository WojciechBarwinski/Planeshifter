package pl.barwinscy.planeshifter.login_module.services;

import org.springframework.stereotype.Service;
import pl.barwinscy.planeshifter.login_module.entities.User;
import pl.barwinscy.planeshifter.login_module.repositories.UserRepository;
import pl.barwinscy.planeshifter.login_module.UserMapper;
import pl.barwinscy.planeshifter.login_module.dtos.UserDto;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    public RegistrationServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        userRepository.save(user);
        return userDto;
    }
}
