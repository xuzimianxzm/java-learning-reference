package com.xuzimian.java.learning.spring.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final String rememberKey;
    private final DataSource dataSource;
    private final UserDetailsService userDetailsService;
    private final SpringSessionBackedSessionRegistry sessionRegistry;

    @Autowired
    public SecurityConfig(@Value("${spring.security.remember-me.key}") String rememberKey,
                          DataSource dataSource,
                          UserDetailsService userDetailsService,
                          SpringSessionBackedSessionRegistry sessionRegistry) {
        this.rememberKey = rememberKey;
        this.dataSource = dataSource;
        this.userDetailsService = userDetailsService;
        this.sessionRegistry = sessionRegistry;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();

        tokenRepository.setDataSource(dataSource);

        http.authorizeRequests()
                .antMatchers("/admin/api/**")
                .hasRole("ADMIN")
                .antMatchers("/user/api/**")
                .hasRole("USER")
                .antMatchers("/app/api/**")
                .permitAll()
                .antMatchers("/cas/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .rememberMe()
                .userDetailsService(userDetailsService)
                .and()
                .logout()
                .logoutUrl("/myLogout")
                // 注销成功，重定向到该路径下
                .logoutSuccessUrl("/user/api/hello")
                .invalidateHttpSession(true)
                .and()
                .csrf()
                .disable()
                .sessionManagement()
                .maximumSessions(1)
                .sessionRegistry(sessionRegistry);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
