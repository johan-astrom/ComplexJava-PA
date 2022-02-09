package se.astrom.complexjava.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import se.astrom.complexjava.dto.ApplicationUserDto;
import se.astrom.complexjava.entity.ApplicationUser;
import se.astrom.complexjava.repository.ApplicationRoleRepository;
import se.astrom.complexjava.repository.ApplicationUserRepository;

@Service
public class ApplicationUserService {

    private final ApplicationUserRepository userRepository;
    private final ApplicationRoleRepository roleRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public ApplicationUserService(ApplicationUserRepository userRepository, ApplicationRoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public ApplicationUserDto createUser(ApplicationUserDto userDto){
        return null;
    }
}
