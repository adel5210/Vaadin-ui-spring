package com.adel.vaadin.config;

import com.adel.vaadin.views.LoginView;
import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.Set;

@Configuration
public class SecurityConfig extends VaadinWebSecurity {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        setLoginView(http, LoginView.class);
    }

    @Bean
    public UserDetailsManager userDetailsManager() {
        var users = Set.of("adel", "waleed", "kenneth", "Abdul")
                .stream()
                .map(m -> User.withDefaultPasswordEncoder()
                .username(m)
                .password("pw")
                .roles("USER")
                .build())
                .toList();
        return new InMemoryUserDetailsManager(users);
    }

}
