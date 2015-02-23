package com.ghmulti.appengine.service;

import com.ghmulti.appengine.dto.AuthenticatedUserToken;
import com.ghmulti.appengine.exception.AuthenticationException;
import com.ghmulti.appengine.model.AuthorizationToken;
import com.ghmulti.appengine.model.UserProfile;
import com.ghmulti.appengine.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class UsersService {

    private UserProfileRepository userRepository;

    private UsersConnectionRepository jpaUsersConnectionRepository;

    @Autowired
    public UsersService(UsersConnectionRepository usersConnectionRepository, UserProfileRepository userRepository) {
        this.jpaUsersConnectionRepository = usersConnectionRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public AuthenticatedUserToken socialLogin(Connection<?> connection) throws AuthenticationException {
        List<String> userUuids = jpaUsersConnectionRepository.findUserIdsWithConnection(connection);
        if(userUuids.size() == 0) {
            throw new AuthenticationException();
        }
        UserProfile user = userRepository.findByUuid(userUuids.get(0));
        if (user == null) {
            throw new AuthenticationException();
        }
        return new AuthenticatedUserToken(user.getEmail(), createAuthorizationToken(user).getToken());
    }

    public AuthorizationToken createAuthorizationToken(UserProfile user) {
        if(user.getAuthorizationToken() == null || user.getAuthorizationToken().hasExpired()) {
            user.setAuthorizationToken(new AuthorizationToken(user, 1*24*60*60));
            userRepository.save(user);
        }
        return user.getAuthorizationToken();
    }

}
