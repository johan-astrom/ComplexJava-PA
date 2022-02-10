package se.astrom.complexjava;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import se.astrom.complexjava.dto.ApplicationUserPostDto;
import se.astrom.complexjava.entity.ApplicationRole;
import se.astrom.complexjava.entity.ApplicationUser;
import se.astrom.complexjava.repository.ApplicationRoleRepository;
import se.astrom.complexjava.repository.ApplicationUserRepository;
import se.astrom.complexjava.service.ApplicationUserService;

@SpringBootApplication
public class ComplexJavaPaApplication {

    private Logger logger = LoggerFactory.getLogger(ComplexJavaPaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ComplexJavaPaApplication.class, args);
    }

    @Bean
    public CommandLineRunner initializeRoles(ApplicationRoleRepository roleRepository,
                                             ApplicationUserRepository userRepository){
        return args -> {
            roleRepository.save(new ApplicationRole("ROLE_USER"));
            roleRepository.save(new ApplicationRole("ROLE_ADMIN"));
            roleRepository.save(new ApplicationRole("ROLE_MANAGER"));

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            var appUser = new ApplicationUser("admin", encoder.encode("password"));
            appUser.grantRole(roleRepository.findByRole("ROLE_ADMIN"));
            userRepository.save(appUser);

            StringBuilder sb = new StringBuilder();
            for (ApplicationRole role : appUser.getRoles()){
                sb.append(role.getRole());
            }
            logger.info("Default admin user roles : " + sb);
           };
    }

}
