package com.example.user.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.example.user.security.roles.ApplicationUserPermissions.STOCK_WRITE;
import static com.example.user.security.roles.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","index","/css/*", "/js/*").permitAll()
                .antMatchers("/api/users/*").hasRole(USER.name())
                .antMatchers(HttpMethod.DELETE,"/management/api/users/").hasAuthority(STOCK_WRITE.name())
                .antMatchers(HttpMethod.POST,"/management/api/users/").hasAuthority(STOCK_WRITE.name())
                .antMatchers(HttpMethod.GET,"/management/api/users/").hasAnyRole(ADMIN.name(), ADMIN_TRAINEE.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails jayrushUser = User.builder()
                .username("jayrush")
                .password(passwordEncoder.encode("jayrushDamn"))
//                .roles(USER.name()) // ROLE_USER
                .authorities(USER.getGrantedAuthorities())
                .build();

        UserDetails adminUser = User.builder()
                .username("frizzyondabeat")
                .password(passwordEncoder.encode("tomriddle"))
//                .roles(ADMIN.name()) // ROLE_ADMIN
                .authorities(ADMIN.getGrantedAuthorities())
                .build();

        UserDetails adminMinionUser = User.builder()
                .username("voldemort")
                .password(passwordEncoder.encode("tomriddle"))
//                .roles(ADMIN_TRAINEE.name()) // ROLE_ADMIN_TRAINEE
                .authorities(ADMIN_TRAINEE.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(jayrushUser, adminUser, adminMinionUser);
    }
}
