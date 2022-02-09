package se.astrom.complexjava.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import se.astrom.complexjava.dto.ApplicationUserGetDto;
import se.astrom.complexjava.dto.ApplicationUserPostDto;
import se.astrom.complexjava.entity.ApplicationRole;
import se.astrom.complexjava.entity.ApplicationUser;
import se.astrom.complexjava.mapper.Microsoft365LicensesMapper;
import se.astrom.complexjava.repository.ApplicationRoleRepository;
import se.astrom.complexjava.repository.ApplicationUserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ApplicationUserService {

    private final ApplicationUserRepository userRepository;
    private final ApplicationRoleRepository roleRepository;
    private final Microsoft365LicensesMapper mapper;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public ApplicationUserService(ApplicationUserRepository userRepository,
                                  ApplicationRoleRepository roleRepository,
                                  Microsoft365LicensesMapper mapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.mapper = mapper;
    }

    public ApplicationUserGetDto createUser(ApplicationUserPostDto userPostDto){
        ApplicationUser appUser = mapper.appUserPostDtoToAppUser(userPostDto);
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        ApplicationRole roleToGrant = roleRepository.findByRole("USER");
        appUser.grantRole(roleToGrant);
        return mapper.appUserToAppUserGetDto(userRepository.save(appUser));
    }

    public void deleteUserById(Long id){
        ApplicationUser appUser = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        userRepository.deleteById(appUser.getId());
    }

    public ApplicationUserGetDto getUserById(Long id){
        return mapper.appUserToAppUserGetDto(userRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public Iterable<ApplicationUserGetDto> getUsers(){
        return mapper.appUserIterableToAppUserDtoIterable(userRepository.findAll());
    }

}
