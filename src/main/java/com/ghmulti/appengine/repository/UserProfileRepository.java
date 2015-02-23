package com.ghmulti.appengine.repository;

import com.ghmulti.appengine.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    UserProfile findByEmailAddress(String emailAddress);

    @Query("select up from UserProfile up where uuid = ?")
    UserProfile findByUuid(String uuid);

}
