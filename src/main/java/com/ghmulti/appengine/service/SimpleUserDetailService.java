package com.ghmulti.appengine.service;

import com.ghmulti.appengine.dto.SocialUserDetails;
import com.ghmulti.appengine.model.UserProfile;
import com.ghmulti.appengine.repository.UserProfileRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.inject.Inject;

public class SimpleUserDetailService implements UserDetailsService {

    @Inject
    private UserProfileRepository userProfileRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserProfile user = userProfileRepository.findByEmailAddress(username);

        if (user == null) {
            throw new UsernameNotFoundException("No user with username " + username);
        }

        SocialUserDetails socialUser = new SocialUserDetails.Builder()
                .firstName(user.getFirstName())
                .id(user.getId())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .role(user.getRole())
                .username(user.getEmail())
                .build();

        return socialUser;
    }

}
