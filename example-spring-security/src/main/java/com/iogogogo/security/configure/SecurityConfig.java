package com.iogogogo.security.configure;

import com.iogogogo.commons.util.HttpWebContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tao.zeng on 2021/1/8.
 */
@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Password encoder password encoder.
     *
     * @return the password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // auth.userDetailsService(s -> new UserEntity());
        auth.inMemoryAuthentication()
                .withUser("admin").password("$2a$10$/RPcaiXOaKZV.tK7NjTgnu07Xn3lO/e3pToN8IdIimNUbSot4B.j6").roles("admin");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 忽略静态web资源
        web.ignoring().antMatchers("/resources/**/*.html", "/resources/**/*.js");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        AuthHandler authHandler = new AuthHandler();

        http
                // 所有请求都需要认证
                // 登录配置
                .formLogin().loginPage("/login").permitAll()
                // 登录成功Handler
                .successHandler(authHandler)
                // 登录失败Handler
                .failureHandler(authHandler)
                .and()

                .authorizeRequests().anyRequest().authenticated()
                .and()
                // 登出成功Handler
                .logout()
                .logoutSuccessHandler(authHandler)
                .and()
                // 未认证处理
                .exceptionHandling()
                .authenticationEntryPoint(authHandler)
                .and()
                .cors().disable();
    }

    private static class AuthHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler, LogoutSuccessHandler, AuthenticationEntryPoint {

        /**
         * 登录成功
         *
         * @param request
         * @param response
         * @param authentication
         * @throws IOException
         * @throws ServletException
         */
        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
            log.info("login successHandler");
            HttpWebContext.writer(request, response, authentication.getCredentials());
        }

        /**
         * 登录失败
         *
         * @param request
         * @param response
         * @param e
         * @throws IOException
         * @throws ServletException
         */
        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
            log.info("login failureHandler");
            HttpWebContext.writer(request, response, e);
        }

        /**
         * 退出登录
         *
         * @param request
         * @param response
         * @param authentication
         * @throws IOException
         * @throws ServletException
         */
        @Override
        public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
            log.info("logout logoutSuccessHandler");
            HttpWebContext.writer(request, response, authentication.getCredentials());
        }

        /**
         * 未认证处理
         *
         * @param request
         * @param response
         * @param e
         * @throws IOException
         * @throws ServletException
         */
        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
            log.info("authenticationEntryPoint");
            HttpWebContext.writer(request, response, e);
        }
    }
}
