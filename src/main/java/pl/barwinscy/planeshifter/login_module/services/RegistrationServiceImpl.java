package pl.barwinscy.planeshifter.login_module.services;

import org.springframework.stereotype.Service;
import pl.barwinscy.planeshifter.login_module.UserMapperV2;
import pl.barwinscy.planeshifter.login_module.entities.User;
import pl.barwinscy.planeshifter.login_module.repositories.UserRepository;
import pl.barwinscy.planeshifter.login_module.UserMapper;
import pl.barwinscy.planeshifter.login_module.dtos.UserDto;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private UserRepository userRepository;
    //private UserMapper userMapper;
    private UserMapperV2 userMapper;

    public RegistrationServiceImpl(UserRepository userRepository, UserMapperV2 userMapperV2) {
        this.userRepository = userRepository;
        this.userMapper = userMapperV2;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userMapper.mapUserDtoToUser(userDto);
        userRepository.save(user);
        return userDto;
    }
}
