package com.ghmulti.appengine.service;

import com.ghmulti.appengine.model.SocialUserDetails;
import com.ghmulti.appengine.model.UserProfile;
import com.ghmulti.appengine.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.inject.Inject;

public class SimpleUserDetailService implements UserDetailsService {

    private UserRepository userRepository;

    @Inject
    public SimpleUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserProfile user = userRepository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("No user with username " + username);
        }

        SocialUserDetails socialUser = new SocialUserDetails.Builder()
                .firstName(user.getFirstName())
                .id(user.getId())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .role(user.getRole())
                .socialSignInProvider(user.getSignInProvider())
                .username(user.getEmail())
                .build();

        return socialUser;
    }

}
