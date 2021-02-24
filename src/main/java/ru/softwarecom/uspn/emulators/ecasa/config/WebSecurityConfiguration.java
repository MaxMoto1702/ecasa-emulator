package ru.softwarecom.uspn.emulators.ecasa.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.webservices.WebServicesProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
@EnableWebSecurity(debug = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final WebServicesProperties webServicesProperties;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers(
                        webServicesProperties.getPath(),
                        "/debug/**",
                        "/eca-ws/**",
                        "/actuator/**",
                        "/error/**"
                );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers(this::headers)
                .csrf(this::csrf)
                .authorizeRequests(this::authorizeRequests)
//                .formLogin(this::formLogin);
                .formLogin()
        ;
    }

    private void formLogin(FormLoginConfigurer<HttpSecurity> formLogin) {
    }

    private void authorizeRequests(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests) {
        authorizeRequests
                .antMatchers(
                        webServicesProperties.getPath(),
                        "/debug/**",
                        "/eca-ws/**",
                        "/actuator/**",
                        "/error/**"
                )
                .permitAll()

                .anyRequest()
                .authenticated();
    }

    private void csrf(CsrfConfigurer<HttpSecurity> csrf) {
        csrf.disable();
    }

    private void headers(HeadersConfigurer<HttpSecurity> headers) {
        headers.frameOptions().disable();
    }
}
