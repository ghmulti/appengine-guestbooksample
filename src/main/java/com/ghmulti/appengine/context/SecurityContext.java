package com.ghmulti.appengine.context;

import com.ghmulti.appengine.repository.UsersRepository;
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

@Configuration
@EnableWebSecurity
public class SecurityContext extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
            .antMatchers("/css/**", "/images/**", "/js/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/login/authenticate")
            .failureUrl("/login?error=bad_credentials")
            .and()
            .logout()
            .deleteCookies("JSESSIONID")
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login")
            .and()
            .authorizeRequests()
            .antMatchers(
                    "/auth/**",
                    "/login",
                    "/signup/**",
                    "/user/register/**"
            ).permitAll()
            .antMatchers("/**").hasAnyRole("ROLE_FOODER", "ROLE_COOKER");
            //.and()
            //.apply(new SpringSocialConfigurer());
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
    public UsersRepository usersRepository() {
        return new UsersRepository();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new SimpleUserDetailService(usersRepository());
    }
}
