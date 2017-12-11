package org.miage.sid.mailer.service;

import org.miage.sid.mailer.persistence.User;
import org.miage.sid.mailer.persistence.UserRepository;
import org.miage.sid.mailer.persistence.UserRole;
import org.miage.sid.mailer.persistence.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    private final UserRoleRepository userRoleRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository,UserRoleRepository userRolesRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository=userRolesRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " not found.");
        } else {
            List<String> roles = userRoleRepository.findRoleByUsername(username);
            return new CustomUserDetails(user, roles);
        }

    }
}
