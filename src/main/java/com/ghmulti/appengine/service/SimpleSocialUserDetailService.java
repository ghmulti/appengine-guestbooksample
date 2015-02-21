package com.ghmulti.appengine.service;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

import javax.inject.Inject;

public class SimpleSocialUserDetailService implements SocialUserDetailsService {

    private UserDetailsService userDetailsService;

    @Inject
    public SimpleSocialUserDetailService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException, DataAccessException {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
        return (SocialUserDetails) userDetails;
    }

}
