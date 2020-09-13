package pl.barwinscy.planeshifter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.barwinscy.planeshifter.login_module.UserRole;

@SpringBootApplication
public class PlaneshifterApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlaneshifterApplication.class, args);
    }

}
