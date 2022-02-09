package se.astrom.complexjava.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import se.astrom.complexjava.entity.ApplicationUser;
import se.astrom.complexjava.repository.ApplicationUserRepository;

@Service
public class M365LicensesUserDetailsService implements UserDetailsService{

    private final ApplicationUserRepository userRepository;

    public M365LicensesUserDetailsService(ApplicationUserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser appUser = userRepository.findByUsername(username);
        if(appUser == null){
            throw new UsernameNotFoundException("No user found with username: " + username);
        }
        return new M365LicensesUserPrincipal(appUser);
    }
}
