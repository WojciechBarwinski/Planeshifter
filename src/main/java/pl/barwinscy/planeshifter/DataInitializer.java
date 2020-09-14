package pl.barwinscy.planeshifter;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.barwinscy.planeshifter.login_module.entities.User;
import pl.barwinscy.planeshifter.login_module.repositories.UserRepository;

import java.util.Arrays;
import java.util.List;

import static pl.barwinscy.planeshifter.login_module.enums.UserRole.*;

@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;


    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        userRepository.saveAll(createInitUsers());
    }

    private List<User> createInitUsers(){
        return Arrays.asList(
                new User("admin", passwordEncoder.encode("1234"), ADMIN, true, true, true, true),
                new User("userP", passwordEncoder.encode("123"), USER_PREMIUM, true, true, true, true),
                new User("user", passwordEncoder.encode("123"), USER, true, true, true, true)
        );
    }
}
