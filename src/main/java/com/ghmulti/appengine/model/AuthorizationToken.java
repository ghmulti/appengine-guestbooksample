package com.ghmulti.appengine.model;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="authorization_token")
public class AuthorizationToken {

    private final static Integer DEFAULT_TIME_TO_LIVE_IN_SECONDS = (60 * 60 * 24 * 30);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "token", length=36)
    private String token;

    @Temporal(TemporalType.DATE)
    @Column(name = "time_created")
    private Date timeCreated;

    @Temporal(TemporalType.DATE)
    @Column(name = "exporation_date")
    private Date expirationDate;

    @JoinColumn(name = "user_id")
    @OneToOne(fetch = FetchType.LAZY)
    private UserProfile userProfile;

    public AuthorizationToken() {}

    public AuthorizationToken(UserProfile userProfile) {
        this(userProfile, DEFAULT_TIME_TO_LIVE_IN_SECONDS);
    }

    public AuthorizationToken(UserProfile userProfile, Integer timeToLiveInSeconds) {
        this.token = UUID.randomUUID().toString();
        this.userProfile = userProfile;
        this.timeCreated = new Date();
        this.expirationDate = new Date(System.currentTimeMillis() + (timeToLiveInSeconds * 1000L));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean hasExpired() {
        return this.expirationDate != null && this.expirationDate.before(new Date());
    }

    public String getToken() {
        return token;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
