package com.ghmulti.appengine.repository;

import com.ghmulti.appengine.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserProfile, Long> {

    UserProfile findByEmail(String email);

}
