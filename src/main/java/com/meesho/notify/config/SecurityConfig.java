package com.meesho.notify.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

/*@Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(PasswordEncoder(P)).withUser("user1").password("secret1")
                .roles("USER").and().withUser("admin1").password("secret1")
                .roles("USER", "ADMIN");
    }

    // Authorization : Role -> Access
    // survey -> USER
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests().antMatchers("/surveys/**")
                .hasRole("USER").antMatchers("/users/**").hasRole("USER")
                .antMatchers("/**").hasRole("ADMIN").and().csrf().disable()
                .headers().frameOptions().disable();
    }*/

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("secret")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests()
                //.antMatchers("/user/**").hasRole("ADMIN")
                .antMatchers("/v1/**").hasRole("USER")
                .and().csrf().disable().headers().frameOptions().disable()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
   }
