package ru.itis.khammatova.web.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.itis.khammatova.web.security.details.UserDetailsServiceImpl;
import ru.itis.khammatova.web.security.filters.OauthUserFilter;
import ru.itis.khammatova.web.security.jwt.JwtAuthenticationFilter;
import ru.itis.khammatova.web.security.jwt.JwtAuthenticationProvider;

@EnableWebSecurity
public class GlobalSecurityConfig {

    @Order(2)
    @Configuration
    public static class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Autowired
        private OauthUserFilter googleUserFilter;

        @Autowired
        @Qualifier("UserDetailsServiceImpl")
        private UserDetailsService userDetailsService;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                        .antMatchers("/profile").authenticated().and()
                    .formLogin()
                        .loginPage("/signIn")
                        .usernameParameter("email")
                        .defaultSuccessUrl("/profile")
                        .failureUrl("/signIn?error").and()
                    .oauth2Login().
                        defaultSuccessUrl("/profile").and()
                    .addFilterAfter(googleUserFilter, UsernamePasswordAuthenticationFilter.class)
                    .logout()
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                        .logoutSuccessUrl("/signIn")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID");
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        }
    }

    @Order(1)
    @Configuration
    public static class ApiSecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Autowired
        @Qualifier("UserDetailsServiceImpl")
        UserDetailsServiceImpl userDetailsService;

        @Autowired
        private JwtAuthenticationFilter jwtAuthenticationFilter;

        @Autowired
        private JwtAuthenticationProvider jwtAuthenticationProvider;


        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/api/**")
                        .addFilterAt(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                        .csrf().disable()
                        .sessionManagement().disable()
                        .authorizeRequests()
                            .antMatchers("/api/jwt/**", "/api/users/count").permitAll()
                            .anyRequest().authenticated().and();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(jwtAuthenticationProvider);
        }
    }
}
