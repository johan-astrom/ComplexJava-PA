package se.astrom.complexjava;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import se.astrom.complexjava.entity.ApplicationRole;
import se.astrom.complexjava.entity.ApplicationUser;
import se.astrom.complexjava.repository.ApplicationRoleRepository;
import se.astrom.complexjava.repository.ApplicationUserRepository;

@SpringBootApplication
public class ComplexJavaPaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComplexJavaPaApplication.class, args);
    }

    @Bean
    public CommandLineRunner initializeRoles(ApplicationRoleRepository roleRepository, ApplicationUserRepository userRepository){
        return args -> {
            roleRepository.save(new ApplicationRole("USER"));
            roleRepository.save(new ApplicationRole("ADMIN"));
            roleRepository.save(new ApplicationRole("MANAGER"));

//            var appUser = new ApplicationUser("admin", "password");
//            appUser.grantRole(roleRepository.findByRole("ADMIN"));
//            userRepository.save(appUser);
        };
    }

}
