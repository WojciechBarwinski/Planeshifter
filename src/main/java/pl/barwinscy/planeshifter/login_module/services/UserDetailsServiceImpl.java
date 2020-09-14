package pl.barwinscy.planeshifter.login_module.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.barwinscy.planeshifter.login_module.entities.User;
import pl.barwinscy.planeshifter.login_module.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userRepository.findByUsername(userName).orElseThrow(() ->
                new UsernameNotFoundException(String.format("Username %s not found", userName)));
    }


    public User getUserById (Long id){
        return userRepository.findById(id).get();
    }
}
