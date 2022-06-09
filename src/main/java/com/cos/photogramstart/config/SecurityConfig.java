package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity // 해당파일을 시큐리티를 활성화시킨다.
@Configuration // ioc 등록
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encode() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // super를 지웠음 -> 기존 시큐리티가 갖고 있는 기능이 다 비활성화 된다 .
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/", "/user/**", "/image/**", "/subscribe/**", "/comment/**",
                        "/api/**")
                .authenticated() // 이런 주소로 시작하게 되면 인증이 필요 -> 403 코드가 나옴
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/auth/signin") // get
                .loginProcessingUrl("/auth/signin") // post
                .defaultSuccessUrl("/"); // 그게 아니면 허용하겠다.
    }
}
