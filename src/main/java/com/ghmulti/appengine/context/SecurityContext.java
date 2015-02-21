package com.ghmulti.appengine.context;

import com.ghmulti.appengine.repository.UserRepository;
import com.ghmulti.appengine.service.SimpleSocialUserDetailService;
import com.ghmulti.appengine.service.SimpleUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.inject.Inject;

@Configuration
@EnableWebSecurity
public class SecurityContext extends WebSecurityConfigurerAdapter {

    @Inject
    private UserRepository userRepository;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
            //Spring Security ignores request to static resources such as CSS or JS files.
            .ignoring()
            .antMatchers("/css/**", "/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            //Configures form login
            .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/login/authenticate")
            .failureUrl("/login?error=bad_credentials")
            //Configures the logout function
            .and()
            .logout()
            .deleteCookies("JSESSIONID")
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login")
            //Configures url based authorization
            .and()
            .authorizeRequests()
            //Anyone can access the urls
            .antMatchers(
                    "/auth/**",
                    "/login",
                    "/signup/**",
                    "/user/register/**"
            ).permitAll()
            //The rest of the our application is protected.
            .antMatchers("/**").hasAnyRole("FOODER", "COOKER")
            //Adds the SocialAuthenticationFilter to Spring Security's filter chain.
            .and()
            .apply(new SpringSocialConfigurer());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userDetailsService())
            .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public SocialUserDetailsService socialUserDetailsService() {
        return new SimpleSocialUserDetailService(userDetailsService());
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new SimpleUserDetailService(userRepository);
    }
}
