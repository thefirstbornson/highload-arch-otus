package ru.otus.highloadarch.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.otus.highloadarch.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    private RESTAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private RESTAuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private RESTAuthenticationSuccessHandler authenticationSuccessHandler;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final  MyUserDetailsService userDetailsService;

    public WebSecurityConfiguration(BCryptPasswordEncoder bCryptPasswordEncoder, MyUserDetailsService userDetailsService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
                auth
                    .userDetailsService(userDetailsService)
                    .passwordEncoder(bCryptPasswordEncoder);
//        auth.inMemoryAuthentication().withUser("user").password("{noop}user").roles("USER").and().withUser("admin")
//                .password("{noop}admin").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/rest/**").authenticated();
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
        http.formLogin().successHandler(authenticationSuccessHandler);
        http.formLogin().failureHandler(authenticationFailureHandler);
        http.logout().logoutSuccessUrl("/");
        http.csrf().disable();

        // CSRF tokens handling
//        http.addFilterAfter(new CsrfTokenResponseHeaderBindingFilter(), CsrfFilter.class);

    }


}
