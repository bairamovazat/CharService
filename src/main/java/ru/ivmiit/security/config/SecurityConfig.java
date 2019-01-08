package ru.ivmiit.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import ru.ivmiit.utils.PasswordEncoderUtils;

import javax.servlet.http.Cookie;
import javax.sql.DataSource;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/chats").hasAuthority("USER")
                    .antMatchers("/signUp").permitAll()
                    .antMatchers("/admin").hasAuthority("ADMIN")
                    .antMatchers("/static/js/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                .formLogin()
                    .usernameParameter("login")
                    .loginPage("/login")
                    .permitAll()
                    .successHandler((request, response, auth) -> {
                        Cookie cookie = new Cookie("success_login", "ok");
                        cookie.setHttpOnly(false);
                        response.addCookie(cookie);
                        response.sendRedirect("/chats");
                    })
                .and()
                .logout()
                    .deleteCookies("success_login", "JSESSIONID")
                .and()
                .rememberMe()
                    .rememberMeParameter("remember-me")
                    .tokenRepository(tokenRepository());
        http.csrf().disable();
    }


    @Bean
    public PersistentTokenRepository tokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(PasswordEncoderUtils.getBCryptPasswordEncoder());
    }

}
