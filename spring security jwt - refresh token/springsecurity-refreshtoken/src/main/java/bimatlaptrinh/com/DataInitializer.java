package bimatlaptrinh.com;

import bimatlaptrinh.com.model.User;
import bimatlaptrinh.com.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepo;
    private final PasswordEncoder encoder;

    @Override public void run(String... args) {
        if (userRepo.findByUsername("admin").isEmpty()) {
            userRepo.save(User.builder()
                    .username("admin")
                    .password(encoder.encode("123456"))
                    .roles(List.of("ROLE_ADMIN"))
                    .build());
        }
    }
}